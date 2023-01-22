package dev.endgame.fabric;

import dev.endgame.common.EndgameMod;
import net.fabricmc.api.ModInitializer;

public class EndgameModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        EndgameMod.init();
    }
}