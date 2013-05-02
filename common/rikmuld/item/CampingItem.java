package rikmuld.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import rikmuld.CampingMod;
import rikmuld.core.lib.ModInfo;

public abstract class CampingItem extends Item{

	private Icon[][] iconBuffer;
    private String[] metadata;
	   
	public CampingItem(int par1, String[] meta) 
	{
		super(par1);
		this.setCreativeTab(CampingMod.customTab);
		metadata = meta;
	}
	
	public CampingItem(int par1) 
	{
		super(par1);
		this.setCreativeTab(CampingMod.customTab);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(this.metadata == null)
		{
			itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5));
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
    		itemIcon = iconBuffer[par1][0];
		}
    	return this.itemIcon;
    }
    
    public void doKeyAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) {}
}
