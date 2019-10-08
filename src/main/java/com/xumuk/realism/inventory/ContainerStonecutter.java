package com.xumuk.realism.inventory;

import com.xumuk.realism.inventory.slot.SlotStoneOutput;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerStonecutter extends SRContainer implements IButtonHandler {

	private CraftMatrixBool matrix = new CraftMatrixBool();
	private ItemStack out = ItemStack.EMPTY;

	public ContainerStonecutter(InventoryPlayer playerInv) {
		super(playerInv);
	}

	@Override
	public void onButtonPress(int buttonID, NBTTagCompound extraNBT) {
		ItemStack stack = matrix.getRecipeResult();
		if (stack != ItemStack.EMPTY) putStackInSlot(0, out = matrix.getRecipeResult());
		matrix.setDirty();
	}

	public ItemStack getOutStack() { return out; }

	@Override
	protected void addContainerSlots() {
		addSlotToContainer(new SlotStoneOutput(new ItemStackHandler(1), 0, 128, 44, matrix::removeAll));
	}
}