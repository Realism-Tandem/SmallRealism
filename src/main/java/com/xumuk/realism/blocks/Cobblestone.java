package com.xumuk.realism.blocks;

import java.util.Random;

import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.basic.block.BasicBlockFalling;
import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Cobblestone extends BasicBlockFalling {

	public Cobblestone(Material materialIn, String name, CreativeTabs tab) {
		super(materialIn, name, tab);
		setTickRandomly(true);
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	@Override
	public  void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{	

		if (!worldIn.isRemote)
		{
			for(EnumFacing f :  EnumFacing.VALUES) {
				if(worldIn.getBlockState(pos.offset(f)).getMaterial() == Material.WATER ) {
					if(worldIn.rand.nextInt(3) == 2) {
						worldIn.setBlockState(pos, RegBlocks.HEAP_ROCKS.getDefaultState());
					}
				}
			}
		
				this.checkFallableDown(worldIn, pos);
				this.checkFallableWest(worldIn, pos);
				this.checkFallableEast(worldIn, pos);
				this.checkFallableNorth(worldIn, pos);
				this.checkFallableSouth(worldIn, pos);
			
		}

	}
}
