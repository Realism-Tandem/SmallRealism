package com.xumuk.realism.recipes;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Optional;

import com.xumuk.realism.utils.SRUtils;

import net.minecraft.item.ItemStack;

public class StoneRecipeManager extends BasicRecipeManager<ItemStack, boolean[][]>{

	public StoneRecipeManager() {
		super((rec1, rec2) -> Arrays.deepEquals((boolean[][])rec1, (boolean[][])rec2), "Stone recipe manager");
	}
	
	@Override
	public ItemStack constainsAndGetValue(boolean[][] matrix) {
		System.out.println("Succes");
		Arrays.asList(matrix).forEach(System.out::println);
		Optional<Entry<ItemStack, boolean[][]>> optional = values()
				.entrySet()
				.stream()
				.filter(e -> (boolean)isRecipeEqual(e.getValue(), matrix))
				.findFirst();
		if (optional.isPresent()) {
			System.out.println(optional.get().getKey().getItem().getUnlocalizedName());
		}
		else {
			System.out.println("Null");
		}
		return optional.isPresent() ? optional.get().getKey() : ItemStack.EMPTY;
	}
	
	@Override
	protected void putIntegerRecipe(int[][] recipe, ItemStack output) {
		putRecipe(SRUtils.parseRecipeToBoolean(recipe), output);
	}
}