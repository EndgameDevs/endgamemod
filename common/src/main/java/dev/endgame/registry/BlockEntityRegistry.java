package dev.endgame.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.block.entity.MixerBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static dev.endgame.Endgame.MOD_ID;
import static dev.endgame.registry.BlockRegistry.MIXER_BLOCK;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<MixerBlockEntity>> MIXER_BLOCK_ENTITY = BLOCK_ENTITIES.register("mixer", () -> BlockEntityType.Builder.of(MixerBlockEntity::new, MIXER_BLOCK.get()).build(null));
}
