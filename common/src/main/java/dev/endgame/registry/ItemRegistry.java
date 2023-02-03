package dev.endgame.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.item.ExampleAnimatedArmorItem;
import dev.endgame.item.ExampleAnimatedItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import static dev.endgame.Endgame.MOD_ID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final CreativeTabRegistry.TabSupplier MOD_TAB = CreativeTabRegistry.create(
            new ResourceLocation(MOD_ID, "creative_tab"),
            () -> new ItemStack(Items.NETHER_STAR) // Icon
    );
    public static final RegistrySupplier<Item> EXAMPLE_ANIMATED_ITEM = ITEMS.register("example_animated_item", () -> new ExampleAnimatedItem(new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> EXAMPLE_ANIMATED_ARMOR_HELMET = ITEMS.register("example_animated_armor_helmet", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.HEAD, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> EXAMPLE_ANIMATED_ARMOR_CHESTPLATE = ITEMS.register("example_animated_armor_chestplate", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> EXAMPLE_ANIMATED_ARMOR_LEGGINGS = ITEMS.register("example_animated_armor_leggings", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> EXAMPLE_ANIMATED_ARMOR_BOOTS = ITEMS.register("example_animated_armor_boots", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.FEET, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> MIXER =ITEMS.register("mixer", ()->new BlockItem(BlockRegistry.MIXER_BLOCK.get(),new Item.Properties().arch$tab(MOD_TAB)));
}
