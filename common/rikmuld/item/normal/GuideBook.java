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
	
	public static final String[] metadataNames = new String[]	
	{
		Items.ITEM_BOOK_GUIDE_CAMP_NAME,
		Items.ITEM_BOOK_GUIDE_TENT_NAME, 
		Items.ITEM_BOOK_GUIDE_EQUIP_NAME, 
		Items.ITEM_BOOK_GUIDE_FOOD_NAME, 
		Items.ITEM_BOOK_GUIDE_WORLD_NAME, 
	};

	public GuideBook(int id) 
	{
		super(id, metadataNames);
		setUnlocalizedName(Items.ITEM_META_BOOK_GUIDE_NAME);
		setHasSubtypes(true);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
    {	
		par3EntityPlayer.addStat(ModAchievements.Guides, 1);
		
        if(stack.getItemDamage()==0)
        {
        	par3EntityPlayer.openGui(CampingMod.instance, GuiIds.GUIGuideCampfire, par2World,  0, 0, 0);
        }
        if(stack.getItemDamage()==1)
        {
        	par3EntityPlayer.openGui(CampingMod.instance, GuiIds.GUIGuideTent, par2World,  0, 0, 0);
        }
        if(stack.getItemDamage()==2)
        {
        	par3EntityPlayer.openGui(CampingMod.instance, GuiIds.GUIGuideEquipment, par2World,  0, 0, 0);
        }
        if(stack.getItemDamage()==3)
        {
        	par3EntityPlayer.openGui(CampingMod.instance, GuiIds.GUIGuideFood, par2World,  0, 0, 0);
        }
        if(stack.getItemDamage()==4)
        {
        	par3EntityPlayer.openGui(CampingMod.instance, GuiIds.GUIGuideWorld, par2World,  0, 0, 0);
        }
        isGuiOpen = true;
        return stack;
    }
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		return metadataNames[itemstack.getItemDamage()];
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativetabs, List list) 
	{
		for (int var4 = 0; var4 < 5; ++var4) 
		{
			list.add(new ItemStack(par1, 1, var4));
		}
	}

}
