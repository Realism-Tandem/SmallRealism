package com.xumuk.realism;

import com.xumuk.realism.blocks.BlockCobblestones;
import com.xumuk.realism.blocks.RottenPlanks;
import com.xumuk.realism.items.ItemSlice;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegRenderMeta {
	
    @SubscribeEvent
    public void registerMeta(ModelRegistryEvent e) {
    	registerRottenPlanks(RegBlocks.ROTTING_PLANKS);
    	registerCobblestones(RegBlocks.COBBLESTONES);
    	registerMetaStone(RegItems.SCALE);
    }


    public void registerMetaStone(Item item) {
    	for (ItemSlice.StoneTypes type : ItemSlice.StoneTypes.values())
    	{
    	    ModelLoader.setCustomModelResourceLocation(item, type.getMeta(), new ModelResourceLocation(item.getRegistryName() + "_" + type.getName(), "inventory"));
    	}
    }
    public void registerRottenPlanks(Block block)
    {
        for(RottenPlanks.EnumType type : RottenPlanks.EnumType.values())
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), type.getMetadata(), new ModelResourceLocation(block.getRegistryName() + "_" + type.getName(), "inventory"));
        }
    }
    public void registerCobblestones(Block block)
    {
        for(BlockCobblestones.EnumType type : BlockCobblestones.EnumType.values())
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), type.getMetadata(), new ModelResourceLocation(block.getRegistryName() + "_" + type.getName(), "inventory"));
        }
    }

}