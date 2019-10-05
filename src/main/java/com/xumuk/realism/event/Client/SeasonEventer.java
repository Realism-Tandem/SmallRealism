package com.xumuk.realism.event.Client;

import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SeasonEventer {
	
	public Biome.BiomeProperties bp;
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void biomeColorGrass(BiomeEvent.GetGrassColor e) {	
		
		World world = Minecraft.getMinecraft().world;
		IDate date = world.getCapability(DateProvider.DATE, null);
		EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().player;
		
		/**
		 * 0 - winter
		 * 1 - late winter
		 * 2 - early spring
		 * 3 - spring
		 * 4 - late spring
		 * 5 - early summer
		 * 6 - summer
		 * 7 - late summer
		 * 8 - early autumn
		 
		 * 9 - autumn
		 * 10 - late autumn
		 * 11 - early winter
		 */ 
		//Winter
		if(date.getMonth() == 11) { e.setNewColor(0xC2D6C3);
			
		//	
		}
		if(date.getMonth() == 0) {e.setNewColor(0xC2D6C3);
		//		
		}
		if(date.getMonth() == 1) {e.setNewColor(0xE3FCE4);
		//		
		}
		//Spring
		if(date.getMonth() == 2) {e.setNewColor(0xAFEBB2);		
	
		}
		if(date.getMonth() == 3) {e.setNewColor(0x89FF8F);		
		
		}
		if(date.getMonth() == 4) {e.setNewColor(0x60E167);		
		
		}
		//Summer
		if(date.getMonth() == 5) {e.setNewColor(0xDDDD55);		
			
		}
		if(date.getMonth() == 6) {e.setNewColor(0xDCDC47);		
			
			
		}
		if(date.getMonth() == 7) { e.setNewColor(0xB4DC47);		
			
		}
		//Autunm
		if(date.getMonth() == 8) { e.setNewColor(0xBECE0F);	
			
		}
		if(date.getMonth() == 9) {e.setNewColor(0xCFDF1C);		
			
		}
		if(date.getMonth() == 10) {e.setNewColor(0xCED771);		
			
		}

		//		else e.setNewColor(0x60E167);
		//0xDDDD55 - лето
		//0x89FF8F - весна
		//0xBECE0F - осень
		//0xFFFFFF - зима
		//		System.out.println(date.getMonth());


	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void biomeColorWater(BiomeEvent.GetWaterColor e) {


		if (e.getBiome() == Biomes.OCEAN || e.getBiome() == Biomes.DEEP_OCEAN)
		{
			e.setNewColor(0x0517BB);
		} else { e.setNewColor(0xDDDD55); }

	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void biomeColorFoliage(BiomeEvent.GetFoliageColor e) {
		World world = Minecraft.getMinecraft().world;
		IDate date = world.getCapability(DateProvider.DATE, null);

		if (e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS)
		{
			if(date.getMonth() == 11) e.setNewColor(0xFFFFFF);
			if(date.getMonth() == 0) e.setNewColor(0xFFFFFF);
			if(date.getMonth() == 1) e.setNewColor(0xFFFFFF);
			//Spring
			if(date.getMonth() == 2) e.setNewColor(0xAFEBB2);
			if(date.getMonth() == 3) e.setNewColor(0x00FF0D);
			if(date.getMonth() == 4) e.setNewColor(0x60E167);
			//Summer
			if(date.getMonth() == 5) e.setNewColor(0xDDDD55);
			if(date.getMonth() == 6) e.setNewColor(0xDCDC47);
			if(date.getMonth() == 7) e.setNewColor(0xB4DC47);
			//Autunm
			if(date.getMonth() == 8) e.setNewColor(0xFFE269);
			if(date.getMonth() == 9) e.setNewColor(0xEE9200);
			if(date.getMonth() == 10) e.setNewColor(0x700400);
			//0xDDDD55 - лето
			//0x700400 - красная листва
			//0xEE9200 - оранжевая листва
			//0xFFE269 - желтая листва 
			//0x00FF0D - весна
			//0xFFFFFF - зима(по идее листва должна пропадать)
		}
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onBiome(BiomeEvent e)
	{
		World world = Minecraft.getMinecraft().world;
		IDate date = world.getCapability(DateProvider.DATE, null);


		//January
		if(date.getMonth() == 0) 
		{

			if(e.getBiome() == Biomes.BIRCH_FOREST || e.getBiome() == Biomes.BIRCH_FOREST_HILLS|| e.getBiome() == Biomes.COLD_BEACH|| e.getBiome() == Biomes.FOREST) {


				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.0F;	


			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			

			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.9F;			

			}
		}
		//February
		if(date.getMonth() == 1) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.9F;			


			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			
			}
		}
		if(e.getBiome() == Biomes.DEEP_OCEAN) {

			e.getBiome().enableRain = true;
			e.getBiome().temperature = 1.1F;			

		}
		if(e.getBiome() == Biomes.OCEAN) {

			e.getBiome().enableRain = true;
			e.getBiome().temperature = 1.2F;			

		}

		//March
		if(date.getMonth() == 2) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.9F;			

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			


			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.4F;			


			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.5F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.4F;			

			}
		}
		//April
		if(date.getMonth() == 3) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.9F;			


				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			


			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.6F;			


				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.97F;			

			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.65F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.48F;			

			}
		}
		//May
		if(date.getMonth() == 4) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.9F;			

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			


			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.6F;			

			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.65F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.48F;			

			}

			//June
			if(date.getMonth() == 5) 
			{

				if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
						|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
						||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.5F;			


				}
				if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
						|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
						||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.6F;			

				}
				if(e.getBiome() == Biomes.DEEP_OCEAN) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.65F;			

				}
				if(e.getBiome() == Biomes.OCEAN) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.48F;			

				}
			}
			//Jule
			if(date.getMonth() == 6) 
			{

				if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
						|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
						||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.8F;			

				}
				if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
						|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
						||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

					e.getBiome().enableRain = false;
					e.getBiome().temperature = 2F;			

				}
				if(e.getBiome() == Biomes.DEEP_OCEAN) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.7F;			

				}
				if(e.getBiome() == Biomes.OCEAN) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.6F;			

				}
			}
			//August
			if(date.getMonth() == 7) 
			{

				if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
						|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
						||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.8F;			


				}
				if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
						|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
						||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

					e.getBiome().enableRain = false;
					e.getBiome().temperature = 2F;			

				}
				if(e.getBiome() == Biomes.DEEP_OCEAN) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.7F;			

				}
				if(e.getBiome() == Biomes.OCEAN) {

					e.getBiome().enableRain = true;
					e.getBiome().temperature = 1.6F;			
				}
			}
		}
		//Septemper
		if(date.getMonth() == 8) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.8F;			

			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = false;
				e.getBiome().temperature = 2F;			
			}

			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.7F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.6F;			
			}
		}
		//October
		if(date.getMonth() == 9) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.8F;			

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 1.0F;			



			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = false;
				e.getBiome().temperature = 2F;			

			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.7F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.6F;			

			}
		}
		//November
		if(date.getMonth() == 10) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.8F;			

				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 1.0F;			

			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.7F;			

			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.2F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			

			}
		}
		//December
		if(date.getMonth() == 11) 
		{

			if(e.getBiome() != Biomes.DESERT || e.getBiome() != Biomes.DESERT_HILLS ||e.getBiome() != Biomes.MUTATED_DESERT
					|| e.getBiome() != Biomes.JUNGLE || e.getBiome() != Biomes.JUNGLE_HILLS ||e.getBiome() != Biomes.JUNGLE_EDGE
					||e.getBiome() != Biomes.MUTATED_JUNGLE||e.getBiome() != Biomes.MUTATED_JUNGLE_EDGE) {


				e.getBiome().enableSnow = true;
				e.getBiome().temperature = 0.7F;			

			}
			if(e.getBiome() == Biomes.DESERT || e.getBiome() == Biomes.DESERT_HILLS ||e.getBiome() == Biomes.MUTATED_DESERT
					|| e.getBiome() == Biomes.JUNGLE || e.getBiome() == Biomes.JUNGLE_HILLS ||e.getBiome() == Biomes.JUNGLE_EDGE
					||e.getBiome() == Biomes.MUTATED_JUNGLE||e.getBiome() == Biomes.MUTATED_JUNGLE_EDGE) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.2F;			

			}
			if(e.getBiome() == Biomes.DEEP_OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.2F;			

			}
			if(e.getBiome() == Biomes.OCEAN) {

				e.getBiome().enableRain = true;
				e.getBiome().temperature = 1.1F;			
			}
		}

	}



}
