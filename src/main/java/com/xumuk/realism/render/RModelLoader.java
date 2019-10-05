package com.xumuk.realism.render;

import com.xumuk.realism.render.model.row.ModelShovel;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class RModelLoader implements ICustomModelLoader{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		 if(modelLocation.toString().contains("stone_shovel"))
			 return modelLocation.toString().contains("stone_shovel");
		 else if(modelLocation.toString().contains("copper_shovel"))
			 return modelLocation.toString().contains("copper_shovel");
		 else if(modelLocation.toString().contains("bronze_shovel"))
			 return modelLocation.toString().contains("bronze_shovel");
		 else if(modelLocation.toString().contains("iron_shovel"))
			 return modelLocation.toString().contains("iron_shovel");
		 else if(modelLocation.toString().contains("steel_shovel"))
			 return modelLocation.toString().contains("steel_shovel");
		 else if(modelLocation.toString().contains("arm_steel_shovel"))
			 return modelLocation.toString().contains("arm_steel_shovel");
		 else 
			 return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		 if(modelLocation.toString().contains("stone_shovel"))
		        return new ModelShovel(new ResourceLocation("realism:items/shovel/stone_shovel"));
		 if(modelLocation.toString().contains("copper_shovel"))
		        return new ModelShovel(new ResourceLocation("realism:items/shovel/copper_shovel"));
		 if(modelLocation.toString().contains("bronze_shovel"))
		        return new ModelShovel(new ResourceLocation("realism:items/shovel/bronze_shovel"));
		 if(modelLocation.toString().contains("iron_shovel"))
		        return new ModelShovel(new ResourceLocation("realism:items/shovel/iron_shovel"));
		 if(modelLocation.toString().contains("steel_shovel"))
		        return new ModelShovel(new ResourceLocation("realism:items/shovel/steel_shovel"));
		 if(modelLocation.toString().contains("arm_steel_shovel"))
		        return new ModelShovel(new ResourceLocation("realism:items/shovel/arm_steel_shovel"));
		return null;
	}

}
