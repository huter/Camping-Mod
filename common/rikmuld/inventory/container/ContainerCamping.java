package rikmuld.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import rikmuld.core.register.ModItems;
import rikmuld.inventory.inventory.InventoryCamping;
import rikmuld.inventory.slot.BackpackNoSlot;
import rikmuld.inventory.slot.BackpackOnlySlot;
import rikmuld.inventory.slot.CamperToolOnlySlot;
import rikmuld.inventory.slot.CampingSlot;

public class ContainerCamping extends Container {
	
	public ItemStack backpack;
	public World worldObj;
	public EntityPlayer player;
	public InventoryCamping campingInv;
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public InventoryCrafting craftMatrix2 = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    public IInventory craftResult2 = new InventoryCraftResult();
    
	public ContainerCamping(InventoryPlayer playerInventory,  InventoryCamping campInv) {
		
		this.worldObj = playerInventory.player.worldObj;
		this.player = playerInventory.player;
		
		campingInv = campInv;
		
		this.addSlotToContainer(new BackpackOnlySlot(campingInv, 0, 216, 8));
		this.addSlotToContainer(new CamperToolOnlySlot(campingInv, 1, 91, 8));
		
		for (int row = 0; row < 3; ++row) for (int col = 0; col < 9; ++col) 
		{
			BackpackNoSlot slot = new BackpackNoSlot(campingInv, col + row * 9+2, 82 + col * 18, 31 + row * 18);
			slot.noItemsValid = true;
			campingInv.backpackSlots.add(slot);
			this.addSlotToContainer(slot);
		}
		
		this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 29, 104));
		this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix2, this.craftResult2, 0, 282, 104));	
		
		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 3; ++i1)
            {
            	CampingSlot slot = new CampingSlot(this.craftMatrix, i1 + l * 3, 10 + i1 * 18, 10 + l * 18);
            	slot.noItemsValid = true;
            	campingInv.craftingSlots.add(slot);
                this.addSlotToContainer(slot);
            }
        }
		
		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 3; ++i1)
            {
            	CampingSlot slot = new CampingSlot(this.craftMatrix2, i1 + l * 3, 263 + i1 * 18, 10 + l * 18);
            	slot.noItemsValid = true;
            	campingInv.craftingSlots.add(slot);
                this.addSlotToContainer(slot);
            }
        }
		
        this.onCraftMatrixChanged(this.craftMatrix);
        this.onCraftMatrixChanged(this.craftMatrix2);
		
		
		for (int var3 = 0; var3 < 3; ++var3) 
		{
			for (int var4 = 0; var4 < 9; ++var4) 
			{
				this.addSlotToContainer(new Slot(playerInventory, var4 + var3 * 9 + 9, 82 + var4 * 18, 97 + var3 * 18));
			}
		}

		for (int var3 = 0; var3 < 9; ++var3) 
		{
			this.addSlotToContainer(new Slot(playerInventory, var3, 82 + var3 * 18, 155));
		}
		
		campInv.craftMatrix = craftMatrix;
		campInv.craftMatrix2 = craftMatrix2;
		
		campingInv.containerExsists = true;
		campingInv.openChest();
	}
	
	public void onCraftMatrixChanged(IInventory par1IInventory)
    {
		campingInv.onInventoryChanged();
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
        this.craftResult2.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix2, this.worldObj));
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
		
		int startNum = 2;
		int endNum = 2;
		
		if(campingInv.getStackInSlot(0)!=null)endNum = ((campingInv.getStackInSlot(0).getItemDamage()+1)*9)+2;
		
		Slot slot = (Slot) inventorySlots.get(i);
		
		if (slot != null && slot.getHasStack()) 
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(campingInv.getStackInSlot(1)==null&&itemstack1.getItem()==ModItems.CampTool2) startNum = 1;
			if(campingInv.getStackInSlot(0)==null&&itemstack1.getItem()==ModItems.CampingBag) startNum = 0;
			if(campingInv.getStackInSlot(0)==null&&itemstack1.getItem()==ModItems.CampingBag) endNum = 1;
			else if(itemstack1.getItem()==ModItems.CampingBag) 
			{
				itemstack = null;
				startNum = 0;
				endNum = 0;
			}
			
			if (i < campingInv.getInventorySize()) 
			{
				if (!mergeItemStack(itemstack1, campingInv.getInventorySize(), inventorySlots.size(), true)) 
				{
					return null;
				}
			} 
				
			else if (!mergeItemStack(itemstack1, startNum, endNum, false)) 
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
