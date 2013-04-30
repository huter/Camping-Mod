package rikmuld.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rikmuld.core.helper.VersionHelper;
import rikmuld.core.register.ModItems;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerHandler implements IPlayerTracker{

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{	
		VersionHelper.execute(player);
		
		NBTTagCompound playerNBT = player.getEntityData();
		
		if(!playerNBT.getBoolean("NotFirstLoggedin"))
		{
			for(int damage = 0; damage<5; damage++)
			{
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.guideBook, 1, damage));
			}
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
		TickHandler.dropItems = true;
	}

}
