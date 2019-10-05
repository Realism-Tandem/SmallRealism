package com.xumuk.realism.basic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BasicItem extends Item {
	public BasicItem(String name, CreativeTabs tab) {
		this.setRegistryName(name);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(name);
	}
}
