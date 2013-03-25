package rikmuld.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import rikmuld.core.lib.ModInfo;

public abstract class CampingBlock extends BlockContainer {

	private Icon[][] iconBuffer;
    private String[] metadata;
    
	protected CampingBlock(int par1, Material material)
	{
		super(par1, material);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{		
		if(this.metadata == null)
		{
			blockIcon = iconRegister.registerIcon(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5));
		}
		else
		{
			iconBuffer = new Icon[metadata.length+1][1];
			for(int x = 0; x<metadata.length; x++)
			{
				iconBuffer[x][0] = iconRegister.registerIcon(ModInfo.MOD_ID+":"+this.metadata[x].toString());
			}
		}
	}
	
    public Icon getIconFromDamage(int par1)
    {
    	if(this.metadata != null)
		{
    		blockIcon = iconBuffer[par1][0];
		}
    	return this.blockIcon;
    }
}
