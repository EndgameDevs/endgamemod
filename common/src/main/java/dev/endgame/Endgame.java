package dev.endgame;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.items.ExampleAnimatedItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class Endgame {
    public static final String MOD_ID = "endgamemod";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<Item> ExampleAnimated = ITEMS.register("example_animated", () -> new ExampleAnimatedItem(new Item.Properties()));
    public static final RegistrySupplier<SoundEvent> ExampleAnimatedSound = SOUNDS.register("example_animated", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MOD_ID, "example_animated"), 0));

    public static void init() {
        ITEMS.register();
        SOUNDS.register();
    }
}