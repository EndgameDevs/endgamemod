package codes.dreaming.endgamemod.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static codes.dreaming.endgamemod.EndgameMod.MODID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MIXER = ITEMS.register("mixer", ()->new BlockItem(BlockRegistry.MIXER_BLOCK.get(),new Item.Properties()));
}
