package rikmuld.core.helper;

import java.util.logging.Level;

import rikmuld.core.register.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipieHelper {
	
	public static void addRecipe(ItemStack output, int count, Object... params)
	{
		output.stackSize = count;
		GameRegistry.addRecipe(output, params);
	}
	
	public static void addShapelessRecipe(ItemStack output, int count, Object... input)
	{
		output.stackSize = count;
		GameRegistry.addShapelessRecipe(output, input);
	}

	public static ItemStack[] getMetaCycle(Object input, int maxMetadata)
	{
		ItemStack[] stack = new ItemStack[maxMetadata];
		for(int i = 0; i<maxMetadata; i++)
		{
			if(input instanceof Block) stack[i] = new ItemStack((Block)input, 1, i);
			if(input instanceof Item) stack[i] = new ItemStack((Item)input, 1, i);
		}
		return stack;
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

	public static Object[] getArrayStacks(Object... stack) 
	{
		return stack;
	}
}
