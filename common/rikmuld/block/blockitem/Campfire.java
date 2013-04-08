package rikmuld.block.blockitem;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.block.CampingBlockItem;
import rikmuld.client.renderer.particles.Particles;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Blocks;
import rikmuld.core.lib.Config;
import rikmuld.core.register.ModAchievements;
import rikmuld.core.register.ModBlocks;
import rikmuld.core.register.ModItems;
import rikmuld.tileentity.TileEntityCampfire;
import rikmuld.tileentity.TileEntityCampfireCheapCooker;
import rikmuld.tileentity.TileEntityCampfireFastCooker;
import rikmuld.tileentity.TileEntityCampfireMultiCooker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Campfire extends CampingBlockItem{

	private Random campfireRand = new Random();
	private static boolean keepCampfireInventory = false;
	Minecraft mc;
	RenderEngine renderEngine;
	
	public Campfire(int id) 
	{
		super(id, Material.fire);
		setUnlocalizedName(Blocks.BLOCK_META_CAMP_NAME);
		setCreativeTab(CampingMod.customTab);
		setHardness(3.0F);
		setLightValue(1.0F);
		setStepSound(soundWoodFootstep);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
	}

	public final boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }
    
    public final boolean isBlockBurning(World world, int x, int y, int z) 
    {
    	if(Config.CAMPFIRE_CAN_BURN)
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    }

    @SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return getBlockEntity(meta);
    }

	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.torchWood.blockID;
    }
	
    public TileEntityCampfire getBlockEntity(int var1)
    {
        switch (var1)
        {
        	case 0:
        		return new TileEntityCampfire();
            case 1:
                return new TileEntityCampfireMultiCooker();
            case 2:
                return new TileEntityCampfireFastCooker();
            case 3:
                return new TileEntityCampfireCheapCooker();
            case 4:
        		return new TileEntityCampfire();
            default:
                return null;
        }
    }
 
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(3) + 1;
    }
 
    @SideOnly(Side.CLIENT)
    public final void getSubBlocks(int unknown, CreativeTabs tab, List subItems) 
    {
    	if(Config.CAMPFIRE_INSTA_COOK_ENABLED==true)
    	{
	    	for (int ix = 0; ix < 5; ix++) 
	 		{
		 		subItems.add(new ItemStack(this, 1, ix));
	 		}
    	}
    	else
    	{
    		for (int ix = 0; ix < 4; ix++) 
	 		{
		 		subItems.add(new ItemStack(this, 1, ix));
	 		}
    	}
    }
 
    public final boolean isOpaqueCube()
    {
	  return false;
	}
    
	public final boolean renderAsNormalBlock()
	{
	  return false;
	}
	
	public final int getRenderType() 
	{
	  return -1;
	}

	public void ColorParticle(World par1world, int x, int y, int z, EntityPlayer player)
	{
		if(Config.CAMPFIRE_CAN_BE_RECOLORED)
		{	
			TileEntityCampfire Tile = (TileEntityCampfire) par1world.getBlockTileEntity(x, y, z);
			ItemStack currentitem = player.getCurrentEquippedItem();
			if(Tile.setColor(currentitem.getItemDamage())) currentitem.stackSize -= 1;	
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		double var7 = (double)((float)par2 + 0.5F);
     	double var9 = (double)((float)par3 + 0.3F);
     	double var11 = (double)((float)par4 + 0.5F);

     	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
     	TileEntityCampfire Tile = (TileEntityCampfire) par1World.getBlockTileEntity(par2, par3, par4);
     	Particles render = new Particles();
     	
     	par1World.spawnParticle("smoke", var7, var9, var11, 0.0D, 0.05D, 0.0D);
     	render.doSpawnParticle("coloredflame", var7, var9, var11, 0.0D, 0.05D, 0.0D, Tile.getColor(par1World.getBlockMetadata(par2, par3, par4)));    	
	} 
	
	 @Override
	 public boolean onBlockActivated(World par1world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
	 {
		 int var6 = par1world.getBlockMetadata(x, y, z);
		 ItemStack currentitem = player.getCurrentEquippedItem();
				
				if (var6==0)
				{
					if (currentitem != null)
					{
						if (currentitem.itemID == Item.dyePowder.itemID)
						{
							ColorParticle(par1world, x, y, z, player);
							return true;
						}
						
						if (currentitem.itemID == Item.stick.itemID)
					    {
							if(Config.CAMPFIRE_CAN_TRANS_STICK)
							{
								if(currentitem.stackSize>=2)
								{
								     ItemStack torch = new ItemStack(Block.torchWood.blockID, 1, 0);
								     player.inventory.addItemStackToInventory(torch);
								     currentitem.stackSize-=2;
								}
							}
					    }
						
						if (currentitem.itemID == ModItems.Marshmallow.itemID&&currentitem.getItemDamage() == 1)
						{			    
						     ItemStack marsh = new ItemStack(ModItems.CampingFood.itemID, currentitem.stackSize, 0);
						     player.destroyCurrentEquippedItem();
						     player.inventory.addItemStackToInventory(marsh);
						     player.addStat(ModAchievements.MarshMallowFood, 1);
						}
					    	
						if (ToolHelper.isTool(currentitem))
						{
						     ItemStack camp = new ItemStack(ModBlocks.campfire.blockID, 1, 0);
					         player.inventory.addItemStackToInventory(camp);
					    	 par1world.setBlock(x, y, z, 0 , 0, 2);		
					    	 player.destroyCurrentEquippedItem();				    	
					    	 player.inventory.addItemStackToInventory(ToolHelper.addDamage(currentitem, player));
					    	 return true;
						}
					}
				}
					
				if (var6==1)
				{
					if (currentitem != null)
					{
						if (currentitem.itemID == Item.dyePowder.itemID)
						{
							ColorParticle(par1world, x, y, z, player);
							return true;
						}
						if(Config.CAMPFIRE_CAN_TRANS_STICK)
						{
							if(currentitem.stackSize>=2)
							{
							     ItemStack torch = new ItemStack(Block.torchWood.blockID, 1, 0);
							     player.inventory.addItemStackToInventory(torch);
							     currentitem.stackSize-=2;
							}
						}					
						if (currentitem.itemID == ModItems.Marshmallow.itemID&&currentitem.getItemDamage() == 1)
						{
						     ItemStack marsh = new ItemStack(ModItems.CampingFood, currentitem.stackSize, 0);
						     player.destroyCurrentEquippedItem();
						     player.inventory.addItemStackToInventory(marsh);
						     player.addStat(ModAchievements.MarshMallowFood, 1);
						     return true;
						}			
						if (ToolHelper.isTool(currentitem))
						{
						     ItemStack camp = new ItemStack(ModBlocks.campfire.blockID, 1, 1);
					         player.inventory.addItemStackToInventory(camp);
							 par1world.setBlock(x, y, z, 0 , 0, 2);
					    	 player.destroyCurrentEquippedItem();				    	
					    	 player.inventory.addItemStackToInventory( ToolHelper.addDamage(currentitem, player));
						   	 return true;
						}	
					player.openGui(CampingMod.instance, 1, par1world, x, y, z);
					return true;
					}
					player.openGui(CampingMod.instance, 1, par1world, x, y, z);
					return true;
				}
				
				if(var6==2)
				{
					if (currentitem != null)
					{
						 if (currentitem.itemID == Item.dyePowder.itemID)
						 {
							ColorParticle(par1world, x, y, z, player);
							return true;
						 }
						 if (currentitem.itemID == Item.stick.itemID)
						 {
							 if(Config.CAMPFIRE_CAN_TRANS_STICK)
							 {
								 if(currentitem.stackSize>=2)
								 {
									 	ItemStack torch = new ItemStack(Block.torchWood.blockID, 1, 0);
									 	player.inventory.addItemStackToInventory(torch);
									 	currentitem.stackSize-=2;	
								 }
							 }
						 }
						 
						 if (currentitem.itemID == ModItems.Marshmallow.itemID&&currentitem.getItemDamage() == 1)
						 {   
						     ItemStack marsh = new ItemStack(ModItems.CampingFood, currentitem.stackSize, 0);
						     player.destroyCurrentEquippedItem();
						     player.inventory.addItemStackToInventory(marsh);
						     player.addStat(ModAchievements.MarshMallowFood, 1);
						     return true;    
						 }
						 
						if (ToolHelper.isTool(currentitem))
						{
						     ItemStack camp = new ItemStack(ModBlocks.campfire.blockID, 1, 2);
					         player.inventory.addItemStackToInventory(camp);
					    	 par1world.setBlock(x, y, z, 0 , 0, 2);					    	 
					    	 player.destroyCurrentEquippedItem();				    	
					    	 player.inventory.addItemStackToInventory( ToolHelper.addDamage(currentitem, player));
						   	 return true;
						}
						player.openGui(CampingMod.instance, 2, par1world, x, y, z);
						return true;
					}
				player.openGui(CampingMod.instance, 2, par1world, x, y, z);
				return true;
			}
				if (var6==4)
				{
					if (currentitem != null)
					{
						if (currentitem.itemID == Item.dyePowder.itemID)
						{
							ColorParticle(par1world, x, y, z, player);
							return true;
						}
						if (currentitem.itemID == Item.stick.itemID)
						{
							if(Config.CAMPFIRE_CAN_TRANS_STICK)
							{
								if(currentitem.stackSize>=2)
								{
								     ItemStack torch = new ItemStack(Block.torchWood.blockID, 1, 0);
								     player.inventory.addItemStackToInventory(torch);
								     currentitem.stackSize-=2;
								}
							}
						}	
					    else if (currentitem.itemID == Item.beefRaw.itemID)
					    {
					    	ItemStack beefCooked = new ItemStack(Item.beefCooked, currentitem.stackSize, 0);
			  		     	player.destroyCurrentEquippedItem();
			  		     	player.inventory.addItemStackToInventory(beefCooked);
						}
						
					    else if (currentitem.itemID == Item.chickenRaw.itemID)
					    {	
					    	ItemStack chickenCooked = new ItemStack(Item.chickenCooked, currentitem.stackSize, 0);
				  		    player.destroyCurrentEquippedItem();
				  		    player.inventory.addItemStackToInventory(chickenCooked);
			    	   	}
						
					    else if (currentitem.itemID == Item.fishRaw.itemID)
					    {	
					    	ItemStack fishCooked = new ItemStack(Item.fishCooked, currentitem.stackSize, 0);
				  		    player.destroyCurrentEquippedItem();
				  		    player.inventory.addItemStackToInventory(fishCooked);
			    	   	}
						
					    else if (currentitem.itemID == Item.porkRaw.itemID)
					    {	
					    	ItemStack porkCooked = new ItemStack(Item.porkCooked, currentitem.stackSize, 0);
				  		    player.destroyCurrentEquippedItem();
				  		    player.inventory.addItemStackToInventory(porkCooked);
			    	   	}
						
					    else if (currentitem.itemID == Item.potato.itemID)
					    {	
					    	ItemStack bakedPotato = new ItemStack(Item.bakedPotato, currentitem.stackSize, 0);
				  		    player.destroyCurrentEquippedItem();
				  		    player.inventory.addItemStackToInventory(bakedPotato);
			    	   	}
						
					    else if (currentitem.itemID == ModItems.Marshmallow.itemID&&currentitem.getItemDamage() == 1)
					    {			    
						     ItemStack marsh = new ItemStack(ModItems.CampingFood.itemID, currentitem.stackSize, 0);
						     player.destroyCurrentEquippedItem();
						     player.inventory.addItemStackToInventory(marsh);
						     player.addStat(ModAchievements.MarshMallowFood, 1);
					    }
						
						if (ToolHelper.isTool(currentitem))
					    {
						    ItemStack camp = new ItemStack(ModBlocks.campfire.blockID, 1, 4);
					        player.inventory.addItemStackToInventory(camp);
					    	par1world.setBlock(x, y, z, 0 , 0, 2);		    	
					    	player.destroyCurrentEquippedItem();				    	
					    	player.inventory.addItemStackToInventory( ToolHelper.addDamage(currentitem, player));
					   	 	return true;
						}
					}
					return true;
				}
						
				if (var6==3)
				{
					 if (currentitem != null)
					 {
						 if (currentitem.itemID == Item.dyePowder.itemID)
						 {
							ColorParticle(par1world, x, y, z, player);
							return true;
						 }
						 if (currentitem.itemID == Item.stick.itemID)
						 {
							 if(Config.CAMPFIRE_CAN_TRANS_STICK)
							 {
								 if(currentitem.stackSize>=2)
								 {
									 ItemStack torch = new ItemStack(Block.torchWood.blockID, 1, 0);
								     player.inventory.addItemStackToInventory(torch);
								     currentitem.stackSize-=2;
								 }
							 }
						 }
						 
						 if (currentitem.itemID == ModItems.Marshmallow.itemID&&currentitem.getItemDamage() == 1)
						 {				    
						     ItemStack marsh = new ItemStack(ModItems.CampingFood, currentitem.stackSize, 0);
						     player.destroyCurrentEquippedItem();
						     player.inventory.addItemStackToInventory(marsh);
						     player.addStat(ModAchievements.MarshMallowFood, 1);
						     return true;			    
						 }
		   	
						if (ToolHelper.isTool(currentitem))
					    {
						    ItemStack camp = new ItemStack(ModBlocks.campfire.blockID, 1, 3);
					        player.inventory.addItemStackToInventory(camp);
					    	par1world.setBlock(x, y, z, 0 , 0, 2);
					    	player.destroyCurrentEquippedItem();				    	
					    	player.inventory.addItemStackToInventory( ToolHelper.addDamage(currentitem, player));
						   	return true;
						}
						player.openGui(CampingMod.instance, 3, par1world, x, y, z);
						return true;
					}
					player.openGui(CampingMod.instance, 3, par1world, x, y, z);
					return true;
				}
				return true;
			}
	
	 public final int tickRate()
	 {
		return 10;
	 }

	 public void onBlockAdded(World par1World, int par2, int par3, int par4)
	 {
		 par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
	 }

	 public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	 {
		 par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
	 }

	 public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	 {
		 par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
		 this.dropIfCantStay(par1World, par2, par3, par4, par5Random);
	 }	

	 private boolean dropIfCantStay(World world, int x, int y, int z, Random random)
	 {
		if(world.doesBlockHaveSolidTopSurface(x, y-1, z))
		{
			return true;
		}
		
		else
		{
			world.setBlock(x, y, z, 0, 0, 2);
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(Block.torchWood, 3));
		}
		return false;
	 }
	 
	 public void updateCampfireBlockState(boolean par0, World par1World, int par2, int par3, int par4)
		{
		    int var5 = par1World.getBlockMetadata(par2, par3, par4);
		    TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
		    keepCampfireInventory = true;
		
		    if (par0)
		    {
		        par1World.setBlockMetadataWithNotify(par2, par3, par4, this.blockID, 2);
		    }
		    
		    else
		    {
		        par1World.setBlockMetadataWithNotify(par2, par3, par4, this.blockID, 2);
		    }
		
		    keepCampfireInventory = false;
		    par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 2);
		
		    if (var6 != null)
		    {
		        var6.validate();
		        par1World.setBlockTileEntity(par2, par3, par4, var6);
		    }
		}
	 
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
	    if (!keepCampfireInventory)
	    {
	        TileEntityCampfire var7 = (TileEntityCampfire)par1World.getBlockTileEntity(par2, par3, par4);
	
	        if (var7 != null)
	        {
	            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
	            {
	                ItemStack var9 = var7.getStackInSlot(var8);
	
	                if (var9 != null)
	                {
	                    float var10 = this.campfireRand.nextFloat() * 0.8F + 0.1F;
	                    float var11 = this.campfireRand.nextFloat() * 0.8F + 0.1F;
	                    float var12 = this.campfireRand.nextFloat() * 0.8F + 0.1F;
	
	                    while (var9.stackSize > 0)
	                    {
	                        int var13 = this.campfireRand.nextInt(21) + 10;
	
	                        if (var13 > var9.stackSize)
	                        {
	                            var13 = var9.stackSize;
	                        }
	
	                        var9.stackSize -= var13;
	                        EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));
	
	                        if (var9.hasTagCompound())
	                        {
	                        	var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
	                        }
	
	                        float var15 = 0.05F;
	                        var14.motionX = (double)((float)this.campfireRand.nextGaussian() * var15);
	                        var14.motionY = (double)((float)this.campfireRand.nextGaussian() * var15 + 0.2F);
	                        var14.motionZ = (double)((float)this.campfireRand.nextGaussian() * var15);
	                        par1World.spawnEntityInWorld(var14);
	                    }
	                }
	            }
	        }
	    }
	
	    super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
}
