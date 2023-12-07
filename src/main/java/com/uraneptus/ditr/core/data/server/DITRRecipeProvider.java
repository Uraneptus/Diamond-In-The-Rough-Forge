package com.uraneptus.ditr.core.data.server;

import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class DITRRecipeProvider extends RecipeProvider {

    public DITRRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        oreCookingRecipes(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE_ITEM, () -> Items.DIAMOND, 1.0F, output);
    }

    private static void oreCookingRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, RecipeOutput output) {
        String resultName = getItemName(result.get());
        String ingredientName = getItemName(ingredient.get());

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), RecipeCategory.MISC, result.get(), experience, 200)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(output, resultName + "_from_smelting" + "_" + ingredientName);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient.get()), RecipeCategory.MISC, result.get(), experience, 100)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(output, resultName + "_from_blasting" + "_" + ingredientName);
    }
}
