package rikmuld.core.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.Items;
import rikmuld.item.armor.ArmorBackpack;
import rikmuld.item.food.FoodCamping;
import rikmuld.item.normal.GuideBook;
import rikmuld.item.normal.Marshmellow;
import rikmuld.item.normal.RadishSeed;
import rikmuld.item.normal.TentParts;
import rikmuld.item.tool.ToolCamping;
import rikmuld.item.tool.ToolCampingV2;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems {
	
	public static Item CampTool;
	public static Item CampTool2;
	public static Item Marshmallow;
	public static Item TentParts;
	public static Item CampingBag;
	public static Item radishSeed;
	public static Item guideBook;
	public static ItemFood CampingFood;
	
	public static void init() 
	{
		CampTool = new ToolCamping(Items.ITEM_TOOL_CAMP_ID);
		CampTool2 = new ToolCampingV2(Items.ITEM_TOOL_CAMP2_ID);
		Marshmallow = new Marshmellow(Items.ITEM_MARSH_ID);
		CampingFood = new FoodCamping(Items.ITEM_FOOD_ID);
		TentParts = new TentParts(Items.ITEM_TENT_PARTS_ID);
		CampingBag = new ArmorBackpack(Items.ITEM_TOOL_BACK_ID);
		radishSeed = new RadishSeed(Items.ITEM_RADISH_SEED_ID, ModBlocks.RadishCrop.blockID, Block.grass.blockID);
		guideBook = new GuideBook(Items.ITEM_BOOK_GUIDE_ID);
		
		LanguageRegistry.addName(CampTool, Items.ITEM_TOOL_CAMP_GAME_NAME);
		LanguageRegistry.addName(CampTool2, Items.ITEM_TOOL_CAMP2_GAME_NAME);
		LanguageRegistry.addName(radishSeed, Items.ITEM_RADISH_SEED_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(TentParts, 1, 0), Items.ITEM_TENT_PARTS_PEGS_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(TentParts, 1, 1), Items.ITEM_TENT_PARTS_CANVAS_GAME_NAME);	
		LanguageRegistry.addName(new ItemStack(TentParts, 1, 2), Items.ITEM_TENT_PARTS_SLEEP_GAME_NAME);	
		LanguageRegistry.addName(new ItemStack(CampingFood, 1, 0), Items.ITEM_MARSH_FOOD_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(CampingFood, 1, 1), Items.ITEM_RADISH_FOOD_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(Marshmallow, 1, 0), Items.ITEM_MARSH_NORMAL_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(Marshmallow, 1, 1), Items.ITEM_MARSH_STICK_GAME_NAME);		
		LanguageRegistry.addName(new ItemStack(CampingBag, 1, 0), Items.ITEM_TOOL_BACK_SMALL_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(CampingBag, 1, 1), Items.ITEM_TOOL_BACK_NORMAL_GAME_NAME);	
		LanguageRegistry.addName(new ItemStack(CampingBag, 1, 2), Items.ITEM_TOOL_BACK_LARGE_GAME_NAME);	
		LanguageRegistry.addName(new ItemStack(guideBook, 1, 0), Items.ITEM_BOOK_GUIDE_CAMP_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(guideBook, 1, 1), Items.ITEM_BOOK_GUIDE_TENT_GAME_NAME);	
		LanguageRegistry.addName(new ItemStack(guideBook, 1, 2), Items.ITEM_BOOK_GUIDE_EQUIP_GAME_NAME);	
		LanguageRegistry.addName(new ItemStack(guideBook, 1, 3), Items.ITEM_BOOK_GUIDE_FOOD_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(guideBook, 1, 4), Items.ITEM_BOOK_GUIDE_WORLD_GAME_NAME);
		
	    MinecraftForge.addGrassSeed(new ItemStack(radishSeed), Config.PLANT_RADISH_DROP_RATE);
	}
}
