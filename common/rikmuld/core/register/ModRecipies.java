package rikmuld.core.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rikmuld.core.helper.ItemStackHelper;
import rikmuld.core.helper.RecipieHelper;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Config;

public class ModRecipies {

	public static boolean insta = Config.CAMPFIRE_INSTA_COOK_ENABLED;
	
    public static ItemStack iron = new ItemStack(Item.ingotIron);
    public static ItemStack[] dye = ItemStackHelper.getMetaCycle(Item.dyePowder, 16);
    public static ItemStack torch = new ItemStack(Block.torchWood);
	public static ItemStack flint = new ItemStack(Item.flint);
	public static ItemStack stone = new ItemStack(Block.stone);
	public static ItemStack enderp = new ItemStack(Item.enderPearl);
	public static ItemStack furnace = new ItemStack(Block.furnaceIdle);
	public static ItemStack flintst = new ItemStack(Item.flintAndSteel);
	public static ItemStack waterbottle = new ItemStack(Item.potion);
	public static ItemStack egg = new ItemStack(Item.egg);
	public static ItemStack sugar = new ItemStack(Item.sugar);
	public static ItemStack bowl = new ItemStack(Item.bowlEmpty);
	public static ItemStack stick = new ItemStack(Item.stick);
	public static ItemStack bed = new ItemStack(Item.bed);
	public static ItemStack[] wool = ItemStackHelper.getMetaCycle(Block.cloth, 16);
	public static ItemStack string = new ItemStack(Item.silk);
	public static ItemStack chest = new ItemStack(Block.chest);
	public static ItemStack[] toolCamp = ItemStackHelper.getMetaCycle(ModItems.CampTool, 100);
	public static ItemStack[] toolCamp2 = ItemStackHelper.getMetaCycle(ModItems.CampTool2, 250);
	public static ItemStack[] campfire = ItemStackHelper.getMetaCycle(ModBlocks.campfire, 5);
	public static ItemStack[] marshmallow = ItemStackHelper.getMetaCycle(ModItems.Marshmallow, 2);
	public static ItemStack tent = new ItemStack(ModBlocks.tent);
	public static ItemStack[] tentParts = ItemStackHelper.getMetaCycle(ModItems.TentParts, 2);
	public static ItemStack SleepBag = new ItemStack(ModItems.SleepingBag);
	public static ItemStack[] campingBag = ItemStackHelper.getMetaCycle(ModItems.CampingBag, 3);
	
	public static void init() 
	{
		if(insta) RecipieHelper.addShapelessRecipe(campfire[4], 1, campfire[0], campfire[1], campfire[2], campfire[3]);
		RecipieHelper.addShapelessRecipe(marshmallow[1], 3, stick, marshmallow[0]);
		
		for(ItemStack[] tool : ToolHelper.toolStacks)
		{
			RecipieHelper.addShapelessRecipe(tentParts[0], 4, ItemStackHelper.getWildValleu(tool[0]), iron);
			RecipieHelper.addShapelessRecipe(tentParts[1], 10, ItemStackHelper.getWildValleu(tool[0]), string, string, string, string, string, string);
		}

		RecipieHelper.addRecipe(tent, 1, "000", "0 0", "1 1", '0', tentParts[1], '1', tentParts[0]);
		RecipieHelper.addRecipe(SleepBag, 1, "000", '0', ItemStackHelper.getWildValleu(wool[0]));
		RecipieHelper.addRecipe(toolCamp[0], 1,  "010", "010", "010", '0', dye[1], '1', iron);
		RecipieHelper.addRecipe(toolCamp2[0], 1,  "121", "101", "121", '0', toolCamp[0], '1', iron, '2', dye[1]);
		RecipieHelper.addRecipe(campfire[0], 1, " 0 ", "010", "222", '0', torch, '1', flint, '2', stone);
		RecipieHelper.addRecipe(campfire[1], 1, " 0 ", "010", " 0 ", '0', furnace, '1', campfire[0]);
		RecipieHelper.addRecipe(campfire[2], 1, " 0 ", "010", " 0 ", '0', flintst, '1', campfire[0]);
		RecipieHelper.addRecipe(campfire[3], 1, " 0 ", "010", " 0 ", '0', enderp, '1', campfire[0]);
		RecipieHelper.addRecipe(marshmallow[0], 4, "010", "020", "030", '0', sugar, '1', waterbottle, '2', egg, '3', bowl);
		RecipieHelper.addRecipe(campingBag[0], 1, "000", "0 0", "000", '0', tentParts[1]);
		RecipieHelper.addRecipe(campingBag[1], 1, "000", "010", "000", '0', tentParts[1], '1', campingBag[0]);
		RecipieHelper.addRecipe(campingBag[2], 1, "000", "010", "000", '0', tentParts[1], '1', campingBag[1]);
	}
}
