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

public class NetworkHandler {

	private static short id;

	public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(RealismCore.MODID);

	public static void init() {
		// network.registerMessage(WSDCoordToClient.Handler.class,
		// WSDCoordToClient.class, 5, Side.CLIENT);

		NETWORK.registerMessage(MonthSyncMessageServer.Handler.class, MonthSyncMessageServer.class, id++, SERVER);
		NETWORK.registerMessage(MonthSyncMessage.Handler.class, MonthSyncMessage.class, id++, CLIENT);
        
		NETWORK.registerMessage(DaySyncMessageServer.Handler.class, DaySyncMessageServer.class, id++, SERVER);
        NETWORK.registerMessage(DaySyncMessage.Handler.class, DaySyncMessage.class, id++, CLIENT);
        
        NETWORK.registerMessage(YearSyncMessageServer.Handler.class,YearSyncMessageServer.class, id++, SERVER);
        NETWORK.registerMessage(YearSyncMessage.Handler.class, YearSyncMessage.class, id++, CLIENT);
        
        NETWORK.registerMessage(new PacketGuiButton.Handler(), PacketGuiButton.class, id++, SERVER);
		
		// network.registerMessage(PlayerWeightMessage.Handler.class,
		// PlayerWeightMessage.class, 4, Side.CLIENT);
		// network.registerMessage(PlayerWeightMessageServer.Handler.class,
		// PlayerWeightMessageServer.class, 5, Side.SERVER);
	}
	
    public static void sendToAll(final IMessage packet) {
        NETWORK.sendToAll(packet);
    }

	public static void sendTo(final IMessage message, final EntityPlayerMP player) {
		NETWORK.sendTo(message, player);
	}

	public static void sendToServer(final IMessage message) {
		NETWORK.sendToServer(message);
	}
}