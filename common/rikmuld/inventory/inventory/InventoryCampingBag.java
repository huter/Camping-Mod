package rikmuld.inventory.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCampingBag extends InventoryBasic {

	private EntityPlayer playerEntity;
	private boolean reading = false;
	private int backpackHelp = 0;
	static Minecraft mc;
	
	public InventoryCampingBag(EntityPlayer player) 
	{
		super("playerBackInv", false, 28);		
		playerEntity = player;
		loadInventory();
		loadBackpackInventory();
	}
	
	@Override
	public void onInventoryChanged() 
	{
		super.onInventoryChanged();
		if (!reading) 
		{
			saveInventory();
			saveBackpackInventory();
		}
		if(getStackInSlot(0)==null)
		{
			for (int i = 1; i < 28; i++) 
			{
				if (getStackInSlot(i) != null) 
				{
					this.setInventorySlotContents(i, null);
				}
			}
		}
	}

	private void saveBackpackInventory() 
	{
		if(this.isNewBackpack()) loadBackpackInventory();
		if(getStackInSlot(0)!=null)writeToNBT(true);
	}

	private void loadBackpackInventory()
	{
		if(getStackInSlot(0)!=null)readFromNBT(true);
	}

	@Override
	public void openChest() 
	{
		loadInventory();
		loadBackpackInventory();
	}

	@Override
	public void closeChest() 
	{
		saveInventory();
		saveBackpackInventory();	
	}
	
	public int getInventorySize()
	{
		if (this.getStackInSlot(0)==null) return 1;
		else if (this.getStackInSlot(0).getItemDamage()==0) return 10;
		else if (this.getStackInSlot(0).getItemDamage()==0) return 19;
		else if (this.getStackInSlot(0).getItemDamage()==0) return 28;
		else return 1;
	}

	public void loadInventory() 
	{
		readFromNBT(false);
	}

	public void saveInventory() 
	{
		writeToNBT(false);
	}

	private void writeToNBT(boolean backpackInv) 
	{	
		if(!backpackInv)
		{
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
		else
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

			if (getStackInSlot(0).stackTagCompound == null) 
			{
				getStackInSlot(0).setTagCompound(new NBTTagCompound());
			}
			getStackInSlot(0).stackTagCompound.setCompoundTag("BackpackInv", inventory);
		}
	}

	private void readFromNBT(boolean backpackInv) 
	{
		reading = true;	

		if(!backpackInv)
		{
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
		}
		else
		{
			if (getStackInSlot(0).stackTagCompound == null) 
			{
				getStackInSlot(0).setTagCompound(new NBTTagCompound());
			}

			NBTTagList itemList = getStackInSlot(0).getTagCompound().getCompoundTag("BackpackInv").getTagList("BackpackItems");
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
		
		reading = false;
	}
	
	private boolean isNewBackpack() 
	{
		if(playerEntity.getEntityData().hasKey("backpackHelp"))backpackHelp = playerEntity.getEntityData().getInteger("backpackHelp");
		
		if(getStackInSlot(0)==null) backpackHelp = 1;
		if(getStackInSlot(0)!=null&& backpackHelp != 1) backpackHelp = 0;
		if(getStackInSlot(0)!=null&& backpackHelp == 1) backpackHelp = 2;
		
		playerEntity.getEntityData().setInteger("backpackHelp", backpackHelp);
		
		return (backpackHelp==2) ? true:false;
	}
}
