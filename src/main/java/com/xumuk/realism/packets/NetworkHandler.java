package com.xumuk.realism.packets;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;
import static net.minecraftforge.fml.relauncher.Side.SERVER;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.packets.Client.DaySyncMessage;
import com.xumuk.realism.packets.Client.MonthSyncMessage;
import com.xumuk.realism.packets.Client.YearSyncMessage;
import com.xumuk.realism.packets.Server.DaySyncMessageServer;
import com.xumuk.realism.packets.Server.MonthSyncMessageServer;
import com.xumuk.realism.packets.Server.YearSyncMessageServer;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

	private short id;

	public final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(RealismCore.MODID);

	public void init() throws Exception {
		// network.registerMessage(WSDCoordToClient.Handler.class,
		// WSDCoordToClient.class, 5, Side.CLIENT);

		NETWORK.registerMessage(MonthSyncMessageServer.Handler.class, MonthSyncMessageServer.class, 1, SERVER);
		NETWORK.registerMessage(MonthSyncMessage.Handler.class, MonthSyncMessage.class, 2, CLIENT);
        
		NETWORK.registerMessage(DaySyncMessageServer.Handler.class, DaySyncMessageServer.class, 3, SERVER);
        NETWORK.registerMessage(DaySyncMessage.Handler.class, DaySyncMessage.class, 4, CLIENT);
        
        NETWORK.registerMessage(YearSyncMessageServer.Handler.class,YearSyncMessageServer.class, 5, SERVER);
        NETWORK.registerMessage(YearSyncMessage.Handler.class, YearSyncMessage.class, 6, CLIENT);
        
		registerMessage(PacketGuiButton.class, SERVER);
		
		// network.registerMessage(PlayerWeightMessage.Handler.class,
		// PlayerWeightMessage.class, 4, Side.CLIENT);
		// network.registerMessage(PlayerWeightMessageServer.Handler.class,
		// PlayerWeightMessageServer.class, 5, Side.SERVER);
	}

	@SuppressWarnings("unused")
	private void registerMessage(Class<? extends SRSimplePacket> packet) throws Exception {
		NETWORK.registerMessage(packet.newInstance(), packet, id++, SERVER);
		NETWORK.registerMessage(packet.newInstance(), packet, id++, CLIENT);
	}
	
	private void registerMessage(Class<? extends SRSimplePacket> packet, Side side) throws Exception {
		NETWORK.registerMessage(packet.newInstance(), packet, id++, side);
	}
	
    public void sendToAll(final IMessage packet) {
        NETWORK.sendToAll(packet);
    }

	public void sendTo(final IMessage message, final EntityPlayerMP player) {
		NETWORK.sendTo(message, player);
	}

	public void sendToServer(final IMessage message) {
		NETWORK.sendToServer(message);
	}
}