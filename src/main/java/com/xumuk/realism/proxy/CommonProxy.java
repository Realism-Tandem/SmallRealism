package com.xumuk.realism.proxy;

import static com.xumuk.realism.RealismCore.logger;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Level;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.RegItems;
import com.xumuk.realism.RegRecipes;
import com.xumuk.realism.capability.playerCAP.IPlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCapStorage;
import com.xumuk.realism.capability.worldCAP.DateStorage;
import com.xumuk.realism.capability.worldCAP.IDate;
import com.xumuk.realism.event.RegEvents;

import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RegItems.register();
		RegBlocks.register();
		RegEvents.Server();

		CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
		CapabilityManager.INSTANCE.register(IDate.class, DateStorage.INSTANCE, DateStorage.INSTANCE);
		
		try {
			RealismCore.network_handler.init();
		} catch (Exception e) {
			logger.log(Level.FATAL, "Packets don't registered! This is fatal error.");
			e.printStackTrace();
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		RegRecipes.register();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}

	@Override
	@Nonnull
	public IThreadListener getThreadListener(MessageContext context) throws Exception {
		if (context.side.isServer()) return context.getServerHandler().player.mcServer;
		else throw new Exception(
				"Tried to get the IThreadListener from a client-side MessageContext on the dedicated server");
	}
}