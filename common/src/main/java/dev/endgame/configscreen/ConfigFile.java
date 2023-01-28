package dev.endgame.configscreen;

import dev.endgame.Endgame;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = Endgame.MOD_ID)
public class ConfigFile implements ConfigData {
    public String keyarmor = "key.keyboard.r";
    public String keyblock = "key.keyboard.o";

}
