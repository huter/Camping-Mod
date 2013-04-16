package rikmuld.inventory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCampingBag extends InventoryBasic {

	private EntityPlayer playerEntity;
	private boolean reading = false;
	private boolean set = false;
	
	public InventoryCampingBag(EntityPlayer player) 
	{
		super("playerBackInv", false, 28);		
		playerEntity = player;
		loadInventory();
		set = mustSet();
	}

	private boolean mustSet() 
	{
		if(!playerEntity.getEntityData().hasKey("backpackOpen")) firstUse();
		
		int open = playerEntity.getEntityData().getInteger("backpackOpen");
		boolean IsOpendWithBackpack = playerEntity.getEntityData().getBoolean("backpackisOpend");
		
		if(getStackInSlot(0)!=null)
		{
			open++;
			if(open>=2) 
			{
				open =2;
				IsOpendWithBackpack = true;
			}
		}
		else
		{
			open = (IsOpendWithBackpack) ? 1:0;
		}
		playerEntity.getEntityData().setInteger("backpackOpen", open);
		playerEntity.getEntityData().setBoolean("backpackisOpend", IsOpendWithBackpack);
		return (open==2) ? true:false;
	}

	private void firstUse()
	{
		playerEntity.getEntityData().setInteger("backpackOpen", 0);
		playerEntity.getEntityData().setBoolean("backpackisOpend", false);
	}
	
	@Override
	public void onInventoryChanged() 
	{
		super.onInventoryChanged();
		if (!reading) 
		{
			saveInventory();
		}
		if(getStackInSlot(0)==null)
		{
			playerEntity.getEntityData().removeTag("BackpackInv");
			for (int i = 0; i < 28; i++) 
			{
				if (getStackInSlot(i) != null) 
				{
					this.setInventorySlotContents(i, null);
				}
			}
		}
	}

	@Override
	public void openChest() 
	{
		if(getStackInSlot(0)!=null)
		{
			if(getStackInSlot(0).getTagCompound()!=null) playerEntity.getEntityData().setCompoundTag("BackpackInv", getStackInSlot(0).getTagCompound());
			
			NBTTagList itemList = playerEntity.getEntityData().getCompoundTag("BackpackInv").getTagList("BackpackItems");
			for (int i = 0; i < itemList.tagCount(); i++) 
			{
				NBTTagCompound slotEntry = (NBTTagCompound) itemList.tagAt(i);
				int j = slotEntry.getByte("BackpackSlot") & 0xff;
				if (j >= 1 && j < 28) 
				{
					setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
				}
			}
		}

		loadInventory();
	}

	@Override
	public void closeChest() 
	{
		saveInventory();
	}
	
	public int getInventorySize()
	{
		if (this.getStackInSlot(0)==null) return 1;
		else if (this.getStackInSlot(0).getItemDamage()==0) return 10;
		else if (this.getStackInSlot(0).getItemDamage()==0) return 19;
		else if (this.getStackInSlot(0).getItemDamage()==0) return 28;
		else return 1;
	}
	
	private void setNBT() 
	{
		if(getStackInSlot(0)!=null)getStackInSlot(0).setTagCompound(playerEntity.getEntityData().getCompoundTag("BackpackInv"));
	}

	public void loadInventory() 
	{
		readFromNBT();
	}

	public void saveInventory() 
	{
		writeToNBT();
		if(set)setNBT();
	}

	private void writeToNBT() 
	{	
		NBTTagList itemList = new NBTTagList();
		for (int i = 1; i < 28; i++) 
		{
			if (getStackInSlot(i) != null) 
			{
				NBTTagCompound slotEntry = new NBTTagCompound();
				slotEntry.setByte("BackpackSlot", (byte) i);
				getStackInSlot(i).writeToNBT(slotEntry);
				itemList.appendTag(slotEntry);
			}
		}

		NBTTagCompound inventory = new NBTTagCompound();
		inventory.setTag("BackpackItems", itemList);
	
		playerEntity.getEntityData().setCompoundTag("BackpackInv", inventory);
		
		NBTTagList backpack = new NBTTagList();
		if (getStackInSlot(0) != null) 
		{
			NBTTagCompound slot0 = new NBTTagCompound();
			slot0.setByte("Back", (byte) 0);
			getStackInSlot(0).writeToNBT(slot0);
			backpack.appendTag(slot0);
		}
		
		NBTTagCompound theBack = new NBTTagCompound();
		theBack.setTag("theBackPack", backpack);
		
		playerEntity.getEntityData().setCompoundTag("theBackpack", theBack);
	}

	private void readFromNBT() 
	{
		reading = true;
		
		NBTTagList itemList = playerEntity.getEntityData().getCompoundTag("BackpackInv").getTagList("BackpackItems");
		for (int i = 0; i < itemList.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) itemList.tagAt(i);
			int j = slotEntry.getByte("BackpackSlot") & 0xff;
			if (j >= 1 && j < 28) 
			{
				setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
			}
		}
		

		NBTTagList backpack = playerEntity.getEntityData().getCompoundTag("theBackpack").getTagList("theBackPack");
		for (int i = 0; i < backpack.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) backpack.tagAt(i);
			int j = slotEntry.getByte("Back") & 0xff;
			if (j >= 0 && j < 1) 
			{
				setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
			}
		}
		
		reading = false;
	}
}