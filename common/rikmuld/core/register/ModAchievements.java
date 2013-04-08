package rikmuld.core.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import rikmuld.core.helper.ItemStackHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModAchievements extends AchievementPage{

	public static ItemStack campfire[] = ItemStackHelper.getMetaCycle(ModBlocks.campfire, 5);
	public static ItemStack bag[] = ItemStackHelper.getMetaCycle(ModItems.CampingBag, 3);
	public static ItemStack food[] = ItemStackHelper.getMetaCycle(ModItems.CampingFood, 2);
	
	public static Achievement CampersTool = (new Achievement(2380,"CampersTool", 1, 0, ModItems.CampTool, null).setIndependent().registerAchievement());
	public static Achievement Campfire = (new Achievement(2381,"Campfire", 0, -2, campfire[0], ModAchievements.CampersTool).registerAchievement());
	public static Achievement CampfireMultiCook = (new Achievement(2382,"CampfireMultiCook", -1, 0, campfire[1], ModAchievements.Campfire).registerAchievement());
	public static Achievement CampfireFastCook = (new Achievement(2383, "CampfireFastCook", -1, 1, campfire[2], ModAchievements.Campfire).registerAchievement());
	public static Achievement CampfireCheapCook = (new Achievement(2384,"CampfireCheapCook", -1, 2, campfire[3], ModAchievements.Campfire).registerAchievement());
	public static Achievement CampfireInstaCook = (new Achievement(2385,"CampfireInstaCook", -2, 1, campfire[4], ModAchievements.CampfireFastCook).setSpecial().registerAchievement());
	public static Achievement MarshMallowFood = (new Achievement(2386,"MarshMallowFood", -2, -2, food[0],ModAchievements.Campfire).registerAchievement());
	public static Achievement TentParts = (new Achievement(2387,"TentParts", 2, -2, ModItems.TentParts, ModAchievements.CampersTool).registerAchievement());
	public static Achievement Tent = (new Achievement(2388,"Tent", 4, -1, ModBlocks.tent, ModAchievements.TentParts).registerAchievement());
	public static Achievement TentStorage = (new Achievement(2389,"TentStorage", 3, 1, Block.chest, ModAchievements.Tent).registerAchievement());
	public static Achievement TentSleeping = (new Achievement(2390,"TentSleeping", 3, 2, Item.bed, ModAchievements.Tent).registerAchievement());
	public static Achievement Radish = (new Achievement(2391,"Radish", 2, 1, ModItems.radishSeed, ModAchievements.CampersTool).registerAchievement());
	public static Achievement RadishFood = (new Achievement(2392,"RadishFood", 2, 2, food[1], ModAchievements.Radish).registerAchievement());
	public static Achievement CampingBagSmall = (new Achievement(2393,"CampingBagSmall", 0, -3, bag[0], ModAchievements.CampersTool).registerAchievement());
	public static Achievement CampingBagNormal = (new Achievement(2394,"CampingBagNormal", -1, -3, bag[1], ModAchievements.CampingBagSmall).registerAchievement());
	public static Achievement CampingBagLarge = (new Achievement(2395,"CampingBagLarge", -2, -3, bag[2], ModAchievements.CampingBagNormal).registerAchievement());
	public static Achievement CamperTrade = (new Achievement(2396,"CamperTrade", 2, -3, Item.emerald, ModAchievements.CampersTool).registerAchievement());
	public static Achievement Guides = (new Achievement(2397,"Guides", 4, -3, ModItems.guideBook, ModAchievements.CamperTrade).registerAchievement());

	public ModAchievements() 
	{
		super("Camping Millestones", new Achievement[] { CampersTool, Campfire, TentParts, MarshMallowFood, CampfireMultiCook, CampfireFastCook, CampfireCheapCook, CampfireInstaCook, Tent, TentStorage, TentSleeping, Radish, RadishFood, CampingBagSmall, CampingBagNormal, CampingBagLarge, CamperTrade, Guides} );

		LanguageRegistry.instance().addStringLocalization("achievement.CampersTool", "en_US", "First things first!");
		LanguageRegistry.instance().addStringLocalization("achievement.Campfire", "en_US", "Campfires!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireMultiCook", "en_US", "Cooking efficient!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireFastCook", "en_US", "Power Cooker!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireCheapCook", "en_US", "We don't need coal!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireInstaCook", "en_US", "Campfire legend!");
		LanguageRegistry.instance().addStringLocalization("achievement.MarshMallowFood", "en_US", "Campfire tales!");
		LanguageRegistry.instance().addStringLocalization("achievement.TentParts", "en_US", "The begining of tents!");
		LanguageRegistry.instance().addStringLocalization("achievement.Tent", "en_US", "Tents are awesome!");
		LanguageRegistry.instance().addStringLocalization("achievement.TentStorage", "en_US", "The power of storage!");
		LanguageRegistry.instance().addStringLocalization("achievement.TentSleeping", "en_US", "Sleepover!");
		LanguageRegistry.instance().addStringLocalization("achievement.Radish", "en_US", "Fast-Food");
		LanguageRegistry.instance().addStringLocalization("achievement.RadishFood", "en_US", "Bitter-Sweet!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampingBagSmall", "en_US", "Portalble storage!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampingBagNormal", "en_US", "Store-More!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampingBagLarge", "en_US", "Hiking trip!");
		LanguageRegistry.instance().addStringLocalization("achievement.CamperTrade", "en_US", "Trading!");
		LanguageRegistry.instance().addStringLocalization("achievement.Guides", "en_US", "Learning...!");

		LanguageRegistry.instance().addStringLocalization("achievement.CampersTool.desc", "en_US", "Make the camper's tool!");
		LanguageRegistry.instance().addStringLocalization("achievement.Campfire.desc", "en_US", "Make a campfire!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireMultiCook.desc", "en_US", "Make the multi cooking campfire!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireFastCook.desc", "en_US", "Make the fast cooking campfire!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireCheapCook.desc", "en_US", "Make the cheap cooking campfire!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampfireInstaCook.desc", "en_US", "Make the insta cooking campfire!");
		LanguageRegistry.instance().addStringLocalization("achievement.MarshMallowFood.desc", "en_US", "Cook a marshmallow!");
		LanguageRegistry.instance().addStringLocalization("achievement.TentParts.desc", "en_US", "Make some tent parts!");
		LanguageRegistry.instance().addStringLocalization("achievement.Tent.desc", "en_US", "Make a tent!");
		LanguageRegistry.instance().addStringLocalization("achievement.TentStorage.desc", "en_US", "Make a storage tent!");
		LanguageRegistry.instance().addStringLocalization("achievement.TentSleeping.desc", "en_US", "Make a sleeping tent!");
		LanguageRegistry.instance().addStringLocalization("achievement.Radish.desc", "en_US", "Plant some radish!");
		LanguageRegistry.instance().addStringLocalization("achievement.RadishFood.desc", "en_US", "Eat some radish!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampingBagSmall.desc", "en_US", "Make a small camping bag!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampingBagNormal.desc", "en_US", "Make a normal camping bag!");
		LanguageRegistry.instance().addStringLocalization("achievement.CampingBagLarge.desc", "en_US", "Make a large camping bag!");
		LanguageRegistry.instance().addStringLocalization("achievement.CamperTrade.desc", "en_US", "Take a look at the camper trading!");
		LanguageRegistry.instance().addStringLocalization("achievement.Guides.desc", "en_US", "Read a guide book;");
	}
}
