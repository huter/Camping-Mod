package rikmuld.item.normal;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import rikmuld.core.lib.Items;
import rikmuld.item.CampingItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Marshmellow extends CampingItem{
			
	public static final String[] metadataNames = new String[] {Items.ITEM_MARSH_NORMAL_NAME, Items.ITEM_MARSH_STICK_NAME};
	
	public Marshmellow(int i)
	{
		super(i, metadataNames);
		maxStackSize = 64;
		setHasSubtypes(true);
		setMaxDamage(0);
		setUnlocalizedName(Items.ITEM_META_MARSH_NAME);
	}	
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
    {
            return metadataNames[itemstack.getItemDamage()];
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativetabs, List list)
	{
		for(int var4 = 0; var4 < 2; ++var4)
		{
			list.add(new ItemStack(par1, 1, var4));
		}
	}	
}