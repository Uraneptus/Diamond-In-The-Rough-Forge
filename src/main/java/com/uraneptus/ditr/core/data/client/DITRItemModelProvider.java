package com.uraneptus.ditr.core.data.client;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static com.uraneptus.ditr.core.data.DITRDatagenUtil.*;

@SuppressWarnings({"unused", "SameParameterValue"})
public class DITRItemModelProvider extends ItemModelProvider {

    public DITRItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DiamondInTheRough.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicBlockItem(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE);
    }

    private void basicBlockItem(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), modBlockLocation(name(blockForItem.get())));
    }

    private void basicItem(Supplier<? extends Item> item) {
        basicItem(item.get());
    }

    private void blockItemWithBlockTexture(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), GENERATED).texture(LAYER0, modBlockLocation(name(blockForItem.get())));
    }

    private void blockItemWithItemTexture(Supplier<? extends Block> blockForItem) {
        basicItem(blockForItem.get().asItem());
    }
}
