package rikmuld.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import rikmuld.core.helper.KeyHelper;
import rikmuld.item.CampingItem;
import rikmuld.item.CampingItemArmor;
import rikmuld.item.tool.ToolBackpack;
import rikmuld.network.PacketTypeHandler;
import cpw.mods.fml.common.network.Player;

public class PacketKeyPressed extends PacketCamping {

	    public String key;

	    public PacketKeyPressed() 
	    {
	        super(PacketTypeHandler.KEY, false);
	    }

	    public PacketKeyPressed(String key) 
	    {
	        super(PacketTypeHandler.KEY, false);
	        this.key = key;
	    }

	    @Override
	    public void writeData(DataOutputStream data) throws IOException
	    {
	        data.writeUTF(key);
	    }

	    @Override
	    public void readData(DataInputStream data) throws IOException
	    {
	        key = data.readUTF();
	    }

	    @Override
	    public void execute(INetworkManager manager, Player player) 
	    {
	        EntityPlayer thePlayer = (EntityPlayer) player;

	        if (thePlayer.getCurrentEquippedItem() != null && KeyHelper.hasKeyBind(thePlayer.getCurrentEquippedItem())) 
	        {
	            if(thePlayer.getCurrentEquippedItem().getItem() instanceof CampingItemArmor)((CampingItemArmor) thePlayer.getCurrentEquippedItem().getItem()).doKeyAction(thePlayer, thePlayer.getCurrentEquippedItem(), key);
	            if(thePlayer.getCurrentEquippedItem().getItem() instanceof CampingItem)((CampingItem) thePlayer.getCurrentEquippedItem().getItem()).doKeyAction(thePlayer, thePlayer.getCurrentEquippedItem(), key);
	        }
	        
			if((thePlayer.getCurrentEquippedItem() == null ||!(thePlayer.getCurrentEquippedItem().getItem() instanceof ToolBackpack)) && thePlayer.inventory.getStackInSlot(38).getItem() instanceof ToolBackpack)
			{
				((CampingItemArmor) thePlayer.inventory.getStackInSlot(38).getItem()).doKeyAction(thePlayer, thePlayer.inventory.getStackInSlot(38), key);
			}
	    }
}

