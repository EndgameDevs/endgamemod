package dev.endgame;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.CreativeTabRegistry.TabSupplier;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.block.MixerBlock;
import dev.endgame.block.entity.MixerBlockEntity;
import dev.endgame.client.renderer.block.MixerRenderer;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    public static final TabSupplier MOD_TAB = CreativeTabRegistry.create(
            new ResourceLocation(MOD_ID, "creative_tab"),
            () -> new ItemStack(Items.NETHER_STAR) // Icon
    );

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<Block> MIXER_BLOCK = BLOCKS.register("mixer", MixerBlock::new);

    public static final RegistrySupplier<BlockEntityType<MixerBlockEntity>> MIXER_BLOCK_ENTITY = BLOCK_ENTITIES.register("mixer", () -> BlockEntityType.Builder.of(MixerBlockEntity::new, MIXER_BLOCK.get()).build(null));

    public static final RegistrySupplier<Item> ExampleAnimatedItem = ITEMS.register("example_animated_item", () -> new ExampleAnimatedItem(new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemHelmet = ITEMS.register("example_animated_armor_helmet", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.HEAD, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemChestplate = ITEMS.register("example_animated_armor_chestplate", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemLeggings = ITEMS.register("example_animated_armor_leggings", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<Item> ExampleAnimatedArmorItemBoots = ITEMS.register("example_animated_armor_boots", () -> new ExampleAnimatedArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.FEET, new Item.Properties().arch$tab(MOD_TAB)));
    public static final RegistrySupplier<SoundEvent> ExampleAnimatedSound = SOUNDS.register("example_animated", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MOD_ID, "example_animated"), 0));

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