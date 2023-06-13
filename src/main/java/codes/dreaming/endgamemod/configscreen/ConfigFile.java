package codes.dreaming.endgamemod.configscreen;

import codes.dreaming.endgamemod.EndgameMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = EndgameMod.MODID)
public class ConfigFile implements ConfigData {
    public String keyarmor = "key.keyboard.r";
    public String keyblock = "key.keyboard.o";

}