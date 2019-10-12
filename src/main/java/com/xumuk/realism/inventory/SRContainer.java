package com.xumuk.realism.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xumuk.realism.inventory.slot.SlotFixedItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

public abstract class SRContainer extends Container {
	private boolean isOffhand;
	private World world;
	private List<Integer> fixedSlots = new ArrayList();

	public SRContainer(InventoryPlayer playerInv, World world, Integer... fixedSlots) {
		this.world = world;
		this.fixedSlots = Arrays.<Integer>asList(fixedSlots);
		addPlayerInventorySlots(playerInv);
	}

	public List<Integer> getFixedSlots() { return fixedSlots; }

	protected void addPlayerInventorySlots(InventoryPlayer playerInv) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 102 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			if (fixedSlots.contains(k)) addSlotToContainer(new SlotFixedItem(playerInv, k, 8 + k * 18, 160));
			else addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 160));
		}
	}

	public boolean isOffhand() { return isOffhand; }

	public void setOffhand(boolean isOffhand) { this.isOffhand = isOffhand; }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}