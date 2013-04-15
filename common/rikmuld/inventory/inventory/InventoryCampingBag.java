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
	public String invName;

	public InventoryCampingBag(EntityPlayer player, ItemStack is) 
	{
		super("", false, (is.getItemDamage()+1)*9);
		
		playerEntity = player;
		theBackpack = is;
		backpackNum = is.getItemDamage();
		
		if (!hasInventory()) 
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

	@Override
	public String getInvName() 
	{
		return this.invName;
	}
	
	protected static int getInventorySize(ItemStack is)
	{
		return 9 * (backpackNum+1);
	}
	
	private boolean hasInventory()
	{
		if(theBackpack.stackTagCompound!=null) return theBackpack.stackTagCompound.hasKey("Inventory");
		else return false;
	}

	private void createInventory() 
	{
		setInvName(theBackpack.getDisplayName());
		writeToNBT();
	}
	
	public void setInvName(String name) 
	{
		this.invName = name;
	}
	
	private void setNBT() 
	{
		if(playerEntity.getCurrentArmor(2)!=null) 
		{
			if(playerEntity.getCurrentArmor(2).isItemEqual(theBackpack)) 
			{
				playerEntity.getCurrentArmor(2).setTagCompound(theBackpack.getTagCompound());
			}
		}
	}

	public void loadInventory() 
	{
		readFromNBT();
	}

	public void saveInventory() 
	{
		writeToNBT();
		setNBT();
	}

	private void writeToNBT() 
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

	private void readFromNBT() 
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