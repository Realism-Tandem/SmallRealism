package com.xumuk.realism.packets;

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

    public static SimpleNetworkWrapper network;

    public static void init() {

        network = NetworkRegistry.INSTANCE.newSimpleChannel(RealismCore.MODID);


        network.registerMessage(MonthSyncMessageServer.Handler.class, MonthSyncMessageServer.class, 1, Side.SERVER);
        network.registerMessage(MonthSyncMessage.Handler.class, MonthSyncMessage.class, 2, Side.CLIENT);
        
     //   network.registerMessage(WSDCoordToClient.Handler.class, WSDCoordToClient.class, 5, Side.CLIENT);
        
        network.registerMessage(DaySyncMessageServer.Handler.class, DaySyncMessageServer.class, 3, Side.SERVER);
        network.registerMessage(DaySyncMessage.Handler.class, DaySyncMessage.class, 4, Side.CLIENT);
        
        network.registerMessage(YearSyncMessageServer.Handler.class,YearSyncMessageServer.class, 5, Side.SERVER);
        network.registerMessage(YearSyncMessage.Handler.class, YearSyncMessage.class, 6, Side.CLIENT);
     //   network.registerMessage(PlayerWeightMessage.Handler.class, PlayerWeightMessage.class, 4, Side.CLIENT);
  //      network.registerMessage(PlayerWeightMessageServer.Handler.class, PlayerWeightMessageServer.class, 5, Side.SERVER);
    }


	public void sendTo(final IMessage message, final EntityPlayerMP player) {
		network.sendTo(message, player);
	}

	public void sendToServer(final IMessage message){
		network.sendToServer(message);
	}
}