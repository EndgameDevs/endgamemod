package codes.dreaming.endgamemod.registry;

import codes.dreaming.endgamemod.block.entity.MixerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static codes.dreaming.endgamemod.EndgameMod.MODID;
import static codes.dreaming.endgamemod.registry.BlockRegistry.MIXER_BLOCK;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<MixerBlockEntity>> MIXER_BLOCK_ENTITY = BLOCK_ENTITIES.register("mixer", () -> BlockEntityType.Builder.of(MixerBlockEntity::new, MIXER_BLOCK.get()).build(null));
}
