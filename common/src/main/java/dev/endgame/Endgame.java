package dev.endgame;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.endgame.block.MixerMenuScreenHandler;
import dev.endgame.client.renderer.block.MixerRenderer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;

import static dev.endgame.registry.BlockEntityRegistry.BLOCK_ENTITIES;
import static dev.endgame.registry.BlockEntityRegistry.MIXER_BLOCK_ENTITY;
import static dev.endgame.registry.BlockRegistry.BLOCKS;
import static dev.endgame.registry.ItemRegistry.ITEMS;
import static dev.endgame.registry.SoundRegistry.SOUNDS;

public class Endgame {
    public static final String MOD_ID = "endgamemod";
    public static MenuType<MixerMenuScreenHandler> MIXER_SCREEN_HANDLER;

    public static void init() {
        BLOCKS.register();
        ITEMS.register();
        SOUNDS.register();
        BLOCK_ENTITIES.register();
        MIXER_SCREEN_HANDLER = new MenuType<>(MixerMenuScreenHandler::new);
    }

    public static void initClient() {
        System.out.println("Hello from client!");
        BlockEntityRendererRegistry.register(MIXER_BLOCK_ENTITY.get(), context -> new MixerRenderer());
        MenuScreens.register(MIXER_SCREEN_HANDLER, MixerScreen::new);
    }
}