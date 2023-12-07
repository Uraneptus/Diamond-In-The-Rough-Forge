package com.uraneptus.ditr;

import com.mojang.logging.LogUtils;
import com.uraneptus.ditr.core.data.client.DITRBlockStateProvider;
import com.uraneptus.ditr.core.data.client.DITRItemModelProvider;
import com.uraneptus.ditr.core.data.client.DITRLangProvider;
import com.uraneptus.ditr.core.data.server.DITRRecipeProvider;
import com.uraneptus.ditr.core.data.server.loot.DITRLootTablesProvider;
import com.uraneptus.ditr.core.data.server.tags.DITRBlockTagsProvider;
import com.uraneptus.ditr.core.data.server.tags.DITRItemTagsProvider;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(DiamondInTheRough.MOD_ID)
public class DiamondInTheRough {
    public static final String MOD_ID = "ditr";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final GameRules.Key<GameRules.IntegerValue> DIAMOND_CONVERSION_PERCENTAGE = GameRules.register("diamondConversionPercentage", GameRules.Category.MOBS, GameRules.IntegerValue.create(40));

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(DiamondInTheRough.MOD_ID, path);
    }

    public DiamondInTheRough(IEventBus bus) {
        bus.addListener(this::gatherData);

        DITRBlocksItems.BLOCKS.register(bus);
        DITRBlocksItems.ITEMS.register(bus);

        //NeoForge.EVENT_BUS.register(this);
    }

    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new DITRBlockStateProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new DITRItemModelProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new DITRLangProvider(packOutput));

        DITRBlockTagsProvider blockTagProvider = new DITRBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        generator.addProvider(includeServer, blockTagProvider);
        generator.addProvider(includeServer, new DITRItemTagsProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), fileHelper));
        generator.addProvider(includeServer, new DITRLootTablesProvider(packOutput));
        generator.addProvider(includeServer, new DITRRecipeProvider(packOutput, lookupProvider));
    }
}