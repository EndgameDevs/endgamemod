package dev.endgame;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.endgame.block.MixerBlock;
import dev.endgame.block.entity.MixerBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static dev.endgame.registry.ItemRegistry.ITEMS;
import static dev.endgame.registry.SoundRegistry.SOUNDS;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<Block> MIXER_BLOCK = BLOCKS.register("mixer", MixerBlock::new);

    public static final RegistrySupplier<BlockEntityType<MixerBlockEntity>> MIXER_BLOCK_ENTITY = BLOCK_ENTITIES.register("mixer", () -> BlockEntityType.Builder.of(MixerBlockEntity::new, MIXER_BLOCK.get()).build(null));

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