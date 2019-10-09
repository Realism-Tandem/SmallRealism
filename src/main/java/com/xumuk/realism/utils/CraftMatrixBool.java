package com.xumuk.realism.utils;

import com.xumuk.realism.recipes.ListRecipeManager;

import net.minecraft.item.ItemStack;

public class CraftMatrixBool {

	public static final int DEFAULT_WIDTH = 5;
	public static final int DEFAULT_HEIGHT = 5;

	private final boolean[][] matrix;
	private boolean dirty = false;
	private final int width;
	private final int height;

	public CraftMatrixBool() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public CraftMatrixBool(int width, int height) {
		this.width = width;
		this.height = height;
		matrix = new boolean[this.width][this.height];
		refreshMatrix();
	}

	public ItemStack set(int x, int y) {
		matrix[x][y] = true;
		return getRecipeResult();
	}

	public ItemStack remove(int x, int y) {
		matrix[x][y] = false;
		return getRecipeResult();
	}

	public void removeAll() {
		for (int x = 0; x < matrix.length; ++x) for (int y = 0; y < matrix[x].length; ++y) matrix[x][y] = false;
		setDirty();
	}

	public boolean isEqual(CraftMatrixBool other) {
		if (other.width != this.width || other.height != this.height) return false;
		for (int i = 0; i < width * height; i++) if (other.matrix[i] != this.matrix[i]) return false;
		return true;
	}

	private ItemStack getRecipeResult() {
		ListRecipeManager.STONE_RECIPES.constainsAndGetValue(matrix);
		return ItemStack.EMPTY;
	}

	public void setDirty() {
		if (!dirty) this.dirty = true;
	}
	
	public void refreshMatrix() {
		for (int x = 0; x < matrix.length; ++x) for (int y = 0; y < matrix[x].length; ++y) matrix[x][y] = true;
		setNotDirty();
	}
	
	public void setNotDirty() {
		if (dirty) this.dirty = false;
	}

	public int getWidth() { return width; }

	public int getHeight() { return height; }

	public boolean isDirty() { return dirty; }

	public boolean[][] getMatrix() { return matrix; }
}