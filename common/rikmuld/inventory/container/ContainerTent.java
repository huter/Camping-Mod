package rikmuld.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rikmuld.tileentity.TileEntityTent;

public class ContainerTent extends Container {
	
	private TileEntityTent tent;

	public ContainerTent(InventoryPlayer par1InventoryPlayer, TileEntityTent par2TileEntityTent) 
	{
		tent = par2TileEntityTent;
		int var3;
		int x = 0;

		for (var3 = 0; var3 < 5; ++var3) 
		{
			for (int var4 = 0; var4 < 11; ++var4) 
			{
				this.addSlotToContainer(new Slot(par2TileEntityTent, x, -10 + var4 * 18, -2 + var3 * 18));
				x++;
			}
		}

		for (var3 = 0; var3 < 3; ++var3) 
		{
			for (int var4 = 0; var4 < 9; ++var4) 
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 93 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) 
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 151));
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) 
	{
		return this.tent.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int i) 
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) 
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (i < 55) 
			{
				if (!mergeItemStack(itemstack1, 55, inventorySlots.size(), true)) 
				{
					return null;
				}
			} 
			
			else if (!mergeItemStack(itemstack1, 0, 55, false)) 
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