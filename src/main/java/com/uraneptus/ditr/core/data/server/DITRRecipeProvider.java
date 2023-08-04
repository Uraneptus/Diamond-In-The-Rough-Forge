package com.uraneptus.ditr.core.data.server;

import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class DITRRecipeProvider extends RecipeProvider {

    public DITRRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreCookingRecipes(DITRBlocksItems.OBSIDIAN_DIAMOND_ORE_ITEM, () -> Items.DIAMOND, 1.0F, consumer);
    }

    private static void oreCookingRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, Consumer<FinishedRecipe> consumer) {
        String resultName = getItemName(result.get());
        String ingredientName = getItemName(ingredient.get());

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), RecipeCategory.MISC, result.get(), experience, 200)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, resultName + "_from_smelting" + "_" + ingredientName);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient.get()), RecipeCategory.MISC, result.get(), experience, 100)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, resultName + "_from_blasting" + "_" + ingredientName);
    }

}
