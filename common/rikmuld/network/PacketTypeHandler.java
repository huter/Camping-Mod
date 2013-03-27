package rikmuld.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import rikmuld.core.lib.ModInfo;
import rikmuld.network.packets.PacketCamping;
import rikmuld.network.packets.PacketKeyPressed;

	public enum PacketTypeHandler 
	{
	    KEY(PacketKeyPressed.class);

	    private Class<? extends PacketCamping> clazz;
	   
	    PacketTypeHandler(Class<? extends PacketCamping> clazz) 
	    {
	    	this.clazz = clazz;
	    }

	    public static PacketCamping buildPacket(byte[] data) 
	    {

	        ByteArrayInputStream bis = new ByteArrayInputStream(data);
	        int selector = bis.read();
	        DataInputStream dis = new DataInputStream(bis);

	        PacketCamping packet = null;

	        try {
	            packet = values()[selector].clazz.newInstance();
	        }
	        catch (Exception e) {
	            e.printStackTrace(System.err);
	        }

	        packet.readPopulate(dis);
	        return packet;
	    }

	    public static PacketCamping buildPacket(PacketTypeHandler type) 
	    {
	        PacketCamping packet = null;

	        try {
	            packet = values()[type.ordinal()].clazz.newInstance();
	        }
	        catch (Exception e) {
	            e.printStackTrace(System.err);
	        }

	        return packet;
	    }

	    public static Packet populatePacket(PacketCamping CampingPacket)
	    {
	        byte[] data = CampingPacket.populate();

	        Packet250CustomPayload packet250 = new Packet250CustomPayload();
	        packet250.channel = ModInfo.PACKET_CHANEL;
	        packet250.data = data;
	        packet250.length = data.length;
	        packet250.isChunkDataPacket = CampingPacket.isChunkDataPacket;

	        return packet250;
	    }
}

