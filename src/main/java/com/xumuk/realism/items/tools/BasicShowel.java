package com.xumuk.realism.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicShowel extends Item
{
	private String name;
	public float[] destroySpeeds;
	public BasicShowel()
	{


	}
	public BasicShowel(String name, int maxDamage, float[] destroySpeeds)
	{
		super();
		this.name = name;
		this.setRegistryName(name);
		this.setMaxDamage(maxDamage);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.destroySpeeds = destroySpeeds;
	}
	public boolean hasOverlay(ItemStack stack)
	{
		return true;
	}
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Material material = state.getMaterial();
		if(material == Material.GROUND) {
			return destroySpeeds[0];
		} else     
			if(material == Material.SAND) {
				return destroySpeeds[1];
			}
			else
				if(material == Material.GRASS) {
					return destroySpeeds[2];

				} 
				else
					if(material == Material.CLAY) {
						return destroySpeeds[3];
					}
					else return 0.05F;
	}
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
	{
		final ResourceLocation blockRegName = ForgeRegistries.BLOCKS.getKey(state.getBlock());
		if(state.getMaterial() == Material.GRASS || state.getMaterial() == Material.GROUND || state.getMaterial() == Material.SAND|| state.getMaterial() == Material.CLAY) {

			if (blockRegName != null) {
				final NBTTagCompound storage = new NBTTagCompound();
				storage.setString("blockName", blockRegName.toString());
				stack.setTagCompound(storage);
				return true;
			}
		} 
		return true;

	}
	@SubscribeEvent
	public void leftClick(PlayerInteractEvent.LeftClickBlock e) {
		final ItemStack stack = e.getEntityPlayer().getHeldItem(e.getHand());
		final NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) return;
		if(nbt.getSize() > 0) e.setCanceled(true);
	}
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		final ItemStack stack = player.getHeldItem(hand);
		final NBTTagCompound nbt = stack.getTagCompound();

		if (!stack.isEmpty() && nbt != null && nbt.hasKey("blockName")) {
			final String blockName = nbt.getString("blockName");
			final Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));

			if (block != null) {
				worldIn.setBlockState(pos.offset(facing), block.getDefaultState());
				stack.damageItem(1, player);
				nbt.removeTag("blockName");
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}




}