package rikmuld.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rikmuld.core.helper.ItemStackHelper;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.register.ModAchievements;
import rikmuld.core.register.ModBlocks;
import rikmuld.core.register.ModItems;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftHandler implements ICraftingHandler{

	ItemStack campfire[] = ItemStackHelper.getMetaCycle(ModBlocks.campfire, 5);
	ItemStack bag[] = ItemStackHelper.getMetaCycle(ModItems.CampingBag, 3);
	ItemStack tent = new ItemStack(ModBlocks.tent, 1, 0);
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory inv) 
	{
		if (item.itemID == ModItems.Marshmallow.itemID)
        {
			if(item.getItemDamage() == 0)
			{
				ItemStack bowl = new ItemStack(Item.bowlEmpty);
				player.inventory.addItemStackToInventory(bowl);
			} 
        }
		
		for(int i=0; i < inv.getSizeInventory(); i++)
        {  		
            if(inv.getStackInSlot(i) != null)
            {
                ItemStack j = inv.getStackInSlot(i);
                if(j.getItem() != null&&ToolHelper.isTool(j)&&!ToolHelper.isTool(item))
                {
                	 ItemStack k = ToolHelper.addDamage(j, player);
                	 if(k!=null)k.stackSize++;
                     inv.setInventorySlotContents(i, k);
                }
             }      
        }
		
		if(item.itemID == ModBlocks.campfire.blockID)
		{
			if (item.getItemDamage() == campfire[0].getItemDamage()) player.addStat(ModAchievements.Campfire, 1);
			if (item.getItemDamage() == campfire[1].getItemDamage()) player.addStat(ModAchievements.CampfireMultiCook, 1);
			if (item.getItemDamage() == campfire[2].getItemDamage()) player.addStat(ModAchievements.CampfireFastCook, 1);
			if (item.getItemDamage() == campfire[3].getItemDamage()) player.addStat(ModAchievements.CampfireCheapCook, 1);
			if (item.getItemDamage() == campfire[4].getItemDamage()) player.addStat(ModAchievements.CampfireInstaCook, 1);	
		}
		
		if(item.itemID == ModItems.CampingBag.itemID)
		{
			if (item.getItemDamage() == bag[0].getItemDamage()) player.addStat(ModAchievements.CampingBagSmall, 1);
			if (item.getItemDamage() == bag[1].getItemDamage()) player.addStat(ModAchievements.CampingBagNormal, 1);
			if (item.getItemDamage() == bag[2].getItemDamage()) player.addStat(ModAchievements.CampingBagLarge, 1);	
		}
		
		if (item.itemID == ModItems.CampTool.itemID) player.addStat(ModAchievements.CampersTool, 1);
		if (item.itemID == ModItems.TentParts.itemID) player.addStat(ModAchievements.TentParts, 1);
		if (item.itemID == tent.itemID) player.addStat(ModAchievements.Tent, 1);
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) 
	{
	
	}

}
