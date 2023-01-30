package dev.endgame.item;

import dev.architectury.injectables.annotations.PlatformOnly;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractGeckoArmorItem extends ArmorItem implements GeoItem {

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected GeoArmorRenderer<?> renderer;

    public AbstractGeckoArmorItem(ArmorMaterial armorMaterial, EquipmentSlot slot, Properties properties) {
        super(armorMaterial, slot, properties);
    }

    protected abstract GeoArmorRenderer<?> getRenderer();

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
