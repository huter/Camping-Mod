package rikmuld.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rikmuld.core.helper.VersionHelper;
import rikmuld.core.proxys.CommonProxy;
import rikmuld.core.register.ModItems;
import rikmuld.inventory.inventory.InventoryCamping;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerHandler implements IPlayerTracker{

	public NBTTagCompound inventotyCamp = null;
	public NBTTagCompound InventotyCraft = null;

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{	
		CommonProxy.CampingInv = new InventoryCamping(player);
		
		this.inventotyCamp = player.getEntityData().getCompoundTag("CampingInventory");
		this.InventotyCraft = player.getEntityData().getCompoundTag("CampingCraftInventory");
		
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
		boolean keepInv = player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory");
		
		if(!keepInv)
		{
			player.getEntityData().setCompoundTag("CampingInventory", new NBTTagCompound());
			player.getEntityData().setCompoundTag("CampingCraftInventory", new NBTTagCompound());	
		}
		else
		{
			player.getEntityData().setCompoundTag("CampingInventory", inventotyCamp);
			player.getEntityData().setCompoundTag("CampingCraftInventory", InventotyCraft);	
		}
		
		CommonProxy.CampingInv = new InventoryCamping(player);
		player.getEntityData().setBoolean("NotFirstLoggedin", true);
	}

}
