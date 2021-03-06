package rikmuld.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.GuiIds;

public class BackpackHelper {

	public static void doKeyAction(EntityPlayer player, String key) 
	{
		openGui(player.worldObj, player);
	}

	private static void openGui(World world, EntityPlayer player)
	{
		player.openGui(CampingMod.instance, GuiIds.GUICamping, world,  0, 0, 0);
	}
}
