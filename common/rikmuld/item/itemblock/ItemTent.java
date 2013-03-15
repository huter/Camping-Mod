package rikmuld.item.itemblock;

import rikmuld.core.lib.Blocks;
import rikmuld.item.CampingItemBlock;

public class ItemTent extends CampingItemBlock {

	public ItemTent(int id) 
	{
		super(id);
		setHasSubtypes(true);
		setUnlocalizedName(Blocks.BLOCK_TENT_NAME);
	}
	
	@Override
	public int getMetadata(int damageValue) 
	{
		return damageValue;
	}
}
