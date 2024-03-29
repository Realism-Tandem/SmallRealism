package com.xumuk.realism.proxy;

import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.RegCrafts;
import com.xumuk.realism.RegItems;
import com.xumuk.realism.capability.playerCAP.IPlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCapStorage;
import com.xumuk.realism.capability.worldCAP.DateStorage;
import com.xumuk.realism.capability.worldCAP.IDate;
import com.xumuk.realism.event.RegEvents;
import com.xumuk.realism.packets.NetworkHandler;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        RegItems.register();
    	RegBlocks.register();
    	RegEvents.Server();
    	
    	CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
    	 CapabilityManager.INSTANCE.register(IDate.class, DateStorage.INSTANCE, DateStorage.INSTANCE);
    	NetworkHandler.init();
    }
    public void init(FMLInitializationEvent event) {
    	RegCrafts.recipesRegister();
    }
    public void postInit(FMLPostInitializationEvent event) {}
}
