package rikmuld.item.normal;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.GuiIds;
import rikmuld.core.lib.Items;
import rikmuld.core.register.ModAchievements;
import rikmuld.item.CampingItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuideBook extends CampingItem{

	public static boolean isGuiOpen = false;

	public GuideBook(int id) 
	{
		super(id);
		setUnlocalizedName(Items.ITEM_BOOK_GUIDE_NAME);
		setHasSubtypes(true);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
    {	
		par3EntityPlayer.addStat(ModAchievements.Guides, 1);
		par3EntityPlayer.openGui(CampingMod.instance, GuiIds.GUIGuide, par2World,  0, 0, 0);  
        isGuiOpen = true;
        return stack;
    }
}
