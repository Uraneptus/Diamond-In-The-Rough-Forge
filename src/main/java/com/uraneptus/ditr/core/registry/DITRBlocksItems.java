package com.uraneptus.ditr.core.registry;

import com.uraneptus.ditr.DiamondInTheRough;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = DiamondInTheRough.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DITRBlocksItems {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DiamondInTheRough.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DiamondInTheRough.MOD_ID);

    public static final RegistryObject<Block> OBSIDIAN_DIAMOND_ORE = BLOCKS.register("obsidian_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).strength(52.0F, 1200.0F).pushReaction(PushReaction.NORMAL), UniformInt.of(3, 7)));
    public static final RegistryObject<Item> OBSIDIAN_DIAMOND_ORE_ITEM = ITEMS.register("obsidian_diamond_ore", () -> new BlockItem(OBSIDIAN_DIAMOND_ORE.get(), new Item.Properties()));

}
