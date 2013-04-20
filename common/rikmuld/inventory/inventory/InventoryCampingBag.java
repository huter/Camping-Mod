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
	public InventoryCamping campInv;
	public static int backpackNum;
	public boolean usedInCampingInv;

	public InventoryCampingBag(EntityPlayer player, ItemStack is, boolean usedInCampingInventory) 
	{
		super("", false, (is.getItemDamage()+1)*9);

		playerEntity = player;
		theBackpack = is;
		backpackNum = is.getItemDamage();
		usedInCampingInv = usedInCampingInventory;
		
		if (!hasInventory(is.getTagCompound())) 
		{
			createInventory();
		}
		loadInventory();
	}
	
	public InventoryCampingBag(EntityPlayer player, ItemStack is, boolean usedInCampingInventory, InventoryCamping campingInv) 
	{
		super("", false, (is.getItemDamage()+1)*9);

		playerEntity = player;
		theBackpack = is;
		backpackNum = is.getItemDamage();
		campInv = campingInv;
		campInv.setCampingBagInv(this);
		usedInCampingInv = usedInCampingInventory;
		
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
	
	public void onCampingInventoryChanged() 
	{
		if(campInv.getStackInSlot(0)==null)
		{
			for (int i = 0; i <  9 *(backpackNum+1); i++) 
			{
				if (getStackInSlot(i) != null) 
				{
					this.setInventorySlotContents(i, null);
				}
			}
		}
		if(campInv.getStackInSlot(0)!=null)
		{
			loadInventory();
		}
		saveInventory();
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

	public static int getInventorySize(ItemStack is)
	{
		return 9 * (backpackNum+1);
	}

	private boolean hasInventory(NBTTagCompound nbt)
	{
		return (nbt != null && (nbt.hasKey("BackpackInv")));
	}

	private void createInventory() 
	{
		writeToNBT();
	}

	private void setNBT() 
	{
		if(playerEntity.getCurrentEquippedItem() != null&&!usedInCampingInv) 
		{
			playerEntity.getCurrentEquippedItem().setTagCompound(theBackpack.getTagCompound());
		}
		else if(theBackpack!=null&&campInv.getStackInSlot(0)!=null)
		{
			campInv.getStackInSlot(0).setTagCompound(theBackpack.getTagCompound());
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
		if(!usedInCampingInv||campInv.getStackInSlot(0)!=null)
		{
			NBTTagList itemList = new NBTTagList();
			for (int i = 0; i < getSizeInventory(); i++) 
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
	
			if (theBackpack.stackTagCompound == null) 
			{
				theBackpack.setTagCompound(new NBTTagCompound());
			}
			theBackpack.stackTagCompound.setCompoundTag("BackpackInv", inventory);
		}
	}

	private void readFromNBT() 
	{
		reading = true;
		if(!usedInCampingInv||campInv.getStackInSlot(0)!=null)
		{
			if (theBackpack.stackTagCompound == null) 
			{
				theBackpack.setTagCompound(new NBTTagCompound());
			}
	
			NBTTagList itemList = theBackpack.stackTagCompound.getCompoundTag("BackpackInv").getTagList("BackpackItems");
			for (int i = 0; i < itemList.tagCount(); i++) 
			{
				NBTTagCompound slotEntry = (NBTTagCompound) itemList.tagAt(i);
				int j = slotEntry.getByte("BackpackSlot") & 0xff;
				if (j >= 0 && j < getSizeInventory()) 
				{
					setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
				}
			}
		}
		reading = false;
	}
}