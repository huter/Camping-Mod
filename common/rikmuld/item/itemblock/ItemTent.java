package rikmuld.item.itemblock;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import rikmuld.core.lib.Blocks;
import rikmuld.core.lib.Config;
import rikmuld.item.CampingItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTent extends CampingItemBlock {

	public static final String[] metadataNames = new String[] {Blocks.BLOCK_TENT_NAME, Blocks.BLOCK_TENT_STORAGE_NAME, Blocks.BLOCK_TENT_SLEEP_NAME};

	public ItemTent(int id) 
	{
		super(id);
		setHasSubtypes(true);
		setUnlocalizedName(Blocks.BLOCK_TENT_NAME);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativetabs, List list) 
	{
		 int registerTents = 1;
		 
		 if(Config.GENERAL_SEPARATED_TENT)
		 {
			 registerTents = 3;
		 }
		 
	 	 for (int ix = 0; ix < registerTents; ix++) 
	 	 {
	 		list.add(new ItemStack(this, 1, ix));
	 	 }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		if(Config.GENERAL_SEPARATED_TENT)
		{
			return metadataNames[itemstack.getItemDamage()];
		}
		return Blocks.BLOCK_TENT_NAME;
	}
	
	@Override
	public int getMetadata (int damageValue) 
	{
		return damageValue;
	}

}
