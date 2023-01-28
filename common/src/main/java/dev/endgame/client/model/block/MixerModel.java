package dev.endgame.client.model.block;

import dev.endgame.Endgame;
import dev.endgame.block.entity.MixerBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedGeoModel;

public class MixerModel extends DefaultedGeoModel<MixerBlockEntity> {
    public MixerModel() {
        super(new ResourceLocation(Endgame.MOD_ID, "mixer"));
    }

    @Override
    public ResourceLocation getAnimationResource(MixerBlockEntity animatable) {
        return super.getAnimationResource(animatable);
    }

    @Override
    protected String subtype() {
        return "block";
    }

    @Override
    public ResourceLocation getModelResource(MixerBlockEntity animatable) {
        return super.getModelResource(animatable);
    }

    @Override
    public ResourceLocation getTextureResource(MixerBlockEntity animatable) {
        return super.getTextureResource(animatable);
    }

    @Override
    public RenderType getRenderType(MixerBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}
