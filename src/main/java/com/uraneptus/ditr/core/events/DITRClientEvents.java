package com.uraneptus.ditr.core.events;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DiamondInTheRough.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DITRClientEvents {

    @SubscribeEvent
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if (tabKey == CreativeModeTabs.NATURAL_BLOCKS) {
            addAfter(Items.DEEPSLATE_DIAMOND_ORE, DITRBlocksItems.OBSIDIAN_DIAMOND_ORE_ITEM, event);
        }
    }

    private static void addAfter(Item after, Supplier<? extends ItemLike> inputItem, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(after.getDefaultInstance(), inputItem.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
