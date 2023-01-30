package dev.endgame;

import static dev.endgame.registry.ItemRegistry.ITEMS;
import static dev.endgame.registry.SoundRegistry.SOUNDS;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    public static void init() {
        ITEMS.register();
        SOUNDS.register();
    }
}