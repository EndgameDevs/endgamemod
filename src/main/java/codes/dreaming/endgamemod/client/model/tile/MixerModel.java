package codes.dreaming.endgamemod.client.model.tile;

import codes.dreaming.endgamemod.block.entity.MixerBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static codes.dreaming.endgamemod.EndgameMod.MODID;

public class MixerModel extends AnimatedGeoModel<MixerBlockEntity> {

    public static final ResourceLocation ANIMATIONS = new ResourceLocation(MODID, "animations/block/mixer.animation.json");
    public static final ResourceLocation MODEL = new ResourceLocation(MODID, "geo/block/mixer.geo.json");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/block/mixer.png");

    @Override
    public ResourceLocation getAnimationResource(MixerBlockEntity animatable) {
        return ANIMATIONS;
    }

    @Override
    public ResourceLocation getModelResource(MixerBlockEntity animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(MixerBlockEntity entity) {
        return TEXTURE;
    }
}
