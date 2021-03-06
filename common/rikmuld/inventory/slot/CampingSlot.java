package rikmuld.inventory.slot;

import rikmuld.core.proxys.CommonProxy;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CampingSlot extends Slot {
	
	public boolean noItemsValid = false;
	
	public CampingSlot(IInventory inventory, int slotIndex, int xPos, int yPos) 
	{
		super(inventory, slotIndex, xPos, yPos);
	}

	public boolean isItemValid(ItemStack is) 
	{
		 if(!noItemsValid) return true;
         else return false;
    }
	
	@Override
	public void onSlotChanged()
	{
		this.inventory.onInventoryChanged();
		if(CommonProxy.CampingInv!=null)
		{
			CommonProxy.CampingInv.onInventoryChanged();
		}
	}	
}