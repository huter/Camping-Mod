package rikmuld.core.handlers;

import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rikmuld.core.helper.VersionHelper;
import rikmuld.core.register.ModItems;
import rikmuld.core.register.ModLogger;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerHandler implements IPlayerTracker{

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{	
		VersionHelper.execute(player);
		
		NBTTagCompound playerNBT = player.getEntityData();
		
		ModLogger.logDebug("a");
		if(!playerNBT.getBoolean("NotFirstLoggedin"))
		{
			ModLogger.logDebug("b");
			for(int damage = 0; damage<5; damage++)
			{
				ModLogger.logDebug("c");
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.guideBook, 1, damage));
			}
			ModLogger.logDebug("d");
			playerNBT.setBoolean("NotFirstLoggedin", true);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
	
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{
		
	}

}
