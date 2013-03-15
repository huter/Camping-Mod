package rikmuld.core.register;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import rikmuld.block.itemblock.Campfire;
import rikmuld.block.itemblock.Tent;
import rikmuld.block.normal.GhostBlock;
import rikmuld.block.plant.RadishCrop;
import rikmuld.core.lib.Blocks;
import rikmuld.item.itemblock.ItemCampfire;
import rikmuld.item.itemblock.ItemTent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	public static Campfire campfire;
	public static Block tent;
	public static Block ghostBlock;
	public static Block RadishCrop;
	
	public static void init() 
	{
		campfire = new Campfire(Blocks.BLOCK_CAMP_ID);
		tent = new Tent(Blocks.BLOCK_TENT_ID);
		ghostBlock = new GhostBlock(Blocks.BLOCK_GHOST_ID);
		RadishCrop = new RadishCrop(Blocks.BLOCK_RADISH_ID);
	
		GameRegistry.registerBlock(campfire, ItemCampfire.class, Blocks.BLOCK_META_CAMP_NAME);
		GameRegistry.registerBlock(tent, ItemTent.class, Blocks.BLOCK_TENT_NAME);
		GameRegistry.registerBlock(ghostBlock, Blocks.BLOCK_GHOST_NAME);
		GameRegistry.registerBlock(RadishCrop, Blocks.BLOCK_RADISH_NAME);
		
		
		LanguageRegistry.addName(tent, Blocks.BLOCK_TENT_GAME_NAME);
		
		LanguageRegistry.addName(new ItemStack(campfire, 1, 0), Blocks.BLOCK_CAMP_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(campfire, 1, 1), Blocks.BLOCK_CAMP_MULTI_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(campfire, 1 ,2), Blocks.BLOCK_CAMP_FAST_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(campfire, 1, 3), Blocks.BLOCK_CAMP_CHEAP_GAME_NAME);
		LanguageRegistry.addName(new ItemStack(campfire, 1, 4), Blocks.BLOCK_CAMP_INSTA_GAME_NAME);
	}
}
