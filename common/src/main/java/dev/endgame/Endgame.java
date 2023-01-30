package dev.endgame;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.endgame.client.renderer.block.MixerRenderer;

import static dev.endgame.registry.BlockEntityRegistry.BLOCK_ENTITIES;
import static dev.endgame.registry.BlockEntityRegistry.MIXER_BLOCK_ENTITY;
import static dev.endgame.registry.BlockRegistry.BLOCKS;
import static dev.endgame.registry.ItemRegistry.ITEMS;
import static dev.endgame.registry.SoundRegistry.SOUNDS;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    public static void init() {
        ITEMS.register();
        SOUNDS.register();
        BLOCKS.register();
        BLOCK_ENTITIES.register();
    }

    public static void initClient() {
        System.out.println("Hello from client!");
        BlockEntityRendererRegistry.register(MIXER_BLOCK_ENTITY.get(), context -> new MixerRenderer());
    }
}