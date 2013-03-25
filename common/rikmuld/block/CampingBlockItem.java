package rikmuld.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import rikmuld.core.lib.ModInfo;

public abstract class CampingBlockItem extends BlockContainer{
	
	protected CampingBlockItem(int par1, Material material)
	{
		super(par1, material);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{		
		blockIcon = iconRegister.registerIcon(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5)+"_Particle");
	}
}
