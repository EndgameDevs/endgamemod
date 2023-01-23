package dev.endgame;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.items.ExampleAnimatedItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    //Register
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    //Supplier
    public static final RegistrySupplier<Item> ExampleAnimated = ITEMS.register("example_animated", () -> new ExampleAnimatedItem(new Item.Properties()));

    public static final CreativeTabRegistry.TabSupplier TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "end"),() -> new ItemStack(ExampleAnimated.get()));
    public static final RegistrySupplier<SoundEvent> ExampleAnimatedSound = SOUNDS.register("example_animated", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MOD_ID, "example_animated"), 0));
    public static final RegistrySupplier<Block> BLENDER = register("blender", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).friction(0.5f).strength(1f,3f).dynamicShape().noOcclusion()), new Item.Properties().arch$tab(TAB));
    public static final RegistrySupplier<Block> COMPRESSOR = register("compressor",() -> new Block(BlockBehaviour.Properties.of(Material.STONE).friction(0.5f).strength(1f,3f).dynamicShape().noOcclusion()), new Item.Properties().arch$tab(TAB));
    public static void init() {
        BLOCKS.register();
        ITEMS.register();
        SOUNDS.register();
    }

    public static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> sup, Item.Properties prop){
        RegistrySupplier<T> b= BLOCKS.register(id,sup);
        ITEMS.register(id,()->new BlockItem(b.get(),prop));
        return b;
    }
}