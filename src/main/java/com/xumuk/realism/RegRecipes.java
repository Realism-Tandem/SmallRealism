package com.xumuk.realism;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

public class RegRecipes {

	protected static Map<String, String[]> RECIPES = new HashMap();

	public static void register() {

	}
	
	public void putRecipe(String key, String[] recipe) {
		if (!RECIPES.containsKey(key)) RECIPES.put(key, recipe);
		else RealismCore.logger.log(Level.ERROR,
				String.format("Error of registry recipe \"/*\": Recipe with name registered yet!", key));
	}

	public void removeRecipe(String key, String[] recipe) {
		RECIPES.remove(key, recipe);
	}
}