package com.xumuk.realism.proxy;

import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.RegItems;
import com.xumuk.realism.event.RegEvents;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    
    
}
