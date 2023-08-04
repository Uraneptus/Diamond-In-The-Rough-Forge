package com.uraneptus.ditr.core.data.server.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class DITRLootTablesProvider extends LootTableProvider {

    public DITRLootTablesProvider(PackOutput pOutput) {
        super(pOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(DITRBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }
}
