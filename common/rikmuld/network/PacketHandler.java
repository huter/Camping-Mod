package rikmuld.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import rikmuld.network.packets.PacketCamping;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
    {
        PacketCamping PacketCamping = PacketTypeHandler.buildPacket(packet.data);
        PacketCamping.execute(manager, player);
    }
}