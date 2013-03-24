package rikmuld.core.register;

import cpw.mods.fml.common.registry.GameRegistry;
import rikmuld.core.helper.RecipieHelper;
import rikmuld.core.lib.Config;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModRecipies {

	public static boolean insta = Config.CAMPFIRE_INSTA_COOK_ENABLED;
	
    public static ItemStack iron = new ItemStack(Item.ingotIron);
    public static ItemStack[] rosered = RecipieHelper.getMetaCycle(Item.dyePowder, 16);
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
	public static ItemStack[] wool = RecipieHelper.getMetaCycle(Block.cloth, 16);
	public static ItemStack string = new ItemStack(Item.silk);
	public static ItemStack chest = new ItemStack(Block.chest);
	public static ItemStack[] toolCamp = RecipieHelper.getMetaCycle(ModItems.TentTools, 100);
	public static ItemStack[] campfire = RecipieHelper.getMetaCycle(ModBlocks.campfire, 5);
	public static ItemStack[] marshmallow = RecipieHelper.getMetaCycle(ModItems.Marshmallow, 2);
	public static ItemStack tent = new ItemStack(ModBlocks.tent);
	public static ItemStack[] tentParts = RecipieHelper.getMetaCycle(ModItems.TentParts, 2);
	public static ItemStack SleepBag = new ItemStack(ModItems.SleepingBag);
	public static ItemStack[] campingBag = RecipieHelper.getMetaCycle(ModItems.CampingBag, 3);
	
	public static void init() 
	{
		if(insta) RecipieHelper.addShapelessRecipe(campfire[4], 1, campfire[0], campfire[1], campfire[2], campfire[3]);
		RecipieHelper.addShapelessRecipe(marshmallow[1], 3, stick, marshmallow[0]);
		RecipieHelper.addShapelessMetaCycleRecipie(tentParts[0], 4, toolCamp, iron);
		RecipieHelper.addShapelessMetaCycleRecipie(tentParts[1], 10, toolCamp, string, string, string, string, string, string);
		RecipieHelper.addRecipe(tent, 1, RecipieHelper.getArrayStacks(tentParts[1], tentParts[0]), "000", "0 0", "1 1");
		RecipieHelper.addRecipe(SleepBag, 1, RecipieHelper.getArrayStacks(Block.cloth), "000");
		RecipieHelper.addRecipe(toolCamp[0], 1, RecipieHelper.getArrayStacks(rosered[1], iron), "010", "010", "010");
		RecipieHelper.addRecipe(campfire[0], 1, RecipieHelper.getArrayStacks(torch, flint, stone), " 0 ", "010", "222");
		RecipieHelper.addRecipe(campfire[1], 1, RecipieHelper.getArrayStacks(furnace, campfire[0]), " 0 ", "010", " 0 ");
		RecipieHelper.addRecipe(campfire[2], 1, RecipieHelper.getArrayStacks(flintst, campfire[0]), " 0 ", "010", " 0 ");
		RecipieHelper.addRecipe(campfire[3], 1, RecipieHelper.getArrayStacks(enderp, campfire[0]), " 0 ", "010", " 0 ");
		RecipieHelper.addRecipe(marshmallow[0], 4, RecipieHelper.getArrayStacks(sugar, waterbottle, egg, bowl), "010", "020", "030");
		RecipieHelper.addRecipe(campingBag[0], 1, RecipieHelper.getArrayStacks(tentParts[1]), "000", "0 0", "000");
		RecipieHelper.addRecipe(campingBag[1], 1, RecipieHelper.getArrayStacks(tentParts[1], campingBag[0]), "000", "010", "000");
		RecipieHelper.addRecipe(campingBag[2], 1, RecipieHelper.getArrayStacks(tentParts[1], campingBag[1]), "000", "010", "000");
	}
}
