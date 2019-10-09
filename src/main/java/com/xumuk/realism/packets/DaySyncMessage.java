package com.xumuk.realism.packets;

import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DaySyncMessage extends SRSimplePacket {

	public DaySyncMessage() {}

	public DaySyncMessage(byte day) {
		buf().writeByte(day);
	}

	@Override
	public void client(EntityPlayer player, MessageContext ctx) {
		if (player != null) {
			IDate cap = player.getEntityWorld().getCapability(DateProvider.DATE, null);
			if (cap != null) { cap.setDay(buf().readByte()); }
		}
	}

	@Override
	public void server(EntityPlayerMP player, MessageContext ctx) {
		IDate date = player.getEntityWorld().getCapability(DateProvider.DATE, null);
		if (date != null) date.setDay(buf().readByte());
	}
}