package com.xumuk.realism.packets;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;
import static net.minecraftforge.fml.relauncher.Side.SERVER;

import com.xumuk.realism.RealismCore;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkHandler {

	private short id;

	public final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(RealismCore.MODID);

	public void init() {
		// network.registerMessage(WSDCoordToClient.Handler.class,
		// WSDCoordToClient.class, 5, Side.CLIENT);

		registerMessage(DaySyncMessage.class);
		registerMessage(MonthSyncMessage.class);
		registerMessage(YearSyncMessage.class);
		
		// network.registerMessage(PlayerWeightMessage.Handler.class,
		// PlayerWeightMessage.class, 4, Side.CLIENT);
		// network.registerMessage(PlayerWeightMessageServer.Handler.class,
		// PlayerWeightMessageServer.class, 5, Side.SERVER);
	}

	private void registerMessage(Class<? extends SRSimplePacket> packet) {
		try {
			NETWORK.registerMessage(packet.newInstance(), packet, id++, SERVER);
			NETWORK.registerMessage(packet.newInstance(), packet, id++, CLIENT);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
    public void sendToAll(SRSimplePacket packet) {
        NETWORK.sendToAll(packet);
    }

	public void sendTo(final IMessage message, final EntityPlayerMP player) {
		NETWORK.sendTo(message, player);
	}

	public void sendToServer(final IMessage message) {
		NETWORK.sendToServer(message);
	}
}