package dev.endgame.common.items;

import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractGeckoItem extends Item implements GeoItem {

    protected GeoItemRenderer<?> renderer;
    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AbstractGeckoItem(Properties properties, GeoItemRenderer<?> renderer) {
        super(properties);
        this.renderer = renderer;

        // Register our item as server-side handled.
        // This enables both animation data syncing and server-side animation triggering
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void initializeClient(Consumer<?> consumer) {
        throw new AssertionError("This method should be overridden by the platform.");
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        throw new RuntimeException("This method should be overridden by the platform.");
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        throw new IllegalStateException("This method should be overridden by the platform.");
    }

}
