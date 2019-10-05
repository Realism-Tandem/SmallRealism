package com.xumuk.realism;

import com.xumuk.realism.blocks.BlockCobblestones;
import com.xumuk.realism.blocks.HeapRocks;
import com.xumuk.realism.blocks.RottenPlanks;
import com.xumuk.realism.blocks.itemblocks.ItemBlockCobblestones;
import com.xumuk.realism.blocks.itemblocks.ItemBlockRottingPlanks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegBlocks {
	//Meta blocks --------------------------------------------------------------------------------------------
	public static final Block ROTTING_PLANKS = new RottenPlanks("rotting_planks", CreativeTabs.BUILDING_BLOCKS);
	public static final Block COBBLESTONES = new BlockCobblestones("cobblestones", CreativeTabs.BUILDING_BLOCKS);
	
	
	//Blocks with Model --------------------------------------------------------------------------------------------
	public static final Block HEAP_ROCKS = new HeapRocks(Material.ROCK, "heap_rocks", RealismCore.tabMain);
	
	
	//Simple Blocks --------------------------------------------------------------------------------------------
	
	
	public static void register() {
		registerBlockMeta(ROTTING_PLANKS, new ItemBlockRottingPlanks(ROTTING_PLANKS));
		registerBlock(HEAP_ROCKS);
		registerBlockMeta(COBBLESTONES, new ItemBlockCobblestones(COBBLESTONES));
	}
	public static void registerRender() {
		registerRenderBlock(HEAP_ROCKS);

	}
	public static void registerBlock(Block block) {
		/*Модели регистрируются ТОЛЬКО в RegRenderMetaBlocks !!!*/
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	public static void registerRenderBlock(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	public static void registerBlockMeta(Block block, ItemBlock itemBlock) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
}