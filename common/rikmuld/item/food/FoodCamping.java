package rikmuld.item.food;

import java.util.List;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.Items;
import rikmuld.core.register.ModAchievements;
import rikmuld.core.register.ModLogger;
import rikmuld.item.CampingItemFood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodCamping extends CampingItemFood{

    private final boolean alwaysEdible;
    private int healAmount;
    
    public static final String[] metadataNames = new String[] {Items.ITEM_MARSH_FOOD_NAME, Items.ITEM_RADISH_FOOD_NAME};

    public FoodCamping(int par1)
    {
        super(par1, 1, false, metadataNames);
		maxStackSize = 64;
        this.setUnlocalizedName(Items.ITEM_MARSH_FOOD_NAME);
        this.setCreativeTab(CampingMod.customTab);
        this.alwaysEdible = true;
        this.setHasSubtypes(true);
    }
    
    @Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		return metadataNames[itemstack.getItemDamage()];
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativetabs, List list) 
	{
		for (int var4 = 0; var4 < 2; ++var4) 
		{
			list.add(new ItemStack(par1, 1, var4));
		}
	}
  
	 public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	 {
	     if (par3EntityPlayer.canEat(this.alwaysEdible))
	     {
	         par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	     }
	     return par1ItemStack;
	 }
	 
    @Override
    public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        if(par1ItemStack.getItemDamage()==0)
    	{
            ItemStack stick = new ItemStack(Item.stick);
    	    par3EntityPlayer.inventory.addItemStackToInventory(stick);
    	}
    	else if(par1ItemStack.getItemDamage()==1)
    	{
	        par3EntityPlayer.addStat(ModAchievements.RadishFood, 1);
    	}
    }
    
    @Override
    public int getHealAmount()
    {
    	return this.healAmount;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
    	if(par1ItemStack.getItemDamage()==0)
    	{
    		this.healAmount = Config.PLANT_MARSHMALLOW_HEAL;
    		return 64;
    	}
    	else if(par1ItemStack.getItemDamage()==1)
    	{
    		this.healAmount = Config.PLANT_RADISH_HEAL;
    		return 12;
    	}
    	return 32;
    }
}