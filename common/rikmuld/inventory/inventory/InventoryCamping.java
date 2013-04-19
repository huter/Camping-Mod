package rikmuld.inventory.inventory;

import java.util.logging.Level;

import rikmuld.core.register.ModLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCamping extends InventoryBasic {

	private EntityPlayer playerEntity;
	private boolean reading = false;
	static Minecraft mc;
	public InventoryCampingBag campingBagInv;
	public int backpackHelp = 0;
	
	public InventoryCamping(EntityPlayer player) 
	{
		super("playerBackInv", false, 2);		
		playerEntity = player;
		loadInventory();
	}
	
	public void setCampingBagInv(InventoryCampingBag inv) 
	{
		if(this.getStackInSlot(0)!=null)
		{
			campingBagInv = inv;
		}
	}
	
	@Override
	public void onInventoryChanged() 
	{
		super.onInventoryChanged();
		if (!reading) 
		{
			saveInventory();
		}
		if(campingBagInv!=null)
		{
			campingBagInv.onCampingInventoryChanged();
		}
	}
	
	@Override
	public void openChest() 
	{
		loadInventory();
	}

	@Override
	public void closeChest() 
	{
		saveInventory();
	}
	
	public int getInventorySize()
	{
		return 2;
	}

	public void loadInventory() 
	{
		readFromNBT();
	}

	public void saveInventory() 
	{
		writeToNBT();
	}

	private void writeToNBT() 
	{	
		NBTTagList backpack = new NBTTagList();
		for (int i = 0; i < 2; i++) 
		{
			if (getStackInSlot(i) != null) 
			{
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("CampingSlot", (byte) i);
				getStackInSlot(i).writeToNBT(slot);
				backpack.appendTag(slot);
			}
		}
		
		NBTTagCompound theBack = new NBTTagCompound();
		theBack.setTag("CampingItems", backpack);
		
		if (playerEntity.getEntityData().getCompoundTag("CampingInventory") == null) 
		{
			playerEntity.getEntityData().setCompoundTag("CampingInventory", new NBTTagCompound());
		}
		playerEntity.getEntityData().setCompoundTag("CampingInventory", theBack);
	}

	public void readFromNBT()
	{
		reading = true;
		
		if (playerEntity.getEntityData().getCompoundTag("CampingInventory") == null) 
		{
			playerEntity.getEntityData().setCompoundTag("CampingInventory", new NBTTagCompound());
		}
		NBTTagList backpack = playerEntity.getEntityData().getCompoundTag("CampingInventory").getTagList("CampingItems");
		for (int i = 0; i < backpack.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) backpack.tagAt(i);
			int j = slotEntry.getByte("CampingSlot") & 0xff;
			if (j >= 0 && j < 2) 
			{
				setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
			}
		}
		reading = false;
	}
}
