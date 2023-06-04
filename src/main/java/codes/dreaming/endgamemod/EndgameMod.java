package codes.dreaming.endgamemod;

import codes.dreaming.endgamemod.block.MixerMenuScreenHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
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

        MinecraftForge.EVENT_BUS.register(this);
    }
}
