package com.xumuk.realism.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ListRecipeManager {
	public static final StoneRecipeManager STONE_RECIPES = new StoneRecipeManager();

	public static void init() {
		STONE_RECIPES.putStoneRecipe(new int[][] {
			{1, 0, 0},
			{0, 1, 1},
			{0, 0, 1}
			}, new ItemStack(Items.APPLE));
	}	
}