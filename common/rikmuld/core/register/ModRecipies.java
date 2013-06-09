package rikmuld.core.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rikmuld.core.helper.ItemStackHelper;
import rikmuld.core.helper.RecipeHelper;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Config;

public class ModRecipies {

	public static boolean insta = Config.CAMPFIRE_INSTA_COOK_ENABLED;
	public static boolean peasfull = Config.GENERAL_RECIPIE_PEASFULL;
	
    public static ItemStack iron = new ItemStack(Item.ingotIron);
    public static ItemStack gold = new ItemStack(Item.ingotGold);
    public static ItemStack leather = new ItemStack(Item.leather);
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
	public static ItemStack[] tentParts = ItemStackHelper.getMetaCycle(ModItems.TentParts, 3);
	public static ItemStack[] campingBag = ItemStackHelper.getMetaCycle(ModItems.CampingBag, 3);
	public static ItemStack campBoot = new ItemStack(ModItems.CampingArmorBoot);
	public static ItemStack campLeg = new ItemStack(ModItems.CampingArmorLeg);
	public static ItemStack campChest = new ItemStack(ModItems.CampingArmorChest);
	public static ItemStack campHelm = new ItemStack(ModItems.CampingArmorHelm);
	public static ItemStack feather = new ItemStack(Item.feather);
	public static ItemStack leatherBoot = new ItemStack(Item.bootsLeather);
	public static ItemStack leatherLeg = new ItemStack(Item.legsLeather);
	public static ItemStack leatherChest = new ItemStack(Item.plateLeather);
	public static ItemStack leatherHelm = new ItemStack(Item.helmetLeather);
	
	public static void init() 
	{
		if(insta) RecipeHelper.addShapelessRecipe(campfire[4], 1, campfire[0], campfire[1], campfire[2], campfire[3]);
		RecipeHelper.addShapelessRecipe(marshmallow[1], 3, stick, stick, stick, marshmallow[0]);
		
		if(!peasfull)
		{
			RecipeHelper.addRecipe(campfire[3], 1, " 0 ", "010", " 0 ", '0', enderp, '1', campfire[0]);
			for(ItemStack[] tool : ToolHelper.toolStacks)
			{
				RecipeHelper.addShapelessRecipe(tentParts[0], 4, ItemStackHelper.getWildValue(tool[0]), iron);
				RecipeHelper.addShapelessRecipe(tentParts[1], 10, ItemStackHelper.getWildValue(tool[0]), string, string, string, string, string, string);
			}
		}
		else
		{
			RecipeHelper.addRecipe(campfire[3], 1, " 0 ", "010", " 0 ", '0', gold, '1', campfire[0]);
			for(ItemStack[] tool : ToolHelper.toolStacks)
			{
				RecipeHelper.addShapelessRecipe(tentParts[0], 4, ItemStackHelper.getWildValue(tool[0]), iron);
				RecipeHelper.addShapelessRecipe(tentParts[1], 10, ItemStackHelper.getWildValue(tool[0]), leather, leather, leather, leather, leather, leather);
			}
		}
				
		for(ItemStack[] tool : ToolHelper.toolStacks)
		{
			RecipeHelper.addRecipe(campBoot, 1, "010", "232", "020", '0', feather, '1', ItemStackHelper.getWildValue(tool[0]), '2', leather, '3', leatherBoot);
			RecipeHelper.addRecipe(campLeg, 1, "010", "232", "020", '0', feather, '1', ItemStackHelper.getWildValue(tool[0]), '2', leather, '3', leatherLeg);
			RecipeHelper.addRecipe(campChest, 1, "010", "232", "020", '0', feather, '1', ItemStackHelper.getWildValue(tool[0]), '2', leather, '3', leatherChest);
			RecipeHelper.addRecipe(campHelm, 1, "010", "232", "020", '0', feather, '1', ItemStackHelper.getWildValue(tool[0]), '2', leather, '3', leatherHelm);
		}
		
		RecipeHelper.addRecipe(tent, 1, "000", "0 0", "1 1", '0', tentParts[1], '1', tentParts[0]);
		RecipeHelper.addRecipe(tentParts[2], 1, "000", '0', ItemStackHelper.getWildValue(wool[0]));
		RecipeHelper.addRecipe(toolCamp[0], 1,  "010", "010", "010", '0', dye[1], '1', iron);
		RecipeHelper.addRecipe(toolCamp2[0], 1,  "121", "101", "121", '0', toolCamp[0], '1', iron, '2', dye[1]);
		RecipeHelper.addRecipe(campfire[0], 1, " 0 ", "010", "222", '0', torch, '1', flint, '2', stone);
		RecipeHelper.addRecipe(campfire[1], 1, " 0 ", "010", " 0 ", '0', furnace, '1', campfire[0]);
		RecipeHelper.addRecipe(campfire[2], 1, " 0 ", "010", " 0 ", '0', flintst, '1', campfire[0]);

		RecipeHelper.addRecipe(marshmallow[0], 4, "010", "020", "030", '0', sugar, '1', waterbottle, '2', egg, '3', bowl);
		RecipeHelper.addRecipe(campingBag[0], 1, "000", "0 0", "000", '0', tentParts[1]);
		RecipeHelper.addRecipe(campingBag[1], 1, "000", "010", "000", '0', tentParts[1], '1', campingBag[0]);
		RecipeHelper.addRecipe(campingBag[2], 1, "000", "010", "000", '0', tentParts[1], '1', campingBag[1]);
	}
}
