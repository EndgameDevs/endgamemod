package codes.dreaming.endgamemod.configscreen;

import com.mojang.blaze3d.platform.InputConstants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class MenuConfig {
    public static String keyarmor;
    public static String keyblock;
    public static ConfigHolder<ConfigFile> ch = AutoConfig.register(ConfigFile.class, GsonConfigSerializer::new);

    public static ConfigBuilder getConfigBuilder() {
        ConfigBuilder builder = ConfigBuilder.create().setTitle(Component.translatable("title.endgamemod.config"));
        ConfigFile f;
        ch.load();
        f = ch.getConfig();
        builder.setSavingRunnable(() -> {
            f.keyarmor = keyarmor;
            f.keyblock = keyblock;
            ch.setConfig(f);
            ch.save();
        });
        ConfigCategory armor = builder.getOrCreateCategory(Component.translatable("category.endgame.armor"));
        ConfigCategory blocks = builder.getOrCreateCategory(Component.translatable("category.endgame.block"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        armor.addEntry(entryBuilder.startKeyCodeField(Component.translatable("key.endgame.armor1"), InputConstants.getKey(f.keyarmor)).setDefaultValue(InputConstants.getKey(new ConfigFile().keyarmor)).setKeySaveConsumer(armor1k -> keyarmor = String.valueOf(armor1k)).build());
        blocks.addEntry(entryBuilder.startKeyCodeField(Component.translatable("key.endgame.block1"), InputConstants.getKey(f.keyblock)).setDefaultValue(InputConstants.getKey(new ConfigFile().keyblock)).setKeySaveConsumer(block1k -> keyblock = String.valueOf(block1k)).build());
        return builder;
    }
}