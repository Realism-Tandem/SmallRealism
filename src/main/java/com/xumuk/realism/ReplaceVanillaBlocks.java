package com.xumuk.realism;

import com.xumuk.realism.blocks.Cobblestone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ReplaceVanillaBlocks {
	public static Block COBBLESTONE = new Cobblestone(Material.ROCK, "cobblestone", CreativeTabs.BUILDING_BLOCKS);
}
