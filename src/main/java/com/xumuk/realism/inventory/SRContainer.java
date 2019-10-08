package com.xumuk.realism.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class SRContainer extends Container {
	private boolean isOffhand;

	public SRContainer(InventoryPlayer playerInv) {

		addContainerSlots();
		addPlayerInventorySlots(playerInv);
	}

	protected abstract void addContainerSlots();

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
	
	@Override public boolean canInteractWith(EntityPlayer playerIn) { return true; }
}