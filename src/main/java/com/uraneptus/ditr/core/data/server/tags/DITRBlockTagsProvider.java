package com.uraneptus.ditr.core.data.server.tags;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.other.DITRBlockTags;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DITRBlockTagsProvider extends BlockTagsProvider {

    public DITRBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DiamondInTheRough.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.DIAMOND_ORES).add(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE.get());
        tag(BlockTags.DRAGON_IMMUNE).add(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE.get());
        tag(DITRBlockTags.OBSIDIAN_DIAMOND_ORE_REPLACEABLES).add(Blocks.OBSIDIAN);
    }
}
