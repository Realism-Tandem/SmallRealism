package com.xumuk.realism.event.Server;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UnbreakBlock {
	
	@SubscribeEvent
    public void unbreakeableWood(PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() == Blocks.LOG) {
            if (canBlockItemAxe(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND))) event.setCanceled(true);
        } else if (event.getState().getBlock() == Blocks.LOG2) {
            if (canBlockItemAxe(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND))) event.setCanceled(true);
        }
        else if (event.getState().getBlock() == Blocks.STONE || event.getState().getBlock() == Blocks.COBBLESTONE  || event.getState().getBlock() == Blocks.COBBLESTONE_WALL) {
            if (canBlockItemPickaxe(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND))) event.setCanceled(true);
        }
    }
	  private static boolean canBlockItemPickaxe(ItemStack itemStack) {
	        return itemStack == null || !(itemStack.getItem() instanceof ItemPickaxe);
	    }
    private static boolean canBlockItemAxe(ItemStack itemStack) {
        return itemStack == null || !(itemStack.getItem() instanceof ItemAxe);
    }
}
