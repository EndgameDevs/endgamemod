package dev.endgame.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.endgame.Endgame;
import dev.endgame.configscreen.MenuConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Endgame.MOD_ID)
public class EndgameModForge {
    public EndgameModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Endgame.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Endgame.init();
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MenuConfig.getConfigBuilder().setParentScreen(parent).build()));
    }
}
