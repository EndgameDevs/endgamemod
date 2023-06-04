package dev.endgame;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.endgame.block.MixerMenuScreenHandler;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class MixerScreen extends AbstractContainerScreen<MixerMenuScreenHandler> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Endgame.MOD_ID, "textures/gui/mixer.png");

    public MixerScreen(MixerMenuScreenHandler abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX= ((imageWidth - font.width(title)) / 5)*4;
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderProcess(poseStack,x,y);
    }

    private void renderProcess(PoseStack poseStack, int x, int y) {
        if(menu.checkCrafting()) {
            blit(poseStack, x + 52, y + 32, 179, 6, 17, menu.progressScaled());
        }
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int i, int j, float f) {
        renderBackground(poseStack);
        super.render(poseStack, i, j, f);
        renderTooltip(poseStack, i, j);
    }
}
