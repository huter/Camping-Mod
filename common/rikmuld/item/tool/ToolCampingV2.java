package rikmuld.item.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rikmuld.CampingMod;
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
			ToolHelper.addTool(itemID);
		}
		
		@Override
		public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	    {
			return stack;
	    }
}
