package dev.endgame.endgamemod;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.endgame.endgamemod.items.AbstractJackInTheBoxItem;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class EndgameMod {
    public static final String MOD_ID = "endgamemod";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<Item> JackInTheBoxItem = ITEMS.register("jack_in_the_box", () -> EndgameModExpectPlatform.constructJackInTheBoxItemType(new Item.Properties()));
    public static final RegistrySupplier<SoundEvent> JackInTheBoxSound = SOUNDS.register("jack_in_the_box", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MOD_ID, "jack_in_the_box_music"),0));

    public static void init() {
        ITEMS.register();
        SOUNDS.register();
    }
}