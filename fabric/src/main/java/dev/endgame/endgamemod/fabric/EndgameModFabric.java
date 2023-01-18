package dev.endgame.endgamemod.fabric;

import dev.endgame.endgamemod.EndgameMod;
import net.fabricmc.api.ModInitializer;

public class EndgameModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        EndgameMod.init();
    }
}