package codes.dreaming.endgamemod.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static codes.dreaming.endgamemod.EndgameMod.MODID;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final RegistryObject<SoundEvent> EXAMPLE_ANIMATED_SOUND = SOUNDS.register("example_animated", () -> new SoundEvent(new ResourceLocation(MODID, "example_animated")));
}
