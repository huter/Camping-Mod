package rikmuld.core.helper;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
public class ToolHelper {

	public static ArrayList<Integer> tools = new ArrayList<Integer>();
	public static ArrayList<ItemStack[]> toolStacks = new ArrayList<ItemStack[]>();
	
	public static void addTool(Item item)
	{		
		tools.add(item.itemID);
		ItemStack[] theTool = ItemStackHelper.getMetaCycle(item, item.getMaxDamage());
		toolStacks.add(theTool);
	}
	
	public static boolean isTool(ItemStack item)
	{		
		if (tools.contains(item.itemID)) return true;
		else return false;
	}

	public static ItemStack addDamage(ItemStack currentitem, EntityPlayer player) 
	{
         ItemStack k = new ItemStack(currentitem.itemID, 1, (currentitem.getItemDamage() + 1));
         if (k.getItemDamage()>=currentitem.getMaxDamage())
         {
        	 k=null;
         }
         return k;
	}
}
