package dev.endgame.item;

import dev.architectury.injectables.annotations.PlatformOnly;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractGeckoItem extends Item implements GeoItem {

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected GeoItemRenderer<?> renderer;

    public AbstractGeckoItem(Properties properties) {
        super(properties);
    }

    protected abstract GeoItemRenderer<?> getRenderer();

    protected void registerSyncedAnimatable() {
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Override
    @PlatformOnly("fabric")
    public void createRenderer(Consumer<Object> consumer) {
        throw new AssertionError("This method should be overridden by the platform.");
    }

    @PlatformOnly("forge")
    @SuppressWarnings("unused")
    public void initializeClient(Consumer<Object> consumer) {
        throw new AssertionError("This method should be overridden by the platform.");
    }

    @Override
    @PlatformOnly("fabric")
    public Supplier<Object> getRenderProvider() {
        throw new AssertionError("This method should be overridden by the platform.");
    }

}
