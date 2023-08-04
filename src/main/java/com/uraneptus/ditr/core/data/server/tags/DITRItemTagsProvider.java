package com.uraneptus.ditr.core.data.server.tags;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DITRItemTagsProvider extends ItemTagsProvider {

    public DITRItemTagsProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> pProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, pProvider, blockProvider, DiamondInTheRough.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.DIAMOND_ORES).add(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE_ITEM.get());
    }
}
