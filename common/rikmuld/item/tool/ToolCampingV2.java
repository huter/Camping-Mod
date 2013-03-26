package rikmuld.item.tool;

import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.Items;
import rikmuld.item.CampingItem;
	
public class ToolCampingV2 extends CampingItem {

		public ToolCampingV2(int i) 
		{
			super(i);
			maxStackSize = 1;
			setUnlocalizedName(Items.ITEM_TOOL_CAMP2_NAME);
			setMaxDamage(Config.GENERAL_CAMPTOOL2_MAX_DURABILATY);
			isDamageable();
			this.setNoRepair();
			ToolHelper.addTool(itemID);
		}
}
