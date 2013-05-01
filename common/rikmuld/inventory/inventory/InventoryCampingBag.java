package rikmuld.inventory.inventory;

import rikmuld.core.register.ModItems;
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

		public InventoryCampingBag(EntityPlayer player, ItemStack is) 
		{
			super("", false, (is.getItemDamage()+1)*9);

			playerEntity = player;
			theBackpack = is;
			backpackNum = is.getItemDamage();

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

		public static int getInventorySize(ItemStack is)
		{
			return 9 * (backpackNum+1);
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

		private void readFromNBT(NBTTagCompound outerTag) 
		{
			reading = true;

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
			reading = false;
		}
		
		public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return ((par1EntityPlayer.getCurrentEquippedItem().getItem() == ModItems.CampingBag)? true:false);
	    }
}