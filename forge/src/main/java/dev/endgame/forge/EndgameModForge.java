package dev.endgame.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.endgame.common.EndgameMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EndgameMod.MOD_ID)
public class EndgameModForge {
    public EndgameModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(EndgameMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        EndgameMod.init();
    }
}
