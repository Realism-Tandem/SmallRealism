package com.xumuk.realism.event.Server;

import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.RegItems;

import net.minecraft.block.BlockSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClearDropsEvent {

	@SubscribeEvent
	public void onDrop(BlockEvent.HarvestDropsEvent event) {		
		EntityPlayer player = (EntityPlayer)event.getHarvester();
		if(!event.getWorld().isRemote) { 	
			if(player != null) {
				ItemStack is = player.getHeldItem(EnumHand.MAIN_HAND);
				
				if(is != null && is.getItem() != Items.STONE_SHOVEL ){
					if(event.getState().getBlock().equals(Blocks.GRASS_PATH) || event.getState().getBlock().equals(Blocks.FARMLAND) || event.getState().getBlock().equals(Blocks.DIRT) || event.getState().getBlock().equals(Blocks.GRASS)) {
						event.getDrops().clear();
						event.getDrops().add(new ItemStack(RegItems.HANDFUL_DIRT,event.getWorld().rand.nextInt(5)));
					}
				}

				if(event.getState().getBlock().equals(Blocks.GRAVEL)) {
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(RegItems.SCALE,event.getWorld().rand.nextInt(3), 0));
				}
				if(event.getState().getBlock().equals(Blocks.CLAY)) {
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(RegItems.CLUMPCLAY,event.getWorld().rand.nextInt(5)));
				}
				if(event.getState().getBlock().equals(Blocks.VINE)) {
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(Blocks.VINE,1));
				}
				if(event.getState().getBlock().equals(Blocks.STONE)) {
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(RegItems.SCALE, 1 + event.getWorld().rand.nextInt(5), 0));
				}

//				if(event.getState() == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND)) {
//					event.getDrops().clear();
//					event.getDrops().add(new ItemStack(Item.getItemFromBlock(RegBlocks.realismsand),1));
//				}
//				if(event.getState() == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND)) {
//					event.getDrops().clear();
//					event.getDrops().add(new ItemStack(Item.getItemFromBlock(RegBlocks.realismredsand),1));
//				}

			}
		}
	}
}
