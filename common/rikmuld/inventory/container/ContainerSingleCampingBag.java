package rikmuld.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rikmuld.inventory.inventory.InventorySingleCampingBag;
import rikmuld.inventory.slot.BackpackSlot;
import rikmuld.item.normal.Backpack;

public class ContainerSingleCampingBag extends Container {
	
	InventorySingleCampingBag backInv;
	ItemStack backpack;
	
	public ContainerSingleCampingBag(IInventory playerInventory, InventorySingleCampingBag backpackInventory, ItemStack theBackpack) {

		backpackInventory.openChest();
		
		int var3;
	
		if(theBackpack.getItemDamage()==0)
		{
			for (int row = 0; row < 3; ++row) for (int col = 0; col < 3; ++col) 
			{
				this.addSlotToContainer(new BackpackSlot(backpackInventory, col + row * 3+1, 62 + col * 18, 18 + row * 18));
			}
		}
		else if(theBackpack.getItemDamage()==1)
		{
			for (int row = 0; row < 3; ++row) for (int col = 0; col < 6; ++col) 
			{
				this.addSlotToContainer(new BackpackSlot(backpackInventory, col + row * 6+1, 36 + col * 18, 18 + row * 18));
			}
		}
		else if(theBackpack.getItemDamage()==2)
		{
			for (int row = 0; row < 3; ++row) for (int col = 0; col < 9; ++col) 
			{
				this.addSlotToContainer(new BackpackSlot(backpackInventory, col + row * 9+1, 8 + col * 18, 18 + row * 18));
			}
		}

		
		for (var3 = 0; var3 < 3; ++var3) 
		{
			for (int var4 = 0; var4 < 9; ++var4) 
			{
				this.addSlotToContainer(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) 
		{
			this.addSlotToContainer(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
		}

		
		backInv = backpackInventory;
		backpack = theBackpack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int i) 
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack() && slot.getStack().getItem() instanceof Backpack) 
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (i < backInv.getInventorySize(backpack)) 
			{
				if (!mergeItemStack(itemstack1, backInv.getInventorySize(backpack), inventorySlots.size(), true)) 
				{
					return null;
				}
			} 
			
			else if (!mergeItemStack(itemstack1, 0, backInv.getInventorySize(backpack), false)) 
			{
				return null;
			}
			
			if (itemstack1.stackSize == 0) 
			{
				slot.putStack(null);
			} 
			
			else 
			{
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
}