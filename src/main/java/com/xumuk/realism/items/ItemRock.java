package com.xumuk.realism.items;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.SRGuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRock extends Item {
	public ItemRock(String name) {
		super();
		setMaxStackSize(16);
		setRegistryName(name);
		setCreativeTab(RealismCore.tabMain);
		setUnlocalizedName(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (player.getHeldItem(hand).getCount() >= 2) {
			BlockPos pos = player.getPosition();
			if (!world.isRemote) player.openGui(RealismCore.INSTANCE, SRGuiHandler.GUI_STONECUTTER, world, pos.getX(),
					pos.getY(), pos.getZ());
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
}