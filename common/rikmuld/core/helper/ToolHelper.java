package rikmuld.core.helper;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public class ToolHelper {

	public static ArrayList<Integer> tools = new ArrayList();
	
	public static void addTool(int id)
	{		
		tools.add(id);
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
