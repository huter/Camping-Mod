package rikmuld.inventory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCampingBagSmall extends InventoryBasic {

	private EntityPlayer playerEntity;
	private boolean reading = false;
	private ItemStack originalIS;

	public InventoryCampingBagSmall(EntityPlayer player, ItemStack is) 
	{
		super("", false, getInventorySize(is));
		
		playerEntity = player;
		originalIS = is;

		if (!hasInventory(is.getTagCompound())) 
		{
			createInventory();
		}
		loadInventory();
	}

	
	@Override
	public void onInventoryChanged() 
	{
		super.onInventoryChanged();
		if (!reading) 
		{
			saveInventory();
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
	
	protected static int getInventorySize(ItemStack is)
	{
		return 9 * 1;
	}

	private boolean hasInventory(NBTTagCompound nbt)
	{
		return (nbt != null && (nbt.hasKey("Inventory")));
	}

	private void createInventory() 
	{
		NBTTagCompound tag;
		if (originalIS.hasTagCompound())
		{
			tag = originalIS.getTagCompound();
		} 
		
		else 
		{
			tag = new NBTTagCompound();
		}
		writeToNBT(tag);
		originalIS.setTagCompound(tag);
	}

	private void setNBT() 
	{
		if(playerEntity.getCurrentEquippedItem() != null) 
		{
			playerEntity.getCurrentEquippedItem().setTagCompound(originalIS.getTagCompound());
		}
	}

	public void loadInventory() 
	{
		readFromNBT(originalIS.getTagCompound());
	}

	public void saveInventory() 
	{
		writeToNBT(originalIS.getTagCompound());
		setNBT();
	}

	private void writeToNBT(NBTTagCompound outerTag) 
	{
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) 
		{
			if (getStackInSlot(i) != null) 
			{
				NBTTagCompound slotEntry = new NBTTagCompound();
				slotEntry.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(slotEntry);
				itemList.appendTag(slotEntry);
			}
		}

		NBTTagCompound inventory = new NBTTagCompound();
		inventory.setTag("Items", itemList);
		
		if (originalIS.stackTagCompound == null) 
		{
			originalIS.setTagCompound(new NBTTagCompound());
		}
		
		originalIS.stackTagCompound.setCompoundTag("Inventory", inventory);
	}

	private void readFromNBT(NBTTagCompound outerTag) 
	{
		reading = true;
		
		if (originalIS.stackTagCompound == null) 
		{
			originalIS.setTagCompound(new NBTTagCompound());
		}
		
		NBTTagList itemList = originalIS.stackTagCompound.getCompoundTag("Inventory").getTagList("Items");
		for (int i = 0; i < itemList.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) itemList.tagAt(i);
			int j = slotEntry.getByte("Slot") & 0xff;
			if (j >= 0 && j < getSizeInventory()) 
			{
				setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
			}
		}
		reading = false;
	}
}