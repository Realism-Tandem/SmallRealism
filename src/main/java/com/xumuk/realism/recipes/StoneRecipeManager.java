package com.xumuk.realism.recipes;

import java.util.Arrays;

import javax.annotation.Nullable;

import com.xumuk.realism.utils.SRUtils;

import net.minecraft.item.ItemStack;

public class StoneRecipeManager extends BasicRecipeManager<ItemStack, boolean[][]>{

	public StoneRecipeManager() {
		super((rec1, rec2) -> Arrays.deepEquals((boolean[][])rec1, (boolean[][])rec2));
	}
	
	@Override
	public ItemStack constainsAndGetValue(boolean[][] matrix) {
		return values()
				.entrySet()
				.stream()
				.filter(e -> (boolean)isRecipeEqual(e.getValue(), matrix))
				.findFirst()
				.orElse(null)
				.getKey();
	}
	
	public void putStoneRecipe(int[][] recipe, ItemStack output) {
		putRecipe(parseRecipe(recipe), output);
	}
	
	@Nullable
	private boolean[][] parseRecipe(int[][] recipe) {
		boolean[][] matrix = new boolean[recipe.length][recipe[0].length];
		for (int x = 0; x < recipe.length; ++x) {
			for (int y = 0; y < recipe[x].length; ++y) { matrix[x][y] = SRUtils.intToBoolean(recipe[x][y]); }
		}
		return matrix;
	}
}