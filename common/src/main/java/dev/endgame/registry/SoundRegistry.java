package dev.endgame.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static dev.endgame.Endgame.MOD_ID;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);
    public static final RegistrySupplier<SoundEvent> ExampleAnimatedSound = SOUNDS.register("example_animated", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MOD_ID, "example_animated"), 0));

}
