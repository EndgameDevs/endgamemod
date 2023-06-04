package codes.dreaming.endgamemod.block.entity;

import codes.dreaming.endgamemod.block.MixerMenuScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import static codes.dreaming.endgamemod.registry.BlockEntityRegistry.MIXER_BLOCK_ENTITY;

public class MixerBlockEntity extends BlockEntity implements MenuProvider, IAnimatable, WorldlyContainer {
    private final AnimationFactory manager = GeckoLibUtil.createFactory(this);
    private final NonNullList<ItemStack> inv;
    private int status = 0;
    private int max = 100;
    ContainerData containerData = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> MixerBlockEntity.this.status;
                case 1 -> MixerBlockEntity.this.max;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int j) {
            switch (i) {
                case 0 -> MixerBlockEntity.this.status = j;
                case 1 -> MixerBlockEntity.this.max = j;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public MixerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MIXER_BLOCK_ENTITY.get(), blockPos, blockState);
        inv = NonNullList.create();
        inv.add(ItemStack.EMPTY);
        inv.add(ItemStack.EMPTY);
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;
        controller.setAnimation(new AnimationBuilder().addAnimation("animation.model.foodinside", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    private static void craft(MixerBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.getContainerSize());
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory.setItem(i, entity.getItem(i));
        }

        if (checkRecipe(entity)) {
            entity.removeItem(0, 1);
            entity.setItem(1, new ItemStack(Items.BAKED_POTATO, entity.getItem(1).getCount() + 1));
            entity.resetStatus();

        }
    }

    private static boolean checkRecipe(MixerBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.getContainerSize());
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory.setItem(i, entity.getItem(i));
        }

        boolean food = entity.getItem(0).getItem() == Items.POTATO;
        return food && increaseOutput(inventory) && itemOutput(inventory, Items.BAKED_POTATO);
    }

    private static boolean itemOutput(SimpleContainer inventory, Item output) {
        return inventory.getItem(1).getItem() == output || inventory.getItem(1).isEmpty();
    }

    private static boolean increaseOutput(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.endgame.mixer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new MixerMenuScreenHandler(i, inventory, this, this.containerData);
    }

    public void dropBlock() {
        SimpleContainer inventory = new SimpleContainer();

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        ContainerHelper.loadAllItems(compoundTag, inv);
        compoundTag.putInt("mixer.status", status);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ContainerHelper.saveAllItems(compoundTag, inv);
        status = compoundTag.getInt("mixer.status");
    }

    private void resetStatus() {
        status = 0;
    }

    //WORLDYCONTAINER
    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[2];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.get(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int i) {
        return inv.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        ItemStack result = ContainerHelper.removeItem(inv, i, j);
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(inv, i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        inv.set(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        inv.clear();
    }

    public void tick(Level l, MixerBlockEntity mixerBlockEntity) {
        if (level.isClientSide()) {
            return;
        }
        if (checkRecipe(mixerBlockEntity)) {
            mixerBlockEntity.status++;
            if (mixerBlockEntity.status >= mixerBlockEntity.max) {
                craft(mixerBlockEntity);
            }
        } else {
            mixerBlockEntity.resetStatus();
        }

    }

    @Override
    public void registerControllers(AnimationData data) {
        //Convert controllers.add(new AnimationController<>(this, state -> state.setAndContinue(DEFAULT_ANIM)));
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }
}
