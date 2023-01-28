package dev.endgame.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.endgame.Endgame;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Endgame.MOD_ID)
public class EndgameModForge {
    public EndgameModForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Endgame.MOD_ID, eventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> (DistExecutor.SafeRunnable) () -> eventBus.addListener(this::setupClient));
        Endgame.init();
    }

    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {
        Endgame.initClient();
    }
}
