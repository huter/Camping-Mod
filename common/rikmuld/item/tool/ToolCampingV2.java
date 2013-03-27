package rikmuld.item.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rikmuld.CampingMod;
import rikmuld.core.helper.KeyHelper;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.GuiIds;
import rikmuld.core.lib.Items;
import rikmuld.item.CampingItem;
	
public class ToolCampingV2 extends CampingItem{

		public ToolCampingV2(int i) 
		{
			super(i);
			maxStackSize = 1;
			setUnlocalizedName(Items.ITEM_TOOL_CAMP2_NAME);
			setMaxDamage(Config.GENERAL_CAMPTOOL2_MAX_DURABILATY);
			isDamageable();
			ToolHelper.addTool(this);
			KeyHelper.addKeyItem(itemID);
		}
		
		public void openCraftingGui(ItemStack stack, EntityPlayer player)
	    {
			player.openGui(CampingMod.instance, GuiIds.GUICampTool, player.worldObj, 0, 0, 0);
	    }
		
		public void doKeyAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding)
		{
			if (keyBinding.equals(KeyHelper.keyCraft)) 
			{
				openCraftingGui(itemStack, thePlayer);
	        }
		}
}
