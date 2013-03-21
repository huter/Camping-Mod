package rikmuld.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.Items;
import rikmuld.item.CampingItemFood;

public class FoodMarshmellow extends CampingItemFood{

    public final int itemUseDuration;
    private final int healAmount;
    private final float saturationModifier;
    private final boolean isWolfsFavoriteMeat;
    private boolean alwaysEdible;
    
    public FoodMarshmellow(int par1)
    {
        super(par1, 10, false);
        float par3 = 0.6F;
        this.itemUseDuration = 64;
		maxStackSize = 64;
        this.healAmount = Config.PLANT_MARSHMALLOW_HEAL;
        this.isWolfsFavoriteMeat = false;
        this.saturationModifier = par3;
        this.setUnlocalizedName(Items.ITEM_MARSH_FOOD_NAME);
        this.setCreativeTab(CampingMod.customTab);
        this.alwaysEdible = true;
    }
  
    @Override
    public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        ItemStack stick = new ItemStack(Item.stick);
	    par3EntityPlayer.inventory.addItemStackToInventory(stick);
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