package com.xumuk.realism;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.xumuk.realism.utils.SRUtils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RegRecipes {

	private static Map<ItemStack, boolean[][]> RECIPES = new HashMap();

	public static void register() {
		putStoneRecipe(new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } }, new ItemStack(Items.APPLE));
	}

	public static void putStoneRecipe(int[][] recipe, ItemStack output) {
		RECIPES.put(output, parseRecipe(recipe));
	}

	public static void removeStoneRecipe(ItemStack output) {
		RECIPES.remove(output);
	}

	public static boolean[][] getStoneRecipe(ItemStack output) {
		return RECIPES.get(output);
	}

	@Nullable
	private static boolean[][] parseRecipe(int[][] recipe) {
		boolean[][] matrix = new boolean[recipe.length][recipe[0].length];
		for (int x = 0; x < recipe.length; ++x) {
			for (int y = 0; y < recipe[x].length; ++y) { matrix[x][y] = SRUtils.intToBoolean(recipe[x][y]); }
		}
		return matrix;
	}
}