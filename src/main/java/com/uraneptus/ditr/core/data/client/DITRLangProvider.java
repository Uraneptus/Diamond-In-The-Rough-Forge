package com.uraneptus.ditr.core.data.client;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DITRLangProvider extends LanguageProvider {

    public DITRLangProvider(PackOutput output) {
        super(output, DiamondInTheRough.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE, "Obsidian Diamond Ore");
        add("gamerule.diamondConversionPercentage", "Obsidian to diamond conversion chance");
    }

}