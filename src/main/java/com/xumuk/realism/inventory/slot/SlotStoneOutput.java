package com.xumuk.realism.inventory.slot;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotStoneOutput extends SlotItemHandler {
	private Runnable onSlotTake;

	public SlotStoneOutput(IItemHandler inventory, int idx, int x, int y, Runnable onSlotTake) {
		super(inventory, idx, x, y);

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