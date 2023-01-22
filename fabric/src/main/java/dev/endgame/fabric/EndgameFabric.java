package dev.endgame.fabric;

import dev.endgame.common.Endgame;
import net.fabricmc.api.ModInitializer;

public class EndgameFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Endgame.init();
    }
}