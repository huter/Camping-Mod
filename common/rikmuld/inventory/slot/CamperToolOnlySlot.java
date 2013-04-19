package rikmuld.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rikmuld.item.tool.ToolCampingV2;

public class CamperToolOnlySlot extends Slot {
	
	public CamperToolOnlySlot(IInventory inventory, int slotIndex, int xPos, int yPos) 
	{
		super(inventory, slotIndex, xPos, yPos);
	}

	public boolean isItemValid(ItemStack is) 
	{
        return (is != null && is.getItem() instanceof ToolCampingV2) ? true : false;
    }
}