package dev.endgame.fabric;

import dev.endgame.Endgame;
import net.fabricmc.api.ModInitializer;
public class EndgameFabric implements ModInitializer {
    @Override
        public void onInitialize() {
        Endgame.init();
    }
}