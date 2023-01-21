package dev.endgame.endgamemod.forge.platform;

import dev.endgame.endgamemod.items.AbstractJackInTheBoxItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class JackInTheBoxItem extends AbstractJackInTheBoxItem {

    public JackInTheBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        //Since this is a Fabric only method, we throw an exception because this method should never be called.
        throw new AssertionError();
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private JackInTheBoxRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new JackInTheBoxRenderer();

                return this.renderer;
            }
        });
    }


}
