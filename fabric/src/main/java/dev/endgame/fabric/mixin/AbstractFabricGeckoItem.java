package dev.endgame.fabric.mixin;

import dev.endgame.items.AbstractGeckoItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(AbstractGeckoItem.class)
public abstract class AbstractFabricGeckoItem extends Item implements GeoItem {

    protected final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    @Shadow
    protected abstract GeoItemRenderer<?> getRenderer();

    public AbstractFabricGeckoItem(Properties properties) {
        super(properties);
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
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return getRenderer();
            }
        });
    }

}
