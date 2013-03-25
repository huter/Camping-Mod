package rikmuld.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.Items;
import rikmuld.core.register.ModAchievements;
import rikmuld.item.CampingItemFood;

public class FoodRadish extends CampingItemFood{

	   public final int itemUseDuration;
	    private final int healAmount;
	    private final float saturationModifier;
	    private final boolean isWolfsFavoriteMeat;
	    private boolean alwaysEdible;
	    
	    public FoodRadish(int par1)
	    {
	        super(par1, 10, false);
	    	float par3 = 0.6F;
	        this.itemUseDuration = 12;
			maxStackSize = 64;
	        this.healAmount = Config.PLANT_RADISH_HEAL;
	        this.isWolfsFavoriteMeat = false;
	        this.saturationModifier = par3;
	        this.setUnlocalizedName(Items.ITEM_RADISH_FOOD_NAME);
	        this.setCreativeTab(CampingMod.customTab);
	    }
	    
	    public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        par3EntityPlayer.getFoodStats().addStats(this);
	        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
	        par3EntityPlayer.addStat(ModAchievements.RadishFood, 1);
	    }

	    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        if (par3EntityPlayer.canEat(this.alwaysEdible))
	        {
	            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        }
	        return par1ItemStack;
	    }
}
