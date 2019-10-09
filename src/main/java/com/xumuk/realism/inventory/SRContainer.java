package com.xumuk.realism.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class SRContainer extends Container {
	private boolean isOffhand;
	private World world;

	public SRContainer(InventoryPlayer playerInv, World world) {
		this.world = world;
		addPlayerInventorySlots(playerInv);
		addContainerSlots();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0) {
				itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

				if (!this.mergeItemStack(itemstack1, 10, 46, true)) { return ItemStack.EMPTY; }

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (index >= 10 && index < 37) {
				if (!this.mergeItemStack(itemstack1, 37, 46, false)) { return ItemStack.EMPTY; }
			}
			else if (index >= 37 && index < 46) {
				if (!this.mergeItemStack(itemstack1, 10, 37, false)) { return ItemStack.EMPTY; }
			}
			else if (!this.mergeItemStack(itemstack1, 10, 46, false)) { return ItemStack.EMPTY; }

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) { return ItemStack.EMPTY; }

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

			if (index == 0) { playerIn.dropItem(itemstack2, false); }
		}

		return itemstack;
	}

	public void addContainerSlots() {};

	protected void addPlayerInventorySlots(InventoryPlayer playerInv) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
	}

	public boolean isOffhand() { return isOffhand; }

	public void setOffhand(boolean isOffhand) { this.isOffhand = isOffhand; }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}