package dev.endgame.forge.mixin;

import dev.endgame.items.AbstractGeckoItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(AbstractGeckoItem.class)
public abstract class AbstractForgeGeckoItem extends Item implements GeoItem {
    public AbstractForgeGeckoItem(Properties properties) {
        super(properties);
    }

    @Shadow
    protected abstract GeoItemRenderer<?> getRenderer();

    @Overwrite(remap = false)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return getRenderer();
            }
        });
    }

}
