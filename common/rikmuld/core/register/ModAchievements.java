package rikmuld.core.register;

import java.awt.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.AchievementPage;
import rikmuld.core.helper.ItemStackHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModAchievements {

	public static Achievement CampersTool;
	public static Achievement Campfire;
	public static Achievement CampfireMultiCook;
	public static Achievement CampfireFastCook;
	public static Achievement CampfireCheapCook;
	public static Achievement CampfireInstaCook;
	public static Achievement MarshMallowFood;
	public static Achievement TentParts;
	public static Achievement Tent;
	public static Achievement TentStorage;
	public static Achievement TentSleeping;
	public static Achievement Radish;
	public static Achievement RadishFood;
	public static Achievement CampingBagSmall;
	public static Achievement CampingBagNormal;
	public static Achievement CampingBagLarge;
	public static Achievement CamperTrade;
	public static Achievement Guides;

	public static AchievementPage CampingMillestones;
	
	public static ItemStack campfire[] = ItemStackHelper.getMetaCycle(ModBlocks.campfire, 5);
	public static ItemStack bag[] = ItemStackHelper.getMetaCycle(ModItems.CampingBag, 3);
	
	public static void init() 
	{	
		CampersTool = new Achievement(40,"CampersTool", 1, 0, ModItems.CampTool, null).registerAchievement();
		Campfire = new Achievement(41,"Campfire", 0, -2, campfire[0], ModAchievements.CampersTool).registerAchievement();
		CampfireMultiCook = new Achievement(43,"CampfireMultiCook", -1, 0, campfire[1], ModAchievements.Campfire).registerAchievement();
		CampfireFastCook = new Achievement(44, "CampfireFastCook", -1, 1, campfire[2], ModAchievements.Campfire).registerAchievement();
		CampfireCheapCook = new Achievement(45,"CampfireCheapCook", -1, 2, campfire[3], ModAchievements.Campfire).registerAchievement();
		CampfireInstaCook = new Achievement(46,"CampfireInstaCook", -2, 1, campfire[4], ModAchievements.CampfireFastCook).registerAchievement();
		MarshMallowFood = new Achievement(47,"MarshMallowFood", -2, -2, ModItems.MarshmallowFood,ModAchievements.Campfire).registerAchievement();
		TentParts = new Achievement(48,"TentParts", 2, -2, ModItems.TentParts, ModAchievements.CampersTool).registerAchievement();
		Tent = new Achievement(49,"Tent", 4, -1, ModBlocks.tent, ModAchievements.TentParts).registerAchievement();
		TentStorage = new Achievement(50,"TentStorage", 3, 1, Block.chest, ModAchievements.Tent).registerAchievement();
		TentSleeping = new Achievement(51,"TentSleeping", 3, 2, Item.bed, ModAchievements.Tent).registerAchievement();
		Radish = new Achievement(52,"Radish", 2, 1, ModItems.radishSeed, ModAchievements.CampersTool).registerAchievement();
		RadishFood = new Achievement(53,"RadishFood", 2, 2, ModItems.radish, ModAchievements.Radish).registerAchievement();
		CampingBagSmall = new Achievement(54,"CampingBagSmall", 0, -3, bag[0], ModAchievements.CampersTool).registerAchievement();
		CampingBagNormal = new Achievement(55,"CampingBagNormal", -1, -3, bag[1], ModAchievements.CampingBagSmall).registerAchievement();
		CampingBagLarge = new Achievement(56,"CampingBagLarge", -2, -3, bag[2], ModAchievements.CampingBagNormal).registerAchievement();
		CamperTrade = new Achievement(57,"CamperTrade", 2, -3, Item.emerald, ModAchievements.CampersTool).registerAchievement();
		Guides = new Achievement(58,"Guides", 4, -3, ModItems.guideBook, ModAchievements.CamperTrade).registerAchievement();
		
		ModAchievements.addAchievementLocalizations();
		
		CampingMillestones = new AchievementPage("Camping milestones", CampersTool, Campfire, TentParts, MarshMallowFood, CampfireMultiCook, CampfireFastCook, CampfireCheapCook, CampfireInstaCook, Tent, TentStorage, TentSleeping, Radish, RadishFood, CampingBagSmall, CampingBagNormal, CampingBagLarge, CamperTrade, Guides);		
		AchievementPage.registerAchievementPage(CampingMillestones);
	}
	
	private static void addAchievementName(String ach, String name) 
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach,"en_US", name);
	}

	private static void addAchievementDesc(String ach, String desc) 
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}
	
	static void addAchievementLocalizations() 
	{
		ModAchievements.addAchievementName("CampersTool", "First things first!");
		ModAchievements.addAchievementName("Campfire", "Campfires!");
		ModAchievements.addAchievementName("CampfireMultiCook", "Cooking efficient!");
		ModAchievements.addAchievementName("CampfireFastCook", "Power Cooker!");
		ModAchievements.addAchievementName("CampfireCheapCook", "We don't need coal!");
		ModAchievements.addAchievementName("CampfireInstaCook", "Campfire legend!");
		ModAchievements.addAchievementName("MarshMallowFood", "Campfire tales!");
		ModAchievements.addAchievementName("TentParts", "The begining of tents!");
		ModAchievements.addAchievementName("Tent", "Tents are awesome!");
		ModAchievements.addAchievementName("TentStorage", "The power of storage!");
		ModAchievements.addAchievementName("TentSleeping", "Sleepover!");
		ModAchievements.addAchievementName("Radish", "Fast-Food");
		ModAchievements.addAchievementName("RadishFood", "Bitter-Sweet!");
		ModAchievements.addAchievementName("CampingBagSmall", "Portalble storage!");
		ModAchievements.addAchievementName("CampingBagNormal", "Store-More!");
		ModAchievements.addAchievementName("CampingBagLarge", "Hiking trip!");
		ModAchievements.addAchievementName("CamperTrade", "Trading!");
		ModAchievements.addAchievementName("Guides", "Learning...!");

		ModAchievements.addAchievementDesc("CampersTool", "Make the camper's tool!");
		ModAchievements.addAchievementDesc("Campfire", "Make a campfire!");
		ModAchievements.addAchievementDesc("CampfireMultiCook","Make the multi cooking campfire!");
		ModAchievements.addAchievementDesc("CampfireFastCook","Make the fast cooking campfire!");
		ModAchievements.addAchievementDesc("CampfireCheapCook","Make the cheap cooking campfire!");
		ModAchievements.addAchievementDesc("CampfireInstaCook","Make the insta cooking campfire!");
		ModAchievements.addAchievementDesc("MarshMallowFood", "Cook a marshmallow!");
		ModAchievements.addAchievementDesc("TentParts", "Make some tent parts!");
		ModAchievements.addAchievementDesc("Tent", "Make a tent!");
		ModAchievements.addAchievementDesc("TentStorage", "Make a storage tent!");
		ModAchievements.addAchievementDesc("TentSleeping", "Make a sleeping tent!");
		ModAchievements.addAchievementDesc("Radish", "Plant some radish!");
		ModAchievements.addAchievementDesc("RadishFood", "Eat some radish!");
		ModAchievements.addAchievementDesc("CampingBagSmall", "Make a small camping bag!");
		ModAchievements.addAchievementDesc("CampingBagNormal", "Make a normal camping bag!");
		ModAchievements.addAchievementDesc("CampingBagLarge", "Make a large camping bag!");
		ModAchievements.addAchievementDesc("CamperTrade", "Take a look at the camper trading!");
		ModAchievements.addAchievementDesc("Guides", "Read a guide book;");
	}
}
