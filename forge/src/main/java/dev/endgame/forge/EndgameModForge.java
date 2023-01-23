package dev.endgame.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.endgame.Endgame;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Endgame.MOD_ID)
public class EndgameModForge {
    public EndgameModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Endgame.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Endgame.init();
    }
}
