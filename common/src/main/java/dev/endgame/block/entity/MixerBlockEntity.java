package dev.endgame.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import static dev.endgame.Endgame.MIXER_BLOCK_ENTITY;

public class MixerBlockEntity extends BlockEntity implements GeoBlockEntity {
    private static final RawAnimation DEFAULT_ANIM = RawAnimation.begin();
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
}
