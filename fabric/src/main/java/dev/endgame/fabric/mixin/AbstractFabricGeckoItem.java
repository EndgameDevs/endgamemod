package dev.endgame.fabric.mixin;

import dev.endgame.common.items.AbstractGeckoItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(AbstractGeckoItem.class)
public abstract class AbstractFabricGeckoItem extends Item implements GeoItem {

    @Shadow
    protected GeoItemRenderer<?> renderer;
    protected final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public AbstractFabricGeckoItem(Properties properties) {
        super(properties);
    }

    @Overwrite(remap = false)
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Overwrite(remap = false)
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

}
