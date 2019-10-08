package com.xumuk.realism.inventory;

import com.xumuk.realism.inventory.slot.SlotStoneOutput;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerStonecutter extends SRContainer implements IButtonHandler {
	
	private CraftMatrixBool matrix = new CraftMatrixBool();
	private ItemStack out = ItemStack.EMPTY;

	public ContainerStonecutter(InventoryPlayer playerInv, CraftRecipeBool recipe) {

	}

	@Override
	public void onButtonPress(int buttonID, NBTTagCompound extraNBT) {}

	@Override
	protected void addContainerSlots() {
		addSlotToContainer(new SlotStoneOutput(new ItemStackHandler(1), 0, 128, 44, this::resetMatrix));

	}
}