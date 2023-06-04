package codes.dreaming.endgamemod;

import codes.dreaming.endgamemod.client.renderer.tile.MixerRenderer;
import codes.dreaming.endgamemod.registry.BlockEntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static codes.dreaming.endgamemod.EndgameMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.MIXER_BLOCK_ENTITY.get(), MixerRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
    }

    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {

    }
}
