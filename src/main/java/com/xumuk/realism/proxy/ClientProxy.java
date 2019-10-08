package com.xumuk.realism.proxy;

import javax.annotation.Nonnull;

import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.RegItems;
import com.xumuk.realism.event.RegEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		RegEvents.Client();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		RegBlocks.registerRender();
		RegItems.registerRender();
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	@Nonnull
	public IThreadListener getThreadListener(MessageContext context) {
        if (context.side.isClient()) return Minecraft.getMinecraft();
        else return context.getServerHandler().player.mcServer;
	}
}