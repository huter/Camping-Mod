package rikmuld.item;

import rikmuld.core.lib.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.util.Icon;

public abstract class CampingItemFood extends ItemFood{

	private Icon[][] iconBuffer;
    private String[] metadata;
    
	public CampingItemFood(int par1, int par2, boolean par4, String[] meta) 
	{
		super(par1, par2, par4);
		metadata = meta;
	}
	
	public CampingItemFood(int par1, int par2, boolean par4) 
	{
		super(par1, par2, par4);	
	}
	
	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		if(this.metadata == null)
		{
			 iconIndex = iconRegister.func_94245_a(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5));
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
    		iconIndex = iconBuffer[par1][0];
		}
    	return this.iconIndex;
    }
}
