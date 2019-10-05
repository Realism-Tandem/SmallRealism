package com.xumuk.realism.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeapRocks extends Block {
	private static AxisAlignedBB AABB = new AxisAlignedBB(0.2D, 0D, 0.2D, 0.8D, 0.4D, 0.8D);

	public HeapRocks(final Material materialIn, final String name, CreativeTabs tab) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(tab);
		this.setTickRandomly(true);

	}
	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
		world.scheduleUpdate(pos, this, this.tickRate(world));
		if(!world.isRemote) {
			if(!world.isSideSolid(pos.down(), EnumFacing.UP)) {
				world.destroyBlock(pos, true);
			}
		}
    }
	@Override
	public  void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{	

		if (!world.isRemote)
		{
			if(!world.isSideSolid(pos.down(), EnumFacing.UP)) {
				world.destroyBlock(pos, true);
			}
		}
		}
    
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{

		if(!worldIn.isRemote) {
			worldIn.destroyBlock(pos, true);
			
			return true;
		}
		return true;
	}
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return new AxisAlignedBB(0.1D, 0D, 0.1D, 0.9D, 0.4D, 0.9D);
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}

