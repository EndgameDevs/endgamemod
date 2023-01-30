package dev.endgame.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.block.MixerBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

import static dev.endgame.Endgame.MOD_ID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> MIXER_BLOCK = BLOCKS.register("mixer", MixerBlock::new);

}
