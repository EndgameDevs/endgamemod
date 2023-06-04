package codes.dreaming.endgamemod.client.renderer.tile;

import codes.dreaming.endgamemod.block.entity.MixerBlockEntity;
import codes.dreaming.endgamemod.client.model.tile.MixerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MixerRenderer extends GeoBlockRenderer<MixerBlockEntity> {
    public MixerRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new MixerModel());
    }

    @Override
    public RenderType getRenderType(MixerBlockEntity animatable, float partialTick, PoseStack poseStack,
                                    MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
