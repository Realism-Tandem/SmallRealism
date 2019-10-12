package com.xumuk.realism;

import com.xumuk.realism.command.RealismCommand;
import com.xumuk.realism.creativetabs.MainRealism;
import com.xumuk.realism.proxy.IProxy;
import com.xumuk.realism.utils.SRLogManager;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = RealismCore.MODID, name = "realism", version = RealismCore.VERSION)
public class RealismCore {
	public static final String MODID = "realism";
	public static final String VERSION = "0.1 alpha";
	public static CreativeTabs tabMain = new MainRealism("tabMain");
	public static SRLogManager logger = new SRLogManager();

	@Mod.Instance 
	public static RealismCore INSTANCE;

	public static final ResourceLocation DATE = new ResourceLocation("seasons", "date");

	@SidedProxy(clientSide = "com.xumuk.realism.proxy.ClientProxy", serverSide = "com.xumuk.realism.proxy.CommonProxy") 
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@EventHandler
	public void onStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new RealismCommand());
	}
}
