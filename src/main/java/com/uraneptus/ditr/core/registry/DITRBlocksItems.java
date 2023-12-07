package com.uraneptus.ditr.core.registry;

import com.uraneptus.ditr.DiamondInTheRough;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;

public class DITRBlocksItems {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DiamondInTheRough.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DiamondInTheRough.MOD_ID);

    public static final DeferredBlock<Block> OBSIDIAN_DIAMOND_ORE = BLOCKS.register("obsidian_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).strength(52.0F, 1200.0F).pushReaction(PushReaction.NORMAL), UniformInt.of(3, 7)));
    public static final DeferredItem<BlockItem> OBSIDIAN_DIAMOND_ORE_ITEM = ITEMS.registerSimpleBlockItem(OBSIDIAN_DIAMOND_ORE);

}
