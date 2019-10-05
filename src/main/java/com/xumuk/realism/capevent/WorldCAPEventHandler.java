package com.xumuk.realism.capevent;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.capability.worldCAP.DateProvider;

import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldCAPEventHandler {

	@SubscribeEvent
	public void onWorldDate(AttachCapabilitiesEvent<World> e)
	{
		if (e.getObject() != null)
			e.addCapability(RealismCore.DATE, new DateProvider()); 
	}
}
/*

		count++;
		if (e.phase != e.phase.END) { return; }	    	
		if (e.world != null)
		{
			IDate date = e.world.getCapability(DateProvider.DATE, null);
			if(e.world.getTotalWorldTime() % 24000 == 0 && count % 6 == 0) 
			{//24000
				day = (byte)(date.getDay() + 1);
				date.setDay(day);
				NetworkHandler.network.sendToAll(new MonthSyncMessage(date.getMonth()));
			}
			if(e.world.getTotalWorldTime() % 24000 == 0 && count % 6 == 0) 
			{
				if(date.getDay() % 30 == 0 && count % 6 == 0) 
				{
					month = (byte)(date.getMonth() + 1);
					date.setMonth(month);
					date.setDay((byte)0);
					NetworkHandler.network.sendToAll(new MonthSyncMessage(date.getMonth()));

				}
			}

			if(count % 6 == 0) {
				switch(date.getMonth()) {
				case 11: 
					if(e.world.getTotalWorldTime() % 24000 == 0 && count % 6 == 0) 
					{
						year = (short)(date.getYear() + 1);
						date.setYear(year);
						date.setMonth((byte)0);
						NetworkHandler.network.sendToAll(new MonthSyncMessage(date.getMonth()));
					}
					break;
				default: 
					break;
				}
			}

		}
	}
}*/
