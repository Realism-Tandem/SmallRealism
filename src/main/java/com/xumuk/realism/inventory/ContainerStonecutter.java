package com.xumuk.realism.inventory;

import com.xumuk.realism.inventory.slot.SlotStoneOutput;
import com.xumuk.realism.utils.CraftMatrixBool;
import com.xumuk.realism.utils.IButtonHandler;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerStonecutter extends SRContainer implements IButtonHandler {

	private CraftMatrixBool matrix = new CraftMatrixBool();

	public ContainerStonecutter(InventoryPlayer playerInv, World world) {
		super(playerInv, world);
	}

	@Override
	public void onButtonPress(int idX, int idY) {
		ItemStack out = matrix.set(idX, idY);
		if (out != ItemStack.EMPTY) putStackInSlot(0, out);
	}

	public void setRequiresResetToFalse() {
		matrix.setDirty();
	}

	public boolean isRequiresReset() { return matrix.isDirty(); }

	@Override
	public void addContainerSlots() {
		addSlotToContainer(new SlotStoneOutput(new ItemStackHandler(1), 0, 128, 44, this::reset));
	}
	
	private void reset() {
		matrix.removeAll();
	}
}