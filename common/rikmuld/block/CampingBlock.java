package rikmuld.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import rikmuld.core.lib.ModInfo;
import rikmuld.tileentity.TileEntityCamping;

public abstract class CampingBlock extends BlockContainer {

	private Icon[][] iconBuffer;
    private String[] metadata;
    
	protected CampingBlock(int par1, Material material)
	{
		super(par1, material);
	}

	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack par6ItemStack)
    {   
        int direction = 0;
        int facing = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (facing == 0) 
        {
            direction = ForgeDirection.NORTH.ordinal();
        }
        else if (facing == 1) 
        {
            direction = ForgeDirection.EAST.ordinal();
        }
        else if (facing == 2) 
        {
            direction = ForgeDirection.SOUTH.ordinal();
        }
        else if (facing == 3) 
        {
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z, direction, 3);
        ((TileEntityCamping) world.getBlockTileEntity(x, y, z)).setDirection(direction);
    }
	
	@Override
	public void func_94332_a(IconRegister iconRegister)
	{		
		if(this.metadata == null)
		{
			field_94336_cN = iconRegister.func_94245_a(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5));
		}
		else
		{
			iconBuffer = new Icon[metadata.length+1][1];
			for(int x = 0; x<metadata.length; x++)
			{
				iconBuffer[x][0] = iconRegister.func_94245_a(ModInfo.MOD_ID+":"+this.metadata[x].toString());
			}
		}
	}
	
    public Icon getIconFromDamage(int par1)
    {
    	if(this.metadata != null)
		{
    		field_94336_cN = iconBuffer[par1][0];
		}
    	return this.field_94336_cN;
    }
}
