package com.xumuk.realism.inventory;

import com.xumuk.realism.inventory.slot.SlotStoneOutput;
import com.xumuk.realism.utils.CraftMatrixBool;
import com.xumuk.realism.utils.IButtonHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerStonecutter extends SRContainer implements IButtonHandler {

	private CraftMatrixBool matrix = new CraftMatrixBool();
	private InventoryBasic inv = new InventoryBasic("", false, 1);
	private boolean requiresReset = false;

	public ContainerStonecutter(InventoryPlayer playerInv, World world) {
		super(playerInv, world, playerInv.currentItem);
		addSlotToContainer(new SlotStoneOutput(inv, 0, 128, 44, this::reset));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		//TODO: mergeItemStack
		return ItemStack.EMPTY;
	}

	@Override
	public void onButtonPress(int idX, int idY) {
		ItemStack out = matrix.set(idX, idY);
		if (out != ItemStack.EMPTY) inv.addItem(out);
	}

	public void setRequiresResetToFalse() {
		requiresReset = false;
	}

	public boolean isRequiresReset() { return requiresReset; }

	private void reset() {
		matrix.refreshMatrix();
		requiresReset = true;
	}
}