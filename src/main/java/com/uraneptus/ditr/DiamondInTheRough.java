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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(DiamondInTheRough.MOD_ID)
@Mod.EventBusSubscriber(modid = DiamondInTheRough.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DiamondInTheRough {
    public static final String MOD_ID = "ditr";

    public static final GameRules.Key<GameRules.IntegerValue> DIAMOND_CONVERSION_PERCENTAGE = GameRules.register("diamondConversionPercentage", GameRules.Category.MOBS, GameRules.IntegerValue.create(40));

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(DiamondInTheRough.MOD_ID, path);
    }

    public DiamondInTheRough() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);

        DITRBlocksItems.BLOCKS.register(bus);
        DITRBlocksItems.ITEMS.register(bus);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
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
        generator.addProvider(includeServer, new DITRRecipeProvider(packOutput));
    }
}