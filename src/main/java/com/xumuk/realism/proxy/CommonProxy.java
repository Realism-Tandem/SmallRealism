package com.xumuk.realism.proxy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.RegItems;
import com.xumuk.realism.RegRecipes;
import com.xumuk.realism.SRGuiHandler;
import com.xumuk.realism.capability.playerCAP.IPlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCapStorage;
import com.xumuk.realism.capability.worldCAP.DateStorage;
import com.xumuk.realism.capability.worldCAP.IDate;
import com.xumuk.realism.event.RegEvents;
import com.xumuk.realism.packets.NetworkHandler;
import com.xumuk.realism.recipes.ListRecipeManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RegItems.register();
		RegBlocks.register();
		RegEvents.Server();

		CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
		CapabilityManager.INSTANCE.register(IDate.class, DateStorage.INSTANCE, DateStorage.INSTANCE);

		NetworkHandler.init();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(RealismCore.INSTANCE, new SRGuiHandler());
		ListRecipeManager.initManagers();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		RegRecipes.registerRecipes();
	}

	@Override
	@Nonnull
	public IThreadListener getThreadListener(MessageContext context) throws Exception {
		if (context.side.isServer()) return context.getServerHandler().player.mcServer;
		else throw new Exception(
				"Tried to get the IThreadListener from a client-side MessageContext on the dedicated server");
	}

	@Override
	@Nullable
	public EntityPlayer getPlayer(MessageContext context) throws Exception {
		if (context.side.isServer()) return context.getServerHandler().player;
		else throw new Exception("Tried to get the player from a client-side MessageContext on the dedicated server");
	}
}