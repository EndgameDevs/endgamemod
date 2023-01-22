package dev.endgame.common.items;

import dev.endgame.common.Endgame;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.ClientUtils;

public class ExampleAnimatedItem extends AbstractGeckoItem {
    private static final RawAnimation POPUP_ANIM = RawAnimation.begin().thenPlay("use.popup");

    public ExampleAnimatedItem(Properties properties) {
        super(properties, new JackInTheBoxRenderer());
    }

    // Let's add our animation controller
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "popup_controller", 20, state -> PlayState.STOP)
                .triggerableAnim("box_open", POPUP_ANIM)
                // We've marked the "box_open" animation as being triggerable from the server
                .setSoundKeyframeHandler(event -> {
                    // Use helper method to avoid client-code in common class
                    Player player = ClientUtils.getClientPlayer();

                    if (player != null) {
                        player.playSound(Endgame.JackInTheBoxSound.get(), 1, 1);
                    }
                }));
    }

    // Let's handle our use method so that we activate the animation when right-clicking while holding the box
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverLevel)
            triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), "popup_controller", "box_open");

        return super.use(level, player, hand);
    }
}
