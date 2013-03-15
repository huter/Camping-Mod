package rikmuld.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import rikmuld.core.lib.Textures;
import rikmuld.core.register.ModItems;
import rikmuld.entity.ai.EntityAILookAtTradePlayerCamper;
import rikmuld.entity.ai.EntityAITradePlayerCamper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Camper extends EntityAnimal implements  IMerchant, INpc{
	 
	  Random generator = new Random();
	  int isMale;

	 public Camper(World par1World) 
	 {
	  super(par1World);
	  this.moveSpeed = 0.30F;
	  this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, 0.3F, 0.35F));
      this.tasks.addTask(1, new EntityAITradePlayerCamper(this));
      this.tasks.addTask(1, new EntityAILookAtTradePlayerCamper(this));
      this.tasks.addTask(2, new EntityAIOpenDoor(this, true));
      this.tasks.addTask(3, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
      this.tasks.addTask(3, new EntityAIWatchClosest2(this, Camper.class, 5.0F, 0.02F));
      this.tasks.addTask(3, new EntityAIWander(this, 0.3F));
      this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
      
      this.isImmuneToFire = true;
      this.isMale = generator.nextInt(2);
	 }
	 
	    private boolean isPlaying;

	    /** This villager's current customer. */
	    private EntityPlayer buyingPlayer;

	    /** Initialises the MerchantRecipeList.java */
	    private MerchantRecipeList buyingList;
	    private int timeUntilReset;

	    /** addDefaultEquipmentAndRecipies is called if this is true */
	    private boolean needsInitilization;
	    private int wealth;

	    /** Last player to trade with this villager, used for aggressivity. */
	    private String lastBuyingPlayer;
	    private boolean field_82190_bM;
	    private float field_82191_bN;

	    /**
	     * a villagers recipe list is intialized off this list ; the 2 params are min/max amount they will trade for 1
	     * emerald
	     */
	    public static final Map villagerStockList = new HashMap();

	    /**
	     * Selling list of Blacksmith items. negative numbers mean 1 emerald for n items, positive numbers are n emeralds
	     * for 1 item
	     */
	    public static final Map blacksmithSellingList = new HashMap();

	 public int getMaxHealth() 
	 {
		 return 24;
	 }
	
	 protected boolean isAIEnabled()
	 {
		 return true;
	 }
	 
	 public EnumCreatureAttribute getCreatureAttribute()
	 {
		 return EnumCreatureAttribute.UNDEFINED;
	 }
	 
	 public int getAttackStrength(Entity par1Entity)
	 {
	     return 6;
	 }
	
	 public String getTexture()
	 {    
	      if (isMale==0) return Textures.MODEL_LOCATION + Textures.MODEL_CAMPER_MALE;      
          else return Textures.MODEL_LOCATION + Textures.MODEL_CAMPER_FEMALE;   
	 }
	 
	 protected boolean canDespawn()
	 {
		 return false;
	 }

	 protected String getLivingSound()
	 {
		 return "mob.villager.default";
	 }

	 protected String getHurtSound()
	 {
	     return "mob.villager.defaulthurt";
	 }

	 protected String getDeathSound()
	 {
	     return "mob.villager.defaultdeath";
	 }

	 protected void playStepSound(int par1, int par2, int par3, int par4)
	 {
		 this.worldObj.playSoundAtEntity(this, "mob.villager.step", 0.15F, 1.0F);
	 }
	 
	 public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	 {
		 super.writeEntityToNBT(par1NBTTagCompound);
		 par1NBTTagCompound.setInteger("Gender", this.isMale);
		 
		 if (this.buyingList != null)
		 {
            par1NBTTagCompound.setCompoundTag("offers", this.buyingList.getRecipiesAsTags());
		 }
	 }	 
	 
   	 public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
   	 {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.isMale = par1NBTTagCompound.getInteger("Gender");
        
        if (par1NBTTagCompound.hasKey("offers"))
        {
            NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound1);
        }
     }
   	 
     public boolean interact(EntityPlayer par1EntityPlayer)
     {
         ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
         boolean var3 = var2 != null && var2.itemID == Item.monsterPlacer.itemID;

         if (!var3 && this.isEntityAlive())
         {
             if (!this.worldObj.isRemote)
             {
                 this.setCustomer(par1EntityPlayer);
                 par1EntityPlayer.displayGUIMerchant(this, "Camper");
             }

             return true;
         }
         else
         {
             return super.interact(par1EntityPlayer);
         }
     }
	
    public void setPlaying(boolean par1)
    {
        this.isPlaying = par1;
    }

    public boolean isPlaying()
    {
        return this.isPlaying;
    }

    public void setCustomer(EntityPlayer par1EntityPlayer)
    {
        this.buyingPlayer = par1EntityPlayer;
    }

    public EntityPlayer getCustomer()
    {
        return this.buyingPlayer;
    }

    public boolean isTrading()
    {
        return this.buyingPlayer != null;
    }

    public void useRecipe(MerchantRecipe par1MerchantRecipe)
    {
        par1MerchantRecipe.incrementToolUses();

        if (par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1)))
        {
            this.timeUntilReset = 40;
            this.needsInitilization = true;

            if (this.buyingPlayer != null)
            {
                this.lastBuyingPlayer = this.buyingPlayer.getCommandSenderName();
            }
            else
            {
                this.lastBuyingPlayer = null;
            }
        }

        if (par1MerchantRecipe.getItemToBuy().itemID == Item.emerald.itemID)
        {
            this.wealth += par1MerchantRecipe.getItemToBuy().stackSize;
        }
    }

    public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer)
    {
        if (this.buyingList == null)
        {
            this.addDefaultEquipmentAndRecipies(1);
        }

        return this.buyingList;
    }

    private float func_82188_j(float par1)
    {
        float var2 = par1 + this.field_82191_bN;
        return var2 > 0.9F ? 0.9F - (var2 - 0.9F) : var2;
    }

    private void addDefaultEquipmentAndRecipies(int par1)
    {
        if (this.buyingList != null)
        {
            this.field_82191_bN = MathHelper.sqrt_float((float)this.buyingList.size()) * 0.2F;
        }
        else
        {
            this.field_82191_bN = 0.0F;
        }

        MerchantRecipeList var2;
        var2 = new MerchantRecipeList();
        int var6;
        label50:

                addBlacksmithItem(var2, ModItems.guideBook.itemID, this.rand, this.func_82188_j(0.7F));
        		addBlacksmithItem(var2, ModItems.CampingBag.itemID, this.rand, this.func_82188_j(0.3F));
        		addBlacksmithItem(var2, ModItems.TentTools.itemID, this.rand, this.func_82188_j(0.3F));
      
        		addMerchantItem(var2, ModItems.radish.itemID, this.rand, 0.3F);
        		addMerchantItem(var2, ModItems.Marshmallow.itemID, this.rand, 0.3F);
        		addMerchantItem(var2, ModItems.MarshmallowFood.itemID, this.rand, 0.3F);
        		addMerchantItem(var2, ModItems.radishSeed.itemID, this.rand, 0.3F);
        		addMerchantItem(var2, ModItems.TentParts.itemID, this.rand, 0.3F);
        		addMerchantItem(var2, ModItems.SleepingBag.itemID, this.rand, 0.3F);

        if (var2.isEmpty())
        {
        	 addBlacksmithItem(var2, ModItems.guideBook.itemID, this.rand, this.func_82188_j(1.0F));
        }

                
        Collections.shuffle(var2);

        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
        }

        for (int var9 = 0; var9 < par1 && var9 < var2.size(); ++var9)
        {
            this.buyingList.addToListWithCheck((MerchantRecipe)var2.get(var9));
        }
    }

    @SideOnly(Side.CLIENT)
    public void setRecipes(MerchantRecipeList par1MerchantRecipeList) {}

    /**
     * each recipie takes a random stack from villagerStockList and offers it for 1 emerald
     */
    public static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3)
    {
        if (par2Random.nextFloat() < par3)
        {
            par0MerchantRecipeList.add(new MerchantRecipe(getRandomSizedStack(par1, par2Random), Item.emerald));
        }
    }

    private static ItemStack getRandomSizedStack(int par0, Random par1Random)
    {
        return new ItemStack(par0, getRandomCountForItem(par0, par1Random), 0);
    }

    /**
     * default to 1, and villagerStockList contains a min/max amount for each index
     */
    private static int getRandomCountForItem(int par0, Random par1Random)
    {
        Tuple var2 = (Tuple)villagerStockList.get(Integer.valueOf(par0));
        return var2 == null ? 1 : (((Integer)var2.getFirst()).intValue() >= ((Integer)var2.getSecond()).intValue() ? ((Integer)var2.getFirst()).intValue() : ((Integer)var2.getFirst()).intValue() + par1Random.nextInt(((Integer)var2.getSecond()).intValue() - ((Integer)var2.getFirst()).intValue()));
    }

    public static void addBlacksmithItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3)
    {
        if (par2Random.nextFloat() < par3)
        {
            int var4 = getRandomCountForBlacksmithItem(par1, par2Random);
            ItemStack var5;
            ItemStack var6;

            if (var4 < 0)
            {
                var5 = new ItemStack(Item.emerald.itemID, 1, 0);
                var6 = new ItemStack(par1, -var4, 0);
            }
            else
            {
            	int metadata = 0;
 
                if(par1==ModItems.CampingBag.itemID)
                {	
                	metadata = MathHelper.getRandomIntegerInRange(par2Random, 0, 2);
                	var4+=(metadata*MathHelper.getRandomIntegerInRange(par2Random, 2, 4));
                }
                if(par1==ModItems.guideBook.itemID)
                {	
                	metadata = MathHelper.getRandomIntegerInRange(par2Random, 0, 4);
                }
                
                var5 = new ItemStack(Item.emerald.itemID, var4, 0);
                var6 = new ItemStack(par1, 1, metadata);

            }

            par0MerchantRecipeList.add(new MerchantRecipe(var5, var6));
        }
    }

    private static int getRandomCountForBlacksmithItem(int par0, Random par1Random)
    {
        Tuple var2 = (Tuple)blacksmithSellingList.get(Integer.valueOf(par0));
        return var2 == null ? 1 : (((Integer)var2.getFirst()).intValue() >= ((Integer)var2.getSecond()).intValue() ? ((Integer)var2.getFirst()).intValue() : ((Integer)var2.getFirst()).intValue() + par1Random.nextInt(((Integer)var2.getSecond()).intValue() - ((Integer)var2.getFirst()).intValue()));
    }
   
    public void func_82187_q()
    {
        this.field_82190_bM = true;
    }

    static
    { 	
		
        villagerStockList.put(Integer.valueOf(ModItems.radish.itemID), new Tuple(Integer.valueOf(8), Integer.valueOf(16)));
        villagerStockList.put(Integer.valueOf(ModItems.Marshmallow.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(8)));
        villagerStockList.put(Integer.valueOf(ModItems.MarshmallowFood.itemID), new Tuple(Integer.valueOf(3), Integer.valueOf(6)));
        villagerStockList.put(Integer.valueOf(ModItems.radishSeed.itemID), new Tuple(Integer.valueOf(12), Integer.valueOf(24)));
        villagerStockList.put(Integer.valueOf(ModItems.TentParts.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(20)));
        villagerStockList.put(Integer.valueOf(ModItems.SleepingBag.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4))); 
        
        blacksmithSellingList.put(Integer.valueOf(ModItems.TentTools.itemID), new Tuple(Integer.valueOf(-2), Integer.valueOf(2)));
        blacksmithSellingList.put(Integer.valueOf(ModItems.CampingBag.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        blacksmithSellingList.put(Integer.valueOf(ModItems.guideBook.itemID), new Tuple(Integer.valueOf(1), Integer.valueOf(3)));
        
    }
    
	@Override
	public EntityAgeable createChild(EntityAgeable var1) 
	{
		return null;
	}
}
