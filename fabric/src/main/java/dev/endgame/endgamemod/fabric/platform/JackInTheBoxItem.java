package dev.endgame.endgamemod.fabric.platform;

import dev.endgame.endgamemod.items.AbstractJackInTheBoxItem;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.function.Supplier;

public class JackInTheBoxItem extends AbstractJackInTheBoxItem {
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public JackInTheBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

}
