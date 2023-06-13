package codes.dreaming.endgamemod;

import codes.dreaming.endgamemod.block.MixerMenuScreenHandler;
import codes.dreaming.endgamemod.configscreen.MenuConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import static codes.dreaming.endgamemod.registry.BlockEntityRegistry.BLOCK_ENTITIES;
import static codes.dreaming.endgamemod.registry.BlockRegistry.BLOCKS;
import static codes.dreaming.endgamemod.registry.ItemRegistry.ITEMS;
import static codes.dreaming.endgamemod.registry.SoundRegistry.SOUNDS;

@Mod(EndgameMod.MODID)
public class EndgameMod {
    public static final String MODID = "endgamemod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static MenuType<MixerMenuScreenHandler> MIXER_SCREEN_HANDLER = new MenuType<>(MixerMenuScreenHandler::new);

    public EndgameMod() {
        GeckoLib.initialize();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUNDS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MenuScreens.register(MIXER_SCREEN_HANDLER, MixerScreen::new);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MenuConfig.getConfigBuilder().setParentScreen(parent).build()));
        LOGGER.info("LOADED");
    }
}
