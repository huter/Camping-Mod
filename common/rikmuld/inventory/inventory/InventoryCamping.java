package rikmuld.inventory.inventory;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import rikmuld.inventory.slot.BackpackNoSlot;
import rikmuld.inventory.slot.CampingSlot;

public class InventoryCamping extends InventoryBasic {

	private EntityPlayer playerEntity;
	private boolean reading = false;
	static Minecraft mc;
	public boolean containerExsists = false;
    public ArrayList<BackpackNoSlot> backpackSlots = new ArrayList<BackpackNoSlot>();
    public ArrayList<CampingSlot> craftingSlots = new ArrayList<CampingSlot>();
	public InventoryCrafting craftMatrix;
	public InventoryCrafting craftMatrix2;
    
	public InventoryCamping(EntityPlayer player) 
	{
		super("CampingInventory", false, 29);		
		playerEntity = player;
		loadInventory();
	}

	@Override
	public void onInventoryChanged() 
	{
		super.onInventoryChanged();
		setSlots();
		
		if(backpackSlotChanged()||backpackSlotInventoryChanged())
		{
			this.removeBackpackInv();
			if(getStackInSlot(0)!=null)this.setInvFromCampingBag();	
		}
		
		if(getStackInSlot(1)==null)
		{
			removeCraftingInv();
		}
		
		if(!reading)
		{
			saveInventory();
		}
	}

	private void removeCraftingInv() 
	{
		if(containerExsists)
		{	
			for (int i = 0; i < 9; i++) 
			{
				if (craftMatrix.getStackInSlot(i) != null) 
				{
					playerEntity.dropPlayerItem(craftMatrix.getStackInSlot(i));
					craftMatrix.setInventorySlotContents(i, null);
				}
				if (craftMatrix2.getStackInSlot(i) != null) 
				{
					playerEntity.dropPlayerItem(craftMatrix2.getStackInSlot(i));
					craftMatrix2.setInventorySlotContents(i, null);
				}
			}
		}
	}

	private void removeBackpackInv()
	{
		for (int i = 2; i < 29; i++) 
		{
			if (getStackInSlot(i) != null) 
			{
				this.setInventorySlotContents(i, null);
			}
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
		return 29;
	}

	public void loadInventory() 
	{
		loadCraftingSlots();
		readFromNBT();
	}

	public void saveInventory() 
	{
		saveCraftingSlots();
		writeToNBT();
		setInvToCampingBag();
	}

	public void setInvToCampingBag() 
	{
		if(getStackInSlot(0)!=null) 
		{
			NBTTagList backpack = new NBTTagList();
			for (int i = 2; i < ((getStackInSlot(0).getItemDamage()+1)*9)+2; i++) 
			{
				if (getStackInSlot(i) != null) 
				{
					NBTTagCompound slot = new NBTTagCompound();
					slot.setByte("BackpackSlot", (byte) (i-2));
					getStackInSlot(i).writeToNBT(slot);
					backpack.appendTag(slot);
				}
			}

			NBTTagCompound theBack = new NBTTagCompound();
			theBack.setTag("BackpackItems", backpack);

			if (getStackInSlot(0).stackTagCompound.getCompoundTag("BackpackInv") == null) 
			{
				getStackInSlot(0).stackTagCompound.setCompoundTag("BackpackInv", new NBTTagCompound());
			}
			getStackInSlot(0).stackTagCompound.setCompoundTag("BackpackInv", theBack);
		}
	}
	
	public void setInvFromCampingBag() 
	{
		reading = true;

		if (getStackInSlot(0).stackTagCompound.getCompoundTag("BackpackInv") == null) 
		{
			getStackInSlot(0).stackTagCompound.setCompoundTag("BackpackInv", new NBTTagCompound());
		}
		NBTTagList backpack = getStackInSlot(0).stackTagCompound.getCompoundTag("BackpackInv").getTagList("BackpackItems");
		for (int i = 0; i < backpack.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) backpack.tagAt(i);
			int j = slotEntry.getByte("BackpackSlot") & 0xff;
			if (j >= 0 && j < (getStackInSlot(0).getItemDamage()+1)*9)
			{
				setInventorySlotContents(j+2, ItemStack.loadItemStackFromNBT(slotEntry));
			}
		}
		reading = false;
	}
	

	private void setSlots() 
	{
		if(containerExsists)
		{
			if(getStackInSlot(0)!=null)
			{		
				if(getStackInSlot(0).getItemDamage()==0)
				{
					for(int i = 0; i<9; i++)
					{
						if(backpackSlots.get(i)!=null)backpackSlots.get(i).noItemsValid = false;
					}
					for(int i = 9; i<backpackSlots.size(); i++)
					{
						if(backpackSlots.get(i)!=null)backpackSlots.get(i).noItemsValid = true;
					}		
				}
				
				else if(getStackInSlot(0).getItemDamage()==1)
				{
					for(int i = 0; i<18; i++)
					{
						if(backpackSlots.get(i)!=null)backpackSlots.get(i).noItemsValid = false;
					}
					for(int i = 18; i<backpackSlots.size(); i++)
					{
						if(backpackSlots.get(i)!=null)backpackSlots.get(i).noItemsValid = true;
					}
				}
	
				else if(getStackInSlot(0).getItemDamage()==2)
				{
						
					for(int i = 0; i<backpackSlots.size(); i++)
					{
						if(backpackSlots.get(i)!=null)backpackSlots.get(i).noItemsValid = false;
					}		
				}
			}
			else
			{
				for(int i = 0; i<backpackSlots.size(); i++)
				{
					if(backpackSlots.get(i)!=null)backpackSlots.get(i).noItemsValid = true;
				}
			}
			if(getStackInSlot(1)!=null)
			{					
				for(int i = 0; i<craftingSlots.size(); i++)
				{
					if(craftingSlots.get(i)!=null)craftingSlots.get(i).noItemsValid = false;
				}
			}
			else
			{
				for(int i = 0; i<craftingSlots.size(); i++)
				{
					if(craftingSlots.get(i)!=null)craftingSlots.get(i).noItemsValid = true;
				}
			}
		}
	}

	private void writeToNBT() 
	{	
		NBTTagList backpack = new NBTTagList();
		for (int i = 0; i < 29; i++) 
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
			if (j >= 0 && j < 29) 
			{
				setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
			}
		}
		reading = false;
	}
	
	public boolean backpackSlotChanged() 
	{
		int backpackSwitch = 0;
		boolean HelperBackpack = false;
		boolean result = false;

		if(playerEntity.getEntityData().hasKey("backpackSwitch"))backpackSwitch = playerEntity.getEntityData().getInteger("backpackSwitch");
		if(getStackInSlot(0)!=null)
		{
			if (getStackInSlot(0).stackTagCompound == null) 
			{
				getStackInSlot(0).setTagCompound(new NBTTagCompound());
			}

			if(getStackInSlot(0).stackTagCompound.hasKey("HelperBackpack"))HelperBackpack = getStackInSlot(0).stackTagCompound.getBoolean("HelperBackpack");
			if(!HelperBackpack)
			{
				HelperBackpack = true;
				result = true;
			}
			getStackInSlot(0).stackTagCompound.setBoolean("HelperBackpack", HelperBackpack);
		}

		if(getStackInSlot(0)==null && backpackSwitch == 1)
		{
			 backpackSwitch = 0;
			 result = true;
		}
		if(getStackInSlot(0)!=null && backpackSwitch == 0)
		{
			 backpackSwitch = 1;
			 result = true;
		}
		playerEntity.getEntityData().setInteger("backpackSwitch", backpackSwitch);
		return result;
	}

	public boolean backpackSlotInventoryChanged() 
	{
		boolean result = false;
		
		if(getStackInSlot(0)!=null)
		{
			NBTTagCompound nbt = this.getStackInSlot(0).stackTagCompound;
			if(playerEntity.getEntityData().hasKey("returnNBT"))nbt = playerEntity.getEntityData().getCompoundTag("returnNBT");
			
			if(getStackInSlot(0).stackTagCompound!=nbt)
			{
				nbt = this.getStackInSlot(0).stackTagCompound;
				result = true;
			}
			
			playerEntity.getEntityData().setCompoundTag("returnNBT", nbt);
		}
		return result;
	}
	
	private void saveCraftingSlots() 
	{
		if(containerExsists)
		{
			NBTTagList backpack = new NBTTagList();
			for (int i = 0; i < 9; i++) 
			{
				if (craftMatrix.getStackInSlot(i) != null) 
				{
					NBTTagCompound slot = new NBTTagCompound();
					slot.setByte("CampingCraftSlot", (byte) i);
					craftMatrix.getStackInSlot(i).writeToNBT(slot);
					backpack.appendTag(slot);
				}
			}
			for (int i = 9; i < 18; i++) 
			{
				if (craftMatrix2.getStackInSlot(i-9) != null) 
				{
					NBTTagCompound slot = new NBTTagCompound();
					slot.setByte("CampingCraftSlot", (byte) i);
					craftMatrix2.getStackInSlot(i-9).writeToNBT(slot);
					backpack.appendTag(slot);
				}
			}

			NBTTagCompound theBack = new NBTTagCompound();
			theBack.setTag("CampingCraftItems", backpack);

			if (playerEntity.getEntityData().getCompoundTag("CampingCraftInventory") == null) 
			{
				playerEntity.getEntityData().setCompoundTag("CampingCraftInventory", new NBTTagCompound());
			}
			playerEntity.getEntityData().setCompoundTag("CampingCraftInventory", theBack);
		}
	}
	
	private void loadCraftingSlots() 
	{
		reading = true;
		if(containerExsists)
		{
			if (playerEntity.getEntityData().getCompoundTag("CampingCraftInventory") == null) 
			{
				playerEntity.getEntityData().setCompoundTag("CampingCraftInventory", new NBTTagCompound());
			}
			NBTTagList backpack = playerEntity.getEntityData().getCompoundTag("CampingCraftInventory").getTagList("CampingCraftItems");
			for (int i = 0; i < backpack.tagCount(); i++) 
			{
				NBTTagCompound slotEntry = (NBTTagCompound) backpack.tagAt(i);
				int j = slotEntry.getByte("CampingCraftSlot") & 0xff;
				if (j >= 0 && j < 9) 
				{
					craftMatrix.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
				}
				if (j >= 9 && j < 18) 
				{
					craftMatrix2.setInventorySlotContents(j-9, ItemStack.loadItemStackFromNBT(slotEntry));
				}
			}
		}
		reading = false;
	}
}