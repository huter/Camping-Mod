package rikmuld.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import rikmuld.core.lib.Items;
import rikmuld.core.lib.Textures;
import rikmuld.core.register.ModItems;
import rikmuld.item.CampingItemArmor;

public class ArmorCamping extends CampingItemArmor{

	static EnumArmorMaterial CAMPING = EnumHelper.addArmorMaterial("CAMPING", 25, new int[] { 2, 5, 4, 2 }, 20);
	public static final String[] names = new String[] {Items.ITEM_ARMOR_HELM_NAME, Items.ITEM_ARMOR_CHEST_NAME, Items.ITEM_ARMOR_LEG_NAME,  Items.ITEM_ARMOR_BOOT_NAME};
	
	public ArmorCamping(int id, int armorType) 
	{
		super(id, CAMPING, 0, armorType);
		maxStackSize = 1;
		setUnlocalizedName(names[armorType]);
	}

	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
		if(stack.itemID==ModItems.CampingArmorLeg.itemID)
		{
			return Textures.MODEL_LOCATION + Textures.MODEL_ARMOR_2;
		}
		else
		{
			return Textures.MODEL_LOCATION + Textures.MODEL_ARMOR_1;
		}
    }
}
