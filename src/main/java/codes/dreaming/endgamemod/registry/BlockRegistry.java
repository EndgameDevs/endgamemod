package codes.dreaming.endgamemod.registry;

import codes.dreaming.endgamemod.block.MixerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static codes.dreaming.endgamemod.EndgameMod.MODID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> MIXER_BLOCK = BLOCKS.register("mixer", MixerBlock::new);
}
