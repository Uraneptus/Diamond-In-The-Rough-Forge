package com.uraneptus.ditr.core.data.client;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

@SuppressWarnings("SameParameterValue")
public class DITRBlockStateProvider extends BlockStateProvider {
    public DITRBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DiamondInTheRough.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE.get());
    }


}
