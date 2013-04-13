package rikmuld.inventory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCampingBag extends InventoryBasic {

	private EntityPlayer playerEntity;
	private boolean reading = false;
	private ItemStack theBackpack;
	public static int backpackNum;

	public InventoryCampingBag(EntityPlayer player, ItemStack is) 
	{
		super("", false, (is.getItemDamage()+1)*9);
		
		playerEntity = player;
		theBackpack = is;
		backpackNum = is.getItemDamage();
		
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
		return 9 * (backpackNum+1);
	}

	private boolean hasInventory(NBTTagCompound nbt)
	{
		return (nbt != null && (nbt.hasKey("Inventory")));
	}

	private void createInventory() 
	{
		NBTTagCompound tag;
		if (theBackpack.hasTagCompound())
		{
			tag = theBackpack.getTagCompound();
		} 
		
		else 
		{
			tag = new NBTTagCompound();
		}
		
		writeToNBT(tag);
		theBackpack.setTagCompound(tag);
	}
	
	private void setNBT() 
	{
		if(playerEntity.getCurrentEquippedItem() != null) 
		{
			playerEntity.getCurrentEquippedItem().setTagCompound(theBackpack.getTagCompound());
		}
	}

	public void loadInventory() 
	{
		readFromNBT(theBackpack.getTagCompound());
	}

	public void saveInventory() 
	{
		writeToNBT(theBackpack.getTagCompound());
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
		
		if (theBackpack.stackTagCompound == null) 
		{
			theBackpack.setTagCompound(new NBTTagCompound());
		}
		theBackpack.stackTagCompound.setCompoundTag("Inventory", inventory);
	}

	private void readFromNBT(NBTTagCompound outerTag) 
	{
		reading = true;
		
		if (theBackpack.stackTagCompound == null) 
		{
			theBackpack.setTagCompound(new NBTTagCompound());
		}
		
		NBTTagList itemList = theBackpack.stackTagCompound.getCompoundTag("Inventory").getTagList("Items");
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