package net.coalslaw.simpleadditions.datagen;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.block.ModBlocks;
import net.coalslaw.simpleadditions.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> PAINITE_SMELTABLES = List.of(ModBlocks.PAINITE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PAINITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PAINITE.get())
                .unlockedBy(getHasName(ModItems.PAINITE.get()), has(ModItems.PAINITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_APPLE.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', Items.APPLE)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_APPLE.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', Items.APPLE)
                .define('B', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMERALD_APPLE.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', Items.APPLE)
                .define('B', Items.EMERALD)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIND_APPLE.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', Items.APPLE)
                .define('B', Items.WIND_CHARGE)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MYTHICAL_APPLE.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', Items.APPLE)
                .define('B', ModItems.PAINITE.get())
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOUSE_APPLE.get())
                .pattern("CCC")
                .pattern("CAC")
                .pattern("BBB")
                .define('A', Items.APPLE)
                .define('B', Items.OBSIDIAN)
                .define('C', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOD_WRAPPER.get())
                .pattern("ACA")
                .pattern("CBC")
                .pattern("ACA")
                .define('A', Items.STICK)
                .define('B', Items.STRING)
                .define('C', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PAINITE.get(), 9)
                .requires(ModBlocks.PAINITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PAINITE_BLOCK.get()), has(ModBlocks.PAINITE_BLOCK.get())).save(pRecipeOutput);

        oreSmelting(pRecipeOutput, PAINITE_SMELTABLES, RecipeCategory.MISC, ModItems.PAINITE.get(), 5.75f, 200, "painite");
        oreBlasting(pRecipeOutput, PAINITE_SMELTABLES, RecipeCategory.MISC, ModItems.PAINITE.get(), 5.75f, 100, "painite");

    }

    protected static void oreSmelting(
            RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup
    ) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(
            RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup
    ) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput pRecipeOutput,
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, SimpleAdditions.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }
}
