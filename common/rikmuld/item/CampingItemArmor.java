package rikmuld.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.Icon;
import rikmuld.CampingMod;
import rikmuld.core.lib.ModInfo;

public class CampingItemArmor extends ItemArmor {

	private Icon[][] iconBuffer;

	public CampingItemArmor(int par1, EnumArmorMaterial material, int par2, int par3) 
	{
		super(par1, material, par2, par3);
		this.setCreativeTab(CampingMod.customTab);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{	
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID+":"+this.getUnlocalizedName().substring(5));	
	}
}