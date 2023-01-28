package dev.endgame.client.renderer.block;

import dev.endgame.block.entity.MixerBlockEntity;
import dev.endgame.client.model.block.MixerModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MixerRenderer extends GeoBlockRenderer<MixerBlockEntity> {

    public MixerRenderer() {
        super(new MixerModel());
    }
}
