package com.xumuk.realism;

import com.xumuk.realism.client.gui.GuiStonecutter;
import com.xumuk.realism.inventory.ContainerStonecutter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SRGuiHandler implements IGuiHandler {

	public static final int GUI_STONECUTTER = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_STONECUTTER:
			return new ContainerStonecutter(player.inventory, world);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_STONECUTTER:
			return new GuiStonecutter((Container)getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		default:
			return null;
		}
	}
}