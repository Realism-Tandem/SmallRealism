package com.xumuk.realism.packets;

import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class YearSyncMessage extends SRSimplePacket {
	public YearSyncMessage() {}

	public YearSyncMessage(int year) {
		buf().writeInt(year);
	}

	@Override
	public void client(EntityPlayer player) {
		if (player != null) {
			IDate cap = player.getEntityWorld().getCapability(DateProvider.DATE, null);
			if (cap != null) { cap.setYear(buf().readInt()); }
		}
	}

	@Override
	public void server(EntityPlayerMP player) {
		IDate date = player.getEntityWorld().getCapability(DateProvider.DATE, null);
		if (date != null) date.setYear(buf().readInt());
	}
}