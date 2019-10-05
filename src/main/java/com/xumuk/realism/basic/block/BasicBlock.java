package com.xumuk.realism.basic.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BasicBlock extends Block{

	public BasicBlock(Material materialIn, String name, CreativeTabs tab) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(tab);
	}
	
}


