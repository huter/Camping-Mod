package rikmuld.core.handlers;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rikmuld.core.lib.ModInfo;
import rikmuld.core.proxys.CommonProxy;
import rikmuld.core.register.ModItems;
import rikmuld.item.normal.GuideBook;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler{
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
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
							if(CommonProxy.guideCamp!=null)CommonProxy.guideCamp.updateTick();
							if(CommonProxy.guideTent!=null)CommonProxy.guideTent.updateTick();
							if(CommonProxy.guideEquipment!=null)CommonProxy.guideEquipment.updateTick();
							if(CommonProxy.guideFood!=null)CommonProxy.guideFood.updateTick();
							if(CommonProxy.guideWorld!=null)CommonProxy.guideWorld.updateTick();
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
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel()
	{
		 return ModInfo.MOD_ID + ": " + this.getClass().getSimpleName();
	}

}
