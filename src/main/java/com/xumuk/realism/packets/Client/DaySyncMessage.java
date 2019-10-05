package com.xumuk.realism.packets.Client;

import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DaySyncMessage implements IMessage {  
	public byte day;  
	public DaySyncMessage(){}

	public DaySyncMessage(byte value){  
		this.day = value;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		day = buf.readByte();   
	}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeByte(day); 
	}   

	public static class Handler implements IMessageHandler<DaySyncMessage, IMessage> {

		@Override
		public IMessage onMessage(DaySyncMessage message, MessageContext ctx) { 

			EntityPlayerSP player = Minecraft.getMinecraft().player;   
			if(player != null) {
				IDate cap = player.getEntityWorld().getCapability(DateProvider.DATE, null);   
				if(cap != null)    {
					cap.setDay(message.day);  
				}
			}
				return null;    
			} 
		}
	}