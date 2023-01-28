package dev.endgame.fabric;

import dev.endgame.Endgame;
import net.fabricmc.api.ClientModInitializer;

public class ClientListener implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Endgame.initClient();
    }
}
