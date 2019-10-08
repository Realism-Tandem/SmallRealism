package com.xumuk.realism.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class CraftMatrixBool {

	public static final int DEFAULT_WIDTH = 5;
	public static final int DEFAULT_HEIGHT = 5;

	private final boolean[][] matrix;
	private final boolean out = false;
	private final int width;
	private final int height;
	private List<CraftRecipeBool> recipes = new ArrayList();

	public CraftMatrixBool() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public CraftMatrixBool(int width, int height) {
		this.width = width;
		this.height = height;
		matrix = new boolean[this.width][this.height];
		setAll();
	}

	public void setAll() {
		for (int x = 0; x < matrix[width].length; ++x) for (int y = 0; y < matrix[x].length; ++y) matrix[x][y] = true;
	}

	public void set(int x, int y) {
		matrix[x][y] = true;
	}

	public void remove(int x, int y) {
		matrix[x][y] = false;
	}

	public void removeAll() {
		for (int x = 0; x < matrix[width].length; ++x) for (int y = 0; y < matrix[x].length; ++y) matrix[x][y] = false;
	}

	public boolean isEqual(CraftMatrixBool other) {
		if (other.width != this.width || other.height != this.height) return false;
		for (int i = 0; i < width * height; i++) if (other.matrix[i] != this.matrix[i]) return false;
		return true;
	}
	
	public void setRecipe(CraftRecipeBool recipe) {
		recipes.add(recipe);
	}
	
	public void removeRecipe(CraftRecipeBool recipe) {
		recipes.remove(recipe);
	}
	
	public ItemStack getRecipeResult() {
		//TODO: create matches recipes
		return ItemStack.EMPTY;
	}

	public int getWidth() { return width; }

	public int getHeight() { return height; }

	public boolean isHasOut() { return out; }

	public boolean[][] getMatrix() { return matrix; }
}