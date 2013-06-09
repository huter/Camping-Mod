package rikmuld.item.normal;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.GuiIds;
import rikmuld.core.lib.Items;
import rikmuld.item.CampingItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CampingBag extends CampingItem {

	public static final String[] metadataNames = new String[] {Items.ITEM_TOOL_BACK_SMALL_NAME, Items.ITEM_TOOL_BACK_NORMAL_NAME, Items.ITEM_TOOL_BACK_LARGE_NAME };
		
	public CampingBag(int i) 
	{
		super(i, metadataNames);
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
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativetabs, List list) 
	{
		for (int var4 = 0; var4 < 3; ++var4) 
		{
			list.add(new ItemStack(par1, 1, var4));
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		player.openGui(CampingMod.instance, GuiIds.GUICampingBag, world, 0, 0, 0);
		return stack;
    }
}

