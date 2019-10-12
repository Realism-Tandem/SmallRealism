package com.xumuk.realism.inventory.slot;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotStoneOutput extends Slot {
	private Runnable onSlotTake;

	public SlotStoneOutput(InventoryBasic inv, int idx, int x, int y, Runnable onSlotTake) {
		super(inv, idx, x, y);
		this.onSlotTake = onSlotTake;
	}

	@Override
	@Nonnull
	public ItemStack onTake(EntityPlayer thePlayer, @Nonnull ItemStack stack) {
		onSlotTake.run();
		return super.onTake(thePlayer, stack);
	}

	@Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
		return false;
	}
}