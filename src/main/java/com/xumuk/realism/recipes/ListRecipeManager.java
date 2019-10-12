package com.xumuk.realism.recipes;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class ListRecipeManager {
	public static StoneRecipeManager STONE_RECIPES = new StoneRecipeManager();
	private final static HashMap<String, Integer> recipesCounter = new HashMap();

	public static void initManagers() {
		recipesCounter.put(STONE_RECIPES.getName(), 0);
	}
	
	public static void registerRecipe(BasicRecipeManager manager, int[][] recipe, ItemStack output) {
		manager.putIntegerRecipe(recipe, output);
		recipesCounter.put(manager.getName(), recipesCounter.get(manager.getName())+1);
	}
	
	public static HashMap<String, Integer> getRecipesCounter() { return recipesCounter; }
}