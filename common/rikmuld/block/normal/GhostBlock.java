package rikmuld.block.normal;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rikmuld.block.CampingBlock;
import rikmuld.core.lib.Blocks;

public class GhostBlock extends CampingBlock{
	
	public GhostBlock(int id) 
	{
		super(id, Material.air);
		setUnlocalizedName(Blocks.BLOCK_GHOST_NAME);
		setHardness(1.5F);
	}

	public int getRenderType() 
	{
		return -1;
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return this.blockID;
	}

	public boolean isAirBlock(World world, int x, int y, int z) 
	{
		return true;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) 
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return null;
	}
	
	public int quantityDropped(Random par1Random) 
	{
		return 0;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return null;
	}
}