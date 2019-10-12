package com.xumuk.realism.utils;

import java.util.Arrays;

import com.xumuk.realism.recipes.ListRecipeManager;

import net.minecraft.item.ItemStack;

public class CraftMatrixBool {

	public final static int DEFAULT_WIDTH = 5;
	public final static int DEFAULT_HEIGHT = 5;

	private final boolean[][] matrix;
	private final int width;
	private final int height;

	public CraftMatrixBool() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public CraftMatrixBool(int width, int height) {
		this.width = width;
		this.height = height;
		matrix = new boolean[this.width][this.height];
		removeAll();
	}

	public ItemStack set(int x, int y) {
		matrix[x][y] = false;
		return getRecipeResult();
	}

	public ItemStack remove(int x, int y) {
		matrix[x][y] = true;
		return getRecipeResult();
	}

	public void removeAll() {
		for (int x = 0; x < matrix.length; ++x) for (int y = 0; y < matrix[x].length; ++y) matrix[x][y] = true;
	}

	public boolean isEqual(CraftMatrixBool other) {
		if (other.width != this.width || other.height != this.height) return false;
		return Arrays.deepEquals(other.matrix, this.matrix);
	}

	private ItemStack getRecipeResult() {
		return ListRecipeManager.STONE_RECIPES.constainsAndGetValue(matrix);
	}
	
	public void setAll() {
		for (int x = 0; x < matrix.length; ++x) for (int y = 0; y < matrix[x].length; ++y) matrix[x][y] = false;
	}
	
	public void refreshMatrix() {
		removeAll();
	}

	public int getWidth() { return width; }

	public int getHeight() { return height; }

	public boolean[][] getMatrix() { return matrix; }
}