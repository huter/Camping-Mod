package rikmuld.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rikmuld.core.proxys.CommonProxy;
import rikmuld.item.tool.ToolCampingV2;

public class CamperToolOnlySlot extends Slot {
	
	public boolean noItemsValid = false;
	public String[] validOtherMod = 
	{
		"item.stoneMinium", 
		"item.stonePhilosophers"
	};
	
	public CamperToolOnlySlot(IInventory inventory, int slotIndex, int xPos, int yPos) 
	{
		super(inventory, slotIndex, xPos, yPos);
	}

	public boolean isItemValid(ItemStack is) 
	{
		if(!noItemsValid)
		{
			for(String item : validOtherMod) 
			{
				if (is != null &&(is.getItem() instanceof ToolCampingV2 || is.getItem().getUnlocalizedName().equals(item))) return true;
			}
		}	
        return false;
    }
}