package rikmuld.item.itemblock;

import net.minecraft.item.ItemStack;
import rikmuld.core.lib.Blocks;
import rikmuld.item.CampingItemBlock;

public class ItemCampfire extends CampingItemBlock {
	
	public ItemCampfire(int id) 
	{
		super(id, metadataNames);
		setHasSubtypes(true);
		setUnlocalizedName(Blocks.BLOCK_META_CAMP_NAME);
	}

	private final static String[] metadataNames = 
	{
		Blocks.BLOCK_CAMP_NAME, 
		Blocks.BLOCK_CAMP_MULTI_NAME,
		Blocks.BLOCK_CAMP_FAST_NAME, 
		Blocks.BLOCK_CAMP_CHEAP_NAME, 
		Blocks.BLOCK_CAMP_INSTA_NAME	
	};
	
	@Override
	public int getMetadata (int damageValue) 
	{
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		return metadataNames[itemstack.getItemDamage()];
	}
}
		
