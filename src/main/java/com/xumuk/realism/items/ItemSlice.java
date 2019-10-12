package com.xumuk.realism.items;

import java.util.Map;

import com.google.common.collect.Maps;
import com.xumuk.realism.RealismCore;
import com.xumuk.realism.SRGuiHandler;
import com.xumuk.realism.utils.IRock;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSlice extends Item implements IRock {

	public ItemSlice(String name, CreativeTabs tab) {
		super();
		this.setHasSubtypes(true);
		this.setRegistryName(name);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(name);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (StoneTypes type : StoneTypes.values()) { items.add(new ItemStack(this, 1, type.getMeta())); }
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		StoneTypes st = StoneTypes.byItemStack(stack);
		return this.getUnlocalizedName() + "." + st.getName();
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

	@Override
	public int getRockColor(int meta) {
		switch (meta) {
			case 0: return 7697781;
			case 1: return 8608072;
			case 2: return 12763842;
			default: case 3: return 8620142;
		}
	}

	public enum StoneTypes {

		DEFAULT("default", 0), 
		GRANITE("granite", 1), 
		DIORITE("diorite", 2), 
		ANDESITE("andesite", 3);

		private static final Map<Integer, StoneTypes> META_HASH = Maps.<Integer, StoneTypes>newHashMap();
		public String name;
		public int meta;

		private StoneTypes(String name, int meta) {
			this.name = name;
			this.meta = meta;

		}

		public static StoneTypes getByMeta(int meta) {
			for (StoneTypes type : values()) { if (type.ordinal() == meta) return type; }
			return null;
		}

		public static StoneTypes byMetadata(int meta) {
			StoneTypes st = META_HASH.get(Integer.valueOf(meta));
			return st;
		}

		public static StoneTypes byItemStack(ItemStack stack) {
			return byMetadata(stack.getMetadata());
		}

		public String getName() { return this.name; }

		public int getMeta() { return this.meta; }

		static {
			for (StoneTypes st : values()) { META_HASH.put(Integer.valueOf(st.getMeta()), st); }
		}
	}
}