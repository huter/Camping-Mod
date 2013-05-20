package rikmuld.core.handlers;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rikmuld.core.helper.CampingArmorHelper;
import rikmuld.core.lib.ModInfo;
import rikmuld.core.proxys.CommonProxy;
import rikmuld.core.register.ModEvents;
import rikmuld.core.register.ModItems;
import rikmuld.item.normal.GuideBook;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler{
	
	static boolean dropItems = true;
	static Minecraft mc;
	public static NBTTagCompound campInv;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		if(type.equals(EnumSet.of(TickType.PLAYER)))	
		{
			EntityPlayer player = (EntityPlayer)tickData[0];
			
			if(player.getCurrentArmor(3)!=null && player.getCurrentArmor(3).getItem() == ModItems.CampingArmorHelm) CampingArmorHelper.applyHelmEffects(player);
			if(player.getCurrentArmor(2)!=null && player.getCurrentArmor(2).getItem() == ModItems.CampingArmorChest) CampingArmorHelper.applyChestEffects(player);
			if(player.getCurrentArmor(1)!=null && player.getCurrentArmor(1).getItem() == ModItems.CampingArmorLeg) CampingArmorHelper.applyLegEffects(player);
			else
			{
				player.stepHeight = 0; 
			}
			if(player.getCurrentArmor(0)!=null && player.getCurrentArmor(0).getItem() == ModItems.CampingArmorBoot) CampingArmorHelper.applyBootEffects(player);
			else
			{
				ModEvents.decreaseFallDamage(0.0F);
			}
				
		}
	
		if(type.equals(EnumSet.of(TickType.RENDER)))	
		{
			if(FMLClientHandler.instance().getClient().thePlayer!=null)
			{
				EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
				ItemStack currentItem = player.getCurrentEquippedItem();
				if(currentItem!=null)
				{
					if(currentItem.itemID == ModItems.guideBook.itemID)
					{
						if(GuideBook.isGuiOpen)
						{
							if(CommonProxy.guideCamp!=null && currentItem.getItemDamage()==0)CommonProxy.guideCamp.updateTick();
							if(CommonProxy.guideTent!=null && currentItem.getItemDamage()==1)CommonProxy.guideTent.updateTick();
							if(CommonProxy.guideEquipment!=null && currentItem.getItemDamage()==2)CommonProxy.guideEquipment.updateTick();
							if(CommonProxy.guideFood!=null && currentItem.getItemDamage()==3)CommonProxy.guideFood.updateTick();
							if(CommonProxy.guideWorld!=null && currentItem.getItemDamage()==4)CommonProxy.guideWorld.updateTick();
						}
					}	
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{

	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.PLAYER,TickType.RENDER);
	}

	@Override
	public String getLabel()
	{
		 return ModInfo.MOD_ID + ": " + this.getClass().getSimpleName();
	}

}
