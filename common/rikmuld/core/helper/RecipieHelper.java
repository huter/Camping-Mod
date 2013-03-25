package rikmuld.core.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
	
	public static void addShapelessMetaCycleRecipie(ItemStack output, int count, ItemStack[] stack, ItemStack... input)
	{	
		Object[] inputStack = new Object[input.length + 1];
		System.arraycopy(input, 0, inputStack, 0, input.length);
		
		for(int a = 0;a<stack.length;a++)
		{
			inputStack[input.length] = stack[a];
			addShapelessRecipe(output, count, inputStack);	
		}
	}
	
	public static void addMetaCycleRecipie(ItemStack output, int count, Object... input)
	{	
		ItemStack[] stack = (ItemStack[]) input[input.length-1];
		
		for(int a = 0;a<stack.length;a++)
		{	
			input[input.length-1] = stack[a];
			addRecipe(output, count, input);	
		}
	}
}
