package dev.endgame.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.endgame.MenuConfig;

public class Menu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> MenuConfig.getConfigBuilderWithDemo().setParentScreen(screen).build();
    }
}
