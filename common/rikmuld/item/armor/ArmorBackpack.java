package rikmuld.item.armor;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.IArmorTextureProvider;
import rikmuld.CampingMod;
import rikmuld.core.helper.KeyHelper;
import rikmuld.core.lib.Items;
import rikmuld.core.lib.Textures;
import rikmuld.inventory.inventory.InventoryCampingBag;
import rikmuld.item.CampingItemArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorBackpack extends CampingItemArmor implements IArmorTextureProvider{

	public static final String[] metadataNames = new String[] {Items.ITEM_TOOL_BACK_SMALL_NAME, Items.ITEM_TOOL_BACK_NORMAL_NAME, Items.ITEM_TOOL_BACK_LARGE_NAME };
	static EnumArmorMaterial BACKPACK = EnumHelper.addArmorMaterial("BACKPACK", -1, new int[] { 0, 5, 0, 0 }, 0);

		public ArmorBackpack(int i) 
		{
			super(i, BACKPACK,  0, 1, metadataNames);
			maxStackSize = 1;
			setHasSubtypes(true);
			setMaxDamage(0);
			setUnlocalizedName(Items.ITEM_META_TOOL_BACK_NAME);
		}

		@Override
		public String getUnlocalizedName(ItemStack itemstack) 
		{
			return metadataNames[itemstack.getItemDamage()];
		}
		
		@Override
		public String getArmorTextureFile(ItemStack itemstack) 
		{
			switch(itemstack.getItemDamage())
			{
			case 0: return Textures.MODEL_LOCATION + Textures.MODEL_ARMOR_BACKPACK_SMALL;
			case 1: return Textures.MODEL_LOCATION + Textures.MODEL_ARMOR_BACKPACK_NORMAL;
			case 2: return Textures.MODEL_LOCATION + Textures.MODEL_ARMOR_BACKPACK_LARGE;
			}
			return Textures.MODEL_LOCATION + Textures.MODEL_ARMOR_BACKPACK_SMALL;
		}
		
		@SideOnly(Side.CLIENT)
		public void getSubItems(int par1, CreativeTabs creativetabs, List list) 
		{
			for (int var4 = 0; var4 < 3; ++var4) 
			{
				list.add(new ItemStack(par1, 1, var4));
			}
		}
}

