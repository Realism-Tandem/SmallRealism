package com.xumuk.realism.packets;

import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class MonthSyncMessage extends SRSimplePacket {

	public MonthSyncMessage() {}

	public MonthSyncMessage(byte month) {
		buf().writeByte(month);
	}

	@Override
	public void client(EntityPlayer player) {
		if (player != null) {
			IDate cap = player.getEntityWorld().getCapability(DateProvider.DATE, null);
			if (cap != null) cap.setMonth(buf().readByte());
		}
	}

	@Override
	public void server(EntityPlayerMP player) {
		IDate date = player.getEntityWorld().getCapability(DateProvider.DATE, null);
		if (date != null) date.setMonth(buf().readByte());

	}
}
