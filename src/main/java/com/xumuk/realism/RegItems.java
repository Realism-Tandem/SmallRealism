package com.xumuk.realism;

import com.xumuk.realism.basic.BasicItem;
import com.xumuk.realism.items.ItemSlice;
import com.xumuk.realism.items.tools.BasicShowel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegItems {
	public static final Item STONE_SHOWEL = new BasicShowel("stone_shovel", 256, new float[] {1.2F,1.6F,1.3F,0.6F}).setCreativeTab(RealismCore.tabMain);
	public static final Item COPPER_SHOWEL = new BasicShowel("copper_shovel", 345, new float[] {1.5F,1.9F,1.6F,0.9F}).setCreativeTab(RealismCore.tabMain);
	public static final Item BRONZE_SHOWEL = new BasicShowel("bronze_shovel", 512, new float[] {1.8F,2.2F,1.9F,1.2F}).setCreativeTab(RealismCore.tabMain);
	public static final Item IRON_SHOWEL = new BasicShowel("iron_shovel", 1024, new float[] {2.1F,2.5F,2.2F,1.5F}).setCreativeTab(RealismCore.tabMain);
	public static final Item STEEL_SHOWEL = new BasicShowel("steel_shovel", 1512, new float[] {2.4F,2.8F,2.5F,1.8F}).setCreativeTab(RealismCore.tabMain);
	public static final Item ARM_STEEL_SHOWEL = new BasicShowel("arm_steel_shovel", 2800, new float[] {2.8F,3.2F,2.9F,2.2F}).setCreativeTab(RealismCore.tabMain);
	
	public static final Item SCALE = new ItemSlice("item_stone", RealismCore.tabMain);
	public static final Item CLUMPCLAY = new BasicItem("item_clumpclay", RealismCore.tabMain);
	public static final Item HANDFUL_DIRT = new BasicItem("item_hf_dirt", RealismCore.tabMain);
	
	public static void register() {
		registerItems(SCALE);
		registerItems(CLUMPCLAY);
		registerItems(HANDFUL_DIRT);
		registerItems(STONE_SHOWEL);
		registerItems(COPPER_SHOWEL);
		registerItems(BRONZE_SHOWEL);
		registerItems(IRON_SHOWEL);
		registerItems(ARM_STEEL_SHOWEL);
		registerItems(STEEL_SHOWEL);
	}
	public static void registerRender() {
		registerItemsRender(CLUMPCLAY);
		registerItemsRender(HANDFUL_DIRT);
		registerItemsRender(STONE_SHOWEL);
		registerItemsRender(COPPER_SHOWEL);
		registerItemsRender(BRONZE_SHOWEL);
		registerItemsRender(IRON_SHOWEL);
		registerItemsRender(ARM_STEEL_SHOWEL);
		registerItemsRender(STEEL_SHOWEL);
	}

	private static void registerItems(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}
	private static void registerItemsRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}