package dev.endgame.endgamemod;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.endgame.endgamemod.items.AbstractJackInTheBoxItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class EndgameModExpectPlatform {
    @ExpectPlatform
    public static <T extends AbstractJackInTheBoxItem> T constructJackInTheBoxItemType(Item.Properties properties) {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
