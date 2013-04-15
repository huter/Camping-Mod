package rikmuld.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import rikmuld.CampingMod;
import rikmuld.core.lib.ModInfo;

public class CampingItemArmor extends ItemArmor{

	private Icon[][] iconBuffer;
    private String[] metadata;
	   
	public CampingItemArmor(int par1, EnumArmorMaterial material, int par2, int par3, String[] meta) 
	{
		super(par1, material, par2, par3);
		this.setCreativeTab(CampingMod.customTab);
		metadata = meta;
	}
	
	public CampingItemArmor(int par1, EnumArmorMaterial material, int par2, int par3) 
	{
		super(par1, material, par3, par3);
		this.setCreativeTab(CampingMod.customTab);
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
    
    public void doKeyAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) {}
}

