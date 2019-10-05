package com.xumuk.realism.event.Client;

import com.xumuk.realism.render.RModelLoader;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//Class for small events
public class ClientEventHandler {
	@SubscribeEvent
	public void eventRegisterModel(ModelRegistryEvent event){
	    ModelLoaderRegistry.registerLoader(new RModelLoader());
	} 
}
