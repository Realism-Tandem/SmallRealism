package com.xumuk.realism.packets;

import javax.annotation.Nullable;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.utils.IButtonHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGuiButton extends SRSimplePacket {

	public PacketGuiButton(int buttonID, @Nullable NBTTagCompound extraNBT) {
		buf().writeInt(buttonID);
		buf().writeBoolean(extraNBT != null);
		if (extraNBT != null) ByteBufUtils.writeTag(buf(), extraNBT);
	}

	public PacketGuiButton(int buttonID) {
		this(buttonID, null);
	}

	@Override
	public void client(EntityPlayer player, MessageContext ctx) {}

	@Override
	public void server(EntityPlayerMP player, MessageContext ctx) {
		if (player != null) {
			try {
				RealismCore.proxy.getThreadListener(ctx).addScheduledTask(() -> {
					if (player.openContainer instanceof IButtonHandler) {
						((IButtonHandler) player.openContainer).onButtonPress(buf().readInt(),
								buf().readBoolean() ? ByteBufUtils.readTag(buf()) : null);
					}
				});
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
}