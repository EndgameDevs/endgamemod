package dev.endgame.endgamemod.forge;

import dev.endgame.endgamemod.forge.platform.JackInTheBoxItem;
import dev.endgame.endgamemod.items.AbstractJackInTheBoxItem;
import net.minecraft.world.item.Item;

public class EndgameModExpectPlatformImpl {
    public static <T extends AbstractJackInTheBoxItem> T constructJackInTheBoxItemType(Item.Properties properties) {
        return (T) new JackInTheBoxItem(properties);
    }
}
