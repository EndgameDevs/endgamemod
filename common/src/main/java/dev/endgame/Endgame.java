package dev.endgame;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.CreativeTabRegistry.TabSupplier;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.items.ExampleAnimatedArmorItem;
import dev.endgame.items.ExampleAnimatedItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    public static final TabSupplier MOD_TAB = CreativeTabRegistry.create(
            new ResourceLocation(MOD_ID, "creative_tab"),
            () -> new ItemStack(Items.NETHER_STAR) // Icon
    );

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<Item> ExampleAnimatedItem = ITEMS.register("example_animated_item", () -> new ExampleAnimatedItem(new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemHelmet = ITEMS.register("example_animated_armor_helmet", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.HEAD, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemChestplate = ITEMS.register("example_animated_armor_chestplate", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemLeggings = ITEMS.register("example_animated_armor_leggings", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemBoots = ITEMS.register("example_animated_armor_boots", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.FEET, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<SoundEvent> ExampleAnimatedSound = SOUNDS.register("example_animated", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MOD_ID, "example_animated"), 0));

    public static void init() {
        ITEMS.register();
        SOUNDS.register();
    }
}