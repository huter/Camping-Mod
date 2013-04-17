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
	private int setBack = 0;
	
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
		if(getStackInSlot(0)==null)
		{
			for (int i = 0; i < 28; i++) 
			{
				if (getStackInSlot(i) != null&&this.setBack==2) 
				{
					this.setInventorySlotContents(i, null);
					this.setBack = 1;
				}
			}
		}
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
		readFromNBT();
	}

	public void saveInventory() 
	{
		writeToNBT();
	}

	private void writeToNBT() 
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
		
		
		if(getStackInSlot(0)!=null&&set)
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

	private void readFromNBT() 
	{
		reading = true;	

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
		
		if(getStackInSlot(0)!=null)
		{
			if (getStackInSlot(0).stackTagCompound == null) 
			{
				getStackInSlot(0).setTagCompound(new NBTTagCompound());
			}
			
			if(!getStackInSlot(0).hasTagCompound()) writeToNBT();
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
}