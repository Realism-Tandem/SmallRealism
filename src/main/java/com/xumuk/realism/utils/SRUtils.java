package com.xumuk.realism.utils;

import java.util.Arrays;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;

import com.xumuk.realism.RealismCore;

public class SRUtils {

	public static boolean intToBoolean(int input) {
		if (input == 0 || input == 1) return input != 0;
		else {
			RealismCore.logger.log(Level.WARN, "Argument must be 1 or 0! It's will setting to false.");
			return false;
		}
	}

	@Nullable
	public static boolean[][] parseRecipeToBoolean(int[][] recipe) {
		boolean[][] matrix = new boolean[recipe.length][recipe[0].length];
		Arrays.asList(matrix).forEach(System.out::println);
		for (int x = 0; x < recipe.length; ++x) {
			for (int y = 0; y < recipe[x].length; ++y) {
				matrix[x][y] = intToBoolean(recipe[x][y]);
				System.out.println(recipe[x][y]);
				System.out.println(matrix[x][y]);
			}
		}
		return matrix;
	}

	public static int[] hex2Rgb(String colorStr) {
		return new int[] {
				Integer.valueOf(colorStr.substring(1, 3), 16), 
				Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16)};
	}

	public static String dec2Hex(int color) {
		return String.format("#%06X", (0xFFFFFF & color));
	}

	public static int[] dec2Rgb(int color) {
		return hex2Rgb(dec2Hex(color));
	}
}