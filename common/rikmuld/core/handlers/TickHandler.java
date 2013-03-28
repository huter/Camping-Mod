package rikmuld.core.handlers;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import rikmuld.client.gui.screen.GuiGuide;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.ModInfo;
import rikmuld.core.register.ModItems;
import rikmuld.item.normal.GuideBook;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler{

	static Minecraft mc;
	private int update;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
		Item currentItem = player.getCurrentEquippedItem().getItem();
		if(currentItem.itemID == ModItems.guideBook.itemID)
		{
			if(GuideBook.isGuiOpen)
			{
				// TODO: make the GuiGuide update every 10 sec
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
