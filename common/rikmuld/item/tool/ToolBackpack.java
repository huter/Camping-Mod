package rikmuld.item.tool;

import java.util.List;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.helper.KeyHelper;
import rikmuld.core.lib.Items;
import rikmuld.core.register.ModLogger;
import rikmuld.inventory.inventory.InventoryCampingBagLarge;
import rikmuld.inventory.inventory.InventoryCampingBagNormal;
import rikmuld.inventory.inventory.InventoryCampingBagSmall;
import rikmuld.item.CampingItemArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToolBackpack extends CampingItemArmor {

public static final String[] metadataNames = new String[] {Items.ITEM_TOOL_BACK_SMALL_NAME, Items.ITEM_TOOL_BACK_NORMAL_NAME, Items.ITEM_TOOL_BACK_LARGE_NAME };

		public ToolBackpack(int i) 
		{
			super(i, EnumArmorMaterial.IRON,  0, 1, metadataNames);
			maxStackSize = 1;
			setHasSubtypes(true);
			setMaxDamage(0);
			setUnlocalizedName(Items.ITEM_META_TOOL_BACK_NAME);
			KeyHelper.addKeyItem(this);
		}

		@Override
		public String getUnlocalizedName(ItemStack itemstack) 
		{
			return metadataNames[itemstack.getItemDamage()];
		}
		
		@SideOnly(Side.CLIENT)
		public void getSubItems(int par1, CreativeTabs creativetabs, List list) 
		{
			for (int var4 = 0; var4 < 3; ++var4) 
			{
				list.add(new ItemStack(par1, 1, var4));
			}
		}
		
		@Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
			this.openGui(par1ItemStack, par2World, par3EntityPlayer);
			return par1ItemStack;
	    }
		
		public ItemStack openGui(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
			if (par1ItemStack.itemID == this.itemID)
			{
				if(par1ItemStack.getItemDamage() == 2)
				{
					par3EntityPlayer.openGui(CampingMod.instance, 5, par2World,  0, 0, 0);
				}
				if(par1ItemStack.getItemDamage() == 1)
				{
					par3EntityPlayer.openGui(CampingMod.instance, 6, par2World,  0, 0, 0);
				}
				if(par1ItemStack.getItemDamage() == 0)
				{
					par3EntityPlayer.openGui(CampingMod.instance, 7, par2World,  0, 0, 0);
				}
			}		
			return par1ItemStack;
	    }
		
		public static IInventory getBackpackInv(EntityPlayer player)
		{
			ItemStack backpack;
			IInventory inventoryBackpack = null;
						
			if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ToolBackpack)
			{
				backpack = player.getCurrentEquippedItem();
				
				if(backpack.getItemDamage() == 2)
				{
					inventoryBackpack = new InventoryCampingBagLarge(player, backpack);
				}
				if(backpack.getItemDamage() == 1)
				{
					inventoryBackpack = new InventoryCampingBagNormal(player, backpack);
				}
				if(backpack.getItemDamage() == 0)
				{
					inventoryBackpack = new InventoryCampingBagSmall(player, backpack);
				}
			}
			
			if((player.getCurrentEquippedItem() == null ||!(player.getCurrentEquippedItem().getItem() instanceof ToolBackpack)) && player.inventory.getStackInSlot(38).getItem() instanceof ToolBackpack)
			{
				backpack = player.inventory.getStackInSlot(38);
				
				if(backpack.getItemDamage() == 2)
				{
					inventoryBackpack = new InventoryCampingBagLarge(player, backpack);
				}
				if(backpack.getItemDamage() == 1)
				{
					inventoryBackpack = new InventoryCampingBagNormal(player, backpack);
				}
				if(backpack.getItemDamage() == 0)
				{
					inventoryBackpack = new InventoryCampingBagSmall(player, backpack);
				}
			}
			
			return inventoryBackpack;
		}
		
		public void doKeyAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding)
		{
			if (keyBinding.equals(KeyHelper.keyBackpack)) 
			{
				openGui(itemStack, thePlayer.worldObj, thePlayer);				
	        }
		}
}

