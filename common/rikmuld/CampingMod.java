package rikmuld;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.AchievementPage;
import rikmuld.core.handlers.CraftHandler;
import rikmuld.core.lib.ModInfo;
import rikmuld.core.proxys.CommonProxy;
import rikmuld.core.register.ModAchievements;
import rikmuld.core.register.ModBlocks;
import rikmuld.core.register.ModConfig;
import rikmuld.core.register.ModEvents;
import rikmuld.core.register.ModItems;
import rikmuld.core.register.ModLogger;
import rikmuld.core.register.ModMobs;
import rikmuld.core.register.ModRecipies;
import rikmuld.core.register.ModTileEntitys;
import rikmuld.core.tab.CampingTab;
import rikmuld.network.PacketHandler;
import rikmuld.world.WorldGen;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES)
@NetworkMod(channels = { ModInfo.PACKET_CHANEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)

public class CampingMod {

	public static CreativeTabs customTab = new CampingTab("customTab");
	
	@Instance(ModInfo.MOD_ID)
	public static CampingMod instance;
	
	@SidedProxy(clientSide = ModInfo.MOD_CLIENT_PROXY, serverSide = ModInfo.MOD_SERVER_PROXY)
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		ModLogger.preinit();
		ModConfig.preInit(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Camping/" + ModInfo.MOD_ID + ".cfg"));
		proxy.registerKeyBindingHandler();
		proxy.registerTickHandler();
		
		ModBlocks.init();
		ModItems.init();
		
	    AchievementPage.registerAchievementPage(new ModAchievements());
	}
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		proxy.registerRenderers();
		
		NetworkRegistry.instance().registerGuiHandler(this, CampingMod.proxy);	
		LanguageRegistry.instance().addStringLocalization("itemGroup.customTab", "en_US", "Camping Stuff");
	
		ModTileEntitys.init();
		ModRecipies.init();
		ModMobs.init();
		ModEvents.init();
	    
		GameRegistry.registerCraftingHandler(new CraftHandler());
		GameRegistry.registerWorldGenerator(new WorldGen());	
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		 ModLogger.log(Level.INFO, ModInfo.MOD_NAME + " has loaded successfylly.");
	}
	
	/* 
	 * TODO: finalise the camping inventory. fix the crafting drop click bug, make items drop on close wehn in crafting inv, make backpack working for backpack on backpack switsh.
	 * TODO: finish the guide books.
	 * TODO: make 2 new config options 1 for peasfull mode recipies, 1 for set home to tent.
	 * 
	 * 
	 * TODO: update the site!!! (also the new planed feutures)
	 * TODO: make special armor for campers.
	 * TODO: make the cloaks working.
	 * TODO: complete the guide books.
	 * TODO: make a camper AI for not run into campfires and stay with tents.
	 * TODO: make a flashlight
	 * TODO: make tents with traped chests and ender chests and make more for inside tents
	 * TODO: make an seperated backpack slot for the backpack and make the backpack an model for when you were them.
	 * TODO: fix the bugs
	 * TODO: add camping animals 
	 * 
	 * -camper skin change reload world (nbt thingy)
	 * 
	 * TODO: for next update (backpack Update) 1.0.1 --> 1.0.2
	 * 
	 * -finish the tent and the equipment guide book.
	 * -make an seperated backpack slot for the backpack and make the backpack an model for when you were them.
	 * -fix the known bugs.
	 * 
	 * TODO: for next update (equipment update) 1.0.2 --> 1.0.3
	 * 
	 * -finish the food guide book.
	 * -make a special armor set (ex. the camping boots (get fast speed ed.))
	 * 
	 * TODO for next update (tent update) 1.0.3 --> 1.0.4
	 * 
	 * -finifh the word guide book.
	 * -make the tent also working with trapped chests and ender chests
	 * -make the tent rolatable in 4 directions 
	 * -make the ghost blocks also half blocks so you are not standing very hight above the tent
	 * -fix the position were you sleep in the tents
	 * 
	 * TODO for next update (Campsite update) 1.0.4 --> 1.0.5
	 * 
	 * finish all the guide books and update them.
	 * make a log were you can sit on
	 * make a lantarn with gives light when equiped
	 * make a supply box were you can stack upstackeble items.
	 * 
	 */
}
