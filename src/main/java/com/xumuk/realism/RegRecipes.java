package com.xumuk.realism;

import com.xumuk.realism.recipes.ListRecipeManager;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.xumuk.realism.recipes.ListRecipeManager.STONE_RECIPES;

import org.apache.logging.log4j.Level;

public class RegRecipes {
	public static void registerRecipes() {
		try {
			ListRecipeManager.registerRecipe(STONE_RECIPES, new int[][] {
				{1, 0, 0, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
				}, new ItemStack(Items.APPLE));
			//Put recipes here!
		}
		finally {
			ListRecipeManager.getRecipesCounter().forEach((string, integer) -> RealismCore.logger.log(Level.INFO, 
					"Register recipes for: " + string + ", " + integer));
		}
	}	
}