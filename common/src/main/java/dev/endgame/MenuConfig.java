package dev.endgame;
import com.mojang.blaze3d.platform.InputConstants;
import me.shedaniel.clothconfig2.api.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
public class MenuConfig {
    public static ConfigBuilder getConfigBuilderWithDemo() {
        ConfigBuilder builder = ConfigBuilder.create().setTitle(Component.translatable("title.endgamemod.config"));
        builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        builder.setGlobalized(true);
        builder.setGlobalizedExpanded(false);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory armor = builder.getOrCreateCategory(Component.translatable("category.endgame.armor"));
        armor.addEntry(entryBuilder.startKeyCodeField(Component.translatable("key.endgame.armor1"), InputConstants.UNKNOWN).setDefaultValue(InputConstants.UNKNOWN).build());
        builder.transparentBackground();
        builder.setSavingRunnable(() -> {
           // TODO NEXT TIME
        });
        return builder;
    }
}
