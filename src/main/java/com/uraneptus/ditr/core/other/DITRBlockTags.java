package com.uraneptus.ditr.core.other;

import com.uraneptus.ditr.DiamondInTheRough;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DITRBlockTags {
    public static final TagKey<Block> OBSIDIAN_DIAMOND_ORE_REPLACEABLES = TagKey.create(Registries.BLOCK, DiamondInTheRough.modPrefix("obsidian_diamond_ore_replaceables"));
    public static final TagKey<Block> DRAGON_MADE_ORES = TagKey.create(Registries.BLOCK, DiamondInTheRough.modPrefix("dragon_made_ores"));
}