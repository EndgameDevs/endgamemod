package dev.endgame.common.items;

import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractGeckoItem extends Item implements GeoItem {
    public AbstractGeckoItem(Properties properties, GeoItemRenderer<?> renderer) {
        super(properties);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        throw new IllegalStateException("This method should be overridden by the platform.");
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        throw new IllegalStateException("This method should be overridden by the platform.");
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        throw new IllegalStateException("This method should be overridden by the platform.");
    }

}
