package com.xumuk.realism.inventory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@ParametersAreNonnullByDefault
public abstract class SRContainer extends Container {

	protected final ItemStack stack;
	private final EntityPlayer player;
	private int itemIndex;
	private int itemDragIndex;
	private boolean isOffhand;

	public SRContainer(InventoryPlayer playerInv, ItemStack stack) {
		this.player = playerInv.player;
		this.stack = stack;
		this.itemDragIndex = playerInv.currentItem;

		if (stack == player.getHeldItemMainhand()) {
			this.itemIndex = playerInv.currentItem + 27;
			this.setOffhand(false);
		}
		else {
			this.itemIndex = -100;
			this.setOffhand(true);
		}

		addContainerSlots();
		addPlayerInventorySlots(playerInv);
	}

	/**
	 * Code by TFC
	 * From https://github.com/TerraFirmaCraft/TerraFirmaCraft/blob/master/src/main/java/net/dries007/tfc/objects/container/ContainerItemStack.java
	 */
	@Override
	@Nonnull
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		// Slot that was clicked
		Slot slot = inventorySlots.get(index);

		ItemStack itemstack;

		if (slot == null || !slot.getHasStack()) return ItemStack.EMPTY;

		if (index == itemIndex) return ItemStack.EMPTY;

		ItemStack itemstack1 = slot.getStack();
		itemstack = itemstack1.copy();

		// Begin custom transfer code here

		int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size(); // number of slots in the
																							// container

		if (index < containerSlots) {
			// Transfer out of the container
			if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
				// Don't transfer anything
				return ItemStack.EMPTY;
			}
			// tile.setAndUpdateSlots(index);
		}
		// Transfer into the container
		else {
			if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) { return ItemStack.EMPTY; }
		}

		// Required
		if (itemstack1.getCount() == 0) {
			slot.putStack(ItemStack.EMPTY);
		}
		else {
			slot.onSlotChanged();
		}
		if (itemstack1.getCount() == itemstack.getCount()) { return ItemStack.EMPTY; }
		slot.onTake(player, itemstack1);
		return itemstack;
	}

	@Override
	@Nonnull
	public ItemStack slotClick(int slotID, int dragType, ClickType clickType, EntityPlayer player) {
		if (slotID == itemIndex && (clickType == ClickType.QUICK_MOVE || clickType == ClickType.PICKUP
				|| clickType == ClickType.THROW || clickType == ClickType.SWAP)) {
			return ItemStack.EMPTY;
		}
		else if ((dragType == itemDragIndex) && clickType == ClickType.SWAP) {
			return ItemStack.EMPTY;
		}
		else {
			return super.slotClick(slotID, dragType, clickType, player);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	protected abstract void addContainerSlots();

	protected void addPlayerInventorySlots(InventoryPlayer playerInv) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) { addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142)); }
	}

	public boolean isOffhand() {
		return isOffhand;
	}

	public void setOffhand(boolean isOffhand) {
		this.isOffhand = isOffhand;
	}
}