package com.xumuk.realism.utils;

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

	public ItemStack getRecipeResult() {
		// TODO: Create matches recipes
		return ItemStack.EMPTY;
	}

	public void setDirty() {
		if (!dirty) this.dirty = true;
	}

	public int getWidth() { return width; }

	public int getHeight() { return height; }

	public boolean isDirty() { return dirty; }

	public boolean[][] getMatrix() { return matrix; }
}