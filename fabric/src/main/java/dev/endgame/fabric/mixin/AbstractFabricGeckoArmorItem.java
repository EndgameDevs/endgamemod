package dev.endgame.fabric.mixin;

import dev.endgame.items.AbstractGeckoArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(AbstractGeckoArmorItem.class)
public abstract class AbstractFabricGeckoArmorItem extends ArmorItem implements GeoItem {

    protected final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    @Shadow(remap = false)
    protected abstract GeoArmorRenderer<?> getRenderer();

    public AbstractFabricGeckoArmorItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    /**
     * @author raccoman
     * @reason createRendered is needed only for fabric
     */
    @Overwrite(remap = false)
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    /**
     * @author raccoman
     * @reason createRendered is needed only for fabric
     */
    @Overwrite(remap = false)
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            @Override
            public @NotNull HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<LivingEntity> original) {
                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                getRenderer().prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return getRenderer();
            }
        });
    }

}
