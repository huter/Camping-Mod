package rikmuld.core.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rikmuld.core.register.ModBlocks;
import rikmuld.core.register.ModItems;

public class ItemStacks {

	public static ItemStack toolCampDamage = new ItemStack(ModItems.TentTools, 1, -1);
	public static ItemStack toolCampNoDamage = new ItemStack(ModItems.TentTools);
    public static ItemStack iron = new ItemStack(Item.ingotIron);
    public static ItemStack rosered = new ItemStack(Item.dyePowder, 1, 1);
    public static ItemStack torch = new ItemStack(Block.torchWood);
    public static ItemStack coal = new ItemStack(Item.coal);
	public static ItemStack flint = new ItemStack(Item.flint);
	public static ItemStack stone = new ItemStack(Block.stone);
	public static ItemStack enderp = new ItemStack(Item.enderPearl);
	public static ItemStack furnace = new ItemStack(Block.furnaceIdle);
	public static ItemStack flintst = new ItemStack(Item.flintAndSteel);
	public static ItemStack campfire = new ItemStack(ModBlocks.campfire, 1, 0);
	public static ItemStack campfireMulti = new ItemStack(ModBlocks.campfire, 1, 1);
	public static ItemStack campfireFast = new ItemStack(ModBlocks.campfire, 1, 2);
	public static ItemStack campfireCheap = new ItemStack(ModBlocks.campfire, 1, 3);
	public static ItemStack campfireInsta = new ItemStack(ModBlocks.campfire, 1, 4);
	public static ItemStack waterbottle = new ItemStack(Item.potion);
	public static ItemStack egg = new ItemStack(Item.egg);
	public static ItemStack sugar = new ItemStack(Item.sugar);
	public static ItemStack bowl = new ItemStack(Item.bowlEmpty);
	public static ItemStack marsh = new ItemStack(ModItems.Marshmallow, 4, 0);
	public static ItemStack marsh1 = new ItemStack(ModItems.Marshmallow, 3, 1);
	public static ItemStack stick = new ItemStack(Item.stick, 1);
	public static ItemStack bed = new ItemStack(Item.bed, 1, 0);
	public static ItemStack tent1 = new ItemStack(ModBlocks.tent, 1, 0);
	public static ItemStack tentPegs_4 = new ItemStack(ModItems.TentParts, 4, 0);
	public static ItemStack tentPegs = new ItemStack(ModItems.TentParts, 0, 0);
	public static ItemStack canvas_10 = new ItemStack(ModItems.TentParts, 10, 1);
	public static ItemStack canvas = new ItemStack(ModItems.TentParts, 1, 1);
	public static ItemStack wool = new ItemStack(Block.cloth, 1, 0);
	public static ItemStack string = new ItemStack(Item.silk);
	public static ItemStack chest = new ItemStack(Block.chest);
	public static ItemStack SleepBag = new ItemStack(ModItems.SleepingBag, 1, 0);
	public static ItemStack campingBagSmall = new ItemStack(ModItems.CampingBag, 1, 0);
	public static ItemStack campingBagNormal = new ItemStack(ModItems.CampingBag, 1, 1);
	public static ItemStack campingBagLarge = new ItemStack(ModItems.CampingBag, 1, 2);
}
