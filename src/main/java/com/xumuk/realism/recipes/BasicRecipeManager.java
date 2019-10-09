package com.xumuk.realism.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public abstract class BasicRecipeManager<Result, Matrix> {
	private Map<Result, Matrix> RECIPES = new HashMap();
	private final BiFunction compareFunction;

	public BasicRecipeManager(BiFunction compareFunction) {
		this.compareFunction = compareFunction;
	}

	public boolean constainsValue(Matrix matrix) {
		return constainsAndGetValue(matrix) != null;
	}

	@Nullable public abstract Result constainsAndGetValue(Matrix matrix);
	
	public void putRecipe(Matrix recipe, Result output) {
		RECIPES.put(output, recipe);
	}

	public Matrix removeRecipe(ItemStack output) {
		return RECIPES.remove(output);
	}

	public Matrix getRecipe(ItemStack output) {
		return RECIPES.get(output);
	}

	protected Object isRecipeEqual(Matrix matrix, Matrix matrix2) {
		return compareFunction.apply(matrix, matrix2);
	}

	public Map<Result, Matrix> values() {
		return RECIPES;
	}
}