package com.xumuk.realism.blocks.itemblocks;

import com.xumuk.realism.blocks.RottenPlanks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBlockRottingPlanks extends ItemMultiTexture{

    public ItemBlockRottingPlanks(Block block) {
        super(block, block, new String[] {"r_oak"} );
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
   
            this.block.getSubBlocks(tab, items);
        }
    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + RottenPlanks.EnumType.byMetadata(stack.getMetadata()).getName();
    }
}
