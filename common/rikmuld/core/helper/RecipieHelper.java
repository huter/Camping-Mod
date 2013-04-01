package rikmuld.core.helper;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipieHelper {
	
	public static void addRecipe(ItemStack output, int count, Object... input)
	{
		output.stackSize = count;
		GameRegistry.addRecipe(output, input);
	}
	
	public static void addShapelessRecipe(ItemStack output, int count, Object... input)
	{
		output.stackSize = count;
		GameRegistry.addShapelessRecipe(output, input);
	}
}
