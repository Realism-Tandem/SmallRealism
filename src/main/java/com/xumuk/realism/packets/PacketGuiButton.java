package com.xumuk.realism.packets;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.utils.IButtonHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGuiButton implements IMessage {

	private int idX;
	private int idY;

	public PacketGuiButton() {}

	public PacketGuiButton(int idX, int idY) {
		this.idX = idX;
		this.idY = idY;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		idX = tag.getInteger("idX");
		idY = tag.getInteger("idY");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("idX", idX);
		tag.setInteger("idY", idY);
		ByteBufUtils.writeTag(buf, tag);
	}

	public static class Handler implements IMessageHandler<PacketGuiButton, IMessage> {
		@Override
		public IMessage onMessage(PacketGuiButton message, MessageContext ctx) {
			try {
				EntityPlayer player = RealismCore.proxy.getPlayer(ctx);
				if (player != null) {
					RealismCore.proxy.getThreadListener(ctx).addScheduledTask(() -> {
						if (player.openContainer instanceof IButtonHandler) {
							((IButtonHandler) player.openContainer).onButtonPress(message.idX, message.idY);
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}