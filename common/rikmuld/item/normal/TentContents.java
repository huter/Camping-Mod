package rikmuld.item.normal;

import rikmuld.core.lib.Items;
import rikmuld.item.CampingItem;

public class TentContents extends CampingItem {

	public TentContents(int i) 
	{
		super(i);
		maxStackSize = 4;
		setMaxDamage(0);
		setUnlocalizedName(Items.ITEM_TENT_CONTENTS_NAME);
	}
}
