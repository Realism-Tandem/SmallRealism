package com.xumuk.realism.blocks.itemblocks;

import com.xumuk.realism.blocks.BlockCobblestones;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBlockCobblestones extends ItemMultiTexture{

    public ItemBlockCobblestones(Block block) {
        super(block, block, new String[] {"cobble_andesite","cobble_diorite","cobble_granite"} );
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
        return super.getUnlocalizedName() + "." + BlockCobblestones.EnumType.byMetadata(stack.getMetadata()).getName();
    }
}
