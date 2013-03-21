package rikmuld.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;
import rikmuld.core.lib.ModInfo;

public abstract class CampingItemBlock extends ItemBlock {

	private Icon[][] iconBuffer;
    private String[] metadata;
    
	public CampingItemBlock(int par1, String[] meta) 
	{
		super(par1);
		metadata = meta;
	}
	
	public CampingItemBlock(int par1) 
	{
		super(par1);
	}
		
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		if(this.metadata == null)
		{
			 iconIndex = iconRegister.registerIcon(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5));
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
    		iconIndex = iconBuffer[par1][0];
		}
    	return this.iconIndex;
    }
}
		
