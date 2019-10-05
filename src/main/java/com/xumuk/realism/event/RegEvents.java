package com.xumuk.realism.event;

import com.xumuk.realism.RegRenderMeta;
import com.xumuk.realism.capevent.PlayerCAPEventHandler;
import com.xumuk.realism.capevent.WorldCAPEventHandler;
import com.xumuk.realism.event.Client.ClientEventHandler;
import com.xumuk.realism.event.Client.RenderDebugMode;
import com.xumuk.realism.event.Client.SeasonEventer;
import com.xumuk.realism.event.Server.SeasonEventerServer;
import com.xumuk.realism.items.tools.BasicShowel;
import com.xumuk.realism.render.TextureHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class RegEvents {
	public static void Client() {
			register(new RegRenderMeta());
			register(new SeasonEventer());
			register(new RenderDebugMode());
			
	}
	public static void Server() {
				register(new PlayerCAPEventHandler());
			register(new WorldCAPEventHandler());
			register(new BasicShowel());
			register(new ClientEventHandler());
			register(new SeasonEventerServer());
			register(new  TextureHandler());
		
	}
	
	public static void register(Object event) {
		MinecraftForge.EVENT_BUS.register(event);
	}
	public static void registerFML(Object event) {
		FMLCommonHandler.instance().bus().register(event);
	}
}
