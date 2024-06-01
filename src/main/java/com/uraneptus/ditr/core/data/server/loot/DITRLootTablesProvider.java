package com.uraneptus.ditr.core.data.server.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DITRLootTablesProvider extends LootTableProvider {

    public DITRLootTablesProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(pOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(DITRBlockLoot::new, LootContextParamSets.BLOCK)
                ), lookupProvider);
    }
}
