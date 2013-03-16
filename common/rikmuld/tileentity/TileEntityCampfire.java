package rikmuld.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityCampfire extends TileEntity implements IInventory{

	private int feul;
	private boolean nofeul;
	private int cook;  
	int color = 16;
	
	public TileEntityCampfire(int feulmultiply, boolean neednofeul, int cooktime) 
	{
		feul=feulmultiply;
		nofeul=neednofeul;
		cook=cooktime;
	}
	
	public TileEntityCampfire() {}
	    
	@Override
	public int getSizeInventory() 
	{
		return 0;
	}

	public int getInventoryStackLimit()
    {
        return 64;
    }
	  
	@Override
	public ItemStack getStackInSlot(int var1) 
	{
		return null;
	}

    public String getInvName()
    {
        return "container.furnace";
    }
    
	@Override
	public ItemStack decrStackSize(int var1, int var2) 
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) 
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) 
	{
		
	}

	public void openChest() {}
	public void closeChest() {}

	public int getStartInventorySide(ForgeDirection side) 
	{	
		if (side == ForgeDirection.DOWN) return 1;
        if (side == ForgeDirection.UP) return 0; 
        return 2;
	}

	public int getSizeInventorySide(ForgeDirection side) 
	{
		return 1;
	}
	
    public int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
        	if (nofeul==true)
        	{
        		return cook;
        	}
        	else 
        	{
        		return 0;
        	}
        }
        else
        {
            int var1 = par0ItemStack.getItem().itemID;
            Item var2 = par0ItemStack.getItem();

			if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                    return 1*feul;
                }

                if (var3.blockMaterial == Material.wood)
                {
                    return 2*feul;
                }
            }
            if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD")) return 2*feul;
            if (var2 instanceof ItemSword && ((ItemSword) var2).getToolMaterialName().equals("WOOD")) return 2*feul;
            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD")) return 2*feul;
            if (var1 == Item.stick.itemID) return 1*feul;
            if (var1 == Item.coal.itemID) return 16*feul;
            if (var1 == Item.bucketLava.itemID) return 200*feul;
            if (var1 == Block.sapling.blockID) return 1*feul;
            if (var1 == Item.blazeRod.itemID) return 24*feul;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    public boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public boolean func_94042_c()
	{
		return true;
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) 
	{
		return true;
	}

	public void setDefaultColor(int metadata) 
	{
		if(metadata==0) color = 15;
		else if(metadata==1) color = 2;
		else if(metadata==2) color = 4;
		else if (metadata==3) color = 8;
		else if (metadata==4) color = 1;
	}

	public boolean setColor(int itemDamage) 
	{
		if(color==itemDamage) return false;
		else color = itemDamage; return true;
	}
	
	public int getColor(int metadata) 
	{
		if(color==16) setDefaultColor(metadata);
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return color;
	}

	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
      	this.color = (par1NBTTagCompound.getInteger("theColor"));
    }
	
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("theColor", this.color);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) 
    {
     readFromNBT(packet.customParam1);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }
}
