package rikmuld.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rikmuld.item.armor.ArmorCampingBag;

public class BackpackNoSlot extends Slot {
	
	public boolean noItemsValid = false;
	
	public BackpackNoSlot(IInventory inventory, int slotIndex, int xPos, int yPos) 
	{
		super(inventory, slotIndex, xPos, yPos);
	}

	public boolean isItemValid(ItemStack is) 
	{
		 if(!noItemsValid) return (is != null && is.getItem() instanceof ArmorCampingBag) ? false : true;
         else return false;
    }
}