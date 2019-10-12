package com.xumuk.realism.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;

import com.xumuk.realism.RealismCore;

import net.minecraft.item.ItemStack;

public abstract class BasicRecipeManager<Result, Matrix> {
	private final Map<Result, Matrix> RECIPES = new HashMap();
	private final BiFunction compareFunction;
	private final String name;

	public BasicRecipeManager(BiFunction compareFunction, @Nullable String name) {
		this.compareFunction = compareFunction;
		this.name = name;
		if(name != null) RealismCore.logger.log(Level.INFO, "Init Recipe Managers: " + name);
	}

	public boolean constainsValue(Matrix matrix) {
		return constainsAndGetValue(matrix) != null;
	}

	@Nullable public abstract Result constainsAndGetValue(Matrix matrix);
	protected abstract void putIntegerRecipe(int[][] recipe, ItemStack output);
	
	protected void putRecipe(Matrix recipe, Result output) {
		RECIPES.put(output, recipe);
	}

	public Matrix removeRecipe(Result output) {
		return RECIPES.remove(output);
	}

	public String getName() { return name; }

	public Matrix getRecipe(Result output) {
		return RECIPES.get(output);
	}

	protected Object isRecipeEqual(Matrix matrix, Matrix matrix2) {
		return compareFunction.apply(matrix, matrix2);
	}

	public Map<Result, Matrix> values() {
		return RECIPES;
	}
}