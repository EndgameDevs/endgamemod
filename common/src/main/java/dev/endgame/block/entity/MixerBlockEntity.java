package dev.endgame.block.entity;

import dev.endgame.MenuScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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

public class MixerBlockEntity extends BlockEntity implements GeoBlockEntity, MenuProvider {
    private static final RawAnimation DEFAULT_ANIM = RawAnimation.begin().thenPlayAndHold("animation.model.foodinside");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MixerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MIXER_BLOCK_ENTITY.get(), blockPos, blockState);
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
        return new MenuScreenHandler(i, inventory);
    }
}
