package dev.endgame.block.entity;

import dev.endgame.block.MixerMenuScreenHandler;
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
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import static dev.endgame.registry.BlockEntityRegistry.MIXER_BLOCK_ENTITY;

public class MixerBlockEntity extends BlockEntity implements GeoBlockEntity, MenuProvider, WorldlyContainer {
    private static final RawAnimation DEFAULT_ANIM = RawAnimation.begin().thenPlayAndHold("animation.model.foodinside");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int status=0;
    private int max=100;
    ContainerData containerData=new ContainerData() {
        @Override
        public int get(int i) {
            switch (i) {
                case 0: return MixerBlockEntity.this.status;
                case 1: return MixerBlockEntity.this.max;
                default: return 0;
            }
        }

        @Override
        public void set(int i, int j) {
            switch(i) {
                case 0: MixerBlockEntity.this.status = j; break;
                case 1: MixerBlockEntity.this.max = j; break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    private final NonNullList<ItemStack> inv;
    public MixerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MIXER_BLOCK_ENTITY.get(), blockPos, blockState);
        inv=NonNullList.create();
        inv.add(ItemStack.EMPTY);
        inv.add(ItemStack.EMPTY);
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> state.setAndContinue(DEFAULT_ANIM)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.endgame.mixer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new MixerMenuScreenHandler(i, inventory,this,this.containerData);
    }
    public void dropBlock() {
        SimpleContainer inventory = new SimpleContainer();

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        ContainerHelper.loadAllItems(compoundTag,inv);
        compoundTag.putInt("mixer.status",status);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ContainerHelper.saveAllItems(compoundTag,inv);
        status=compoundTag.getInt("mixer.status");
    }
    private void resetStatus(){
        status=0;
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
        inv.set(i,itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        inv.clear();
    }

    private static void craft(MixerBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.getContainerSize());
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory.setItem(i, entity.getItem(i));
        }

        if(checkRecipe(entity)) {
            entity.removeItem(0,1);
            entity.setItem(1, new ItemStack(Items.BAKED_POTATO, entity.getItem(1).getCount()+1));
            entity.resetStatus();

        }
    }
    private static boolean checkRecipe(MixerBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.getContainerSize());
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory.setItem(i, entity.getItem(i));
        }

        boolean food = entity.getItem(0).getItem() == Items.POTATO;
        return food && increaseOutput(inventory)
                && itemOutput(inventory, Items.BAKED_POTATO);
    }

    private static boolean itemOutput(SimpleContainer inventory, Item output) {
        return inventory.getItem(1).getItem() == output || inventory.getItem(1).isEmpty();
    }

    private static boolean increaseOutput(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }

    public void tick(Level l, MixerBlockEntity mixerBlockEntity) {
        if(level.isClientSide()) {
            return;
        }
        if(checkRecipe(mixerBlockEntity)) {
            mixerBlockEntity.status++;
            if(mixerBlockEntity.status >= mixerBlockEntity.max) {
                craft(mixerBlockEntity);
            }
        } else {
            mixerBlockEntity.resetStatus();
        }

    }
}
