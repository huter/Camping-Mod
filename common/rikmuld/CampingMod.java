package rikmuld;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
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
	}
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		proxy.registerRenderers();
		
		NetworkRegistry.instance().registerGuiHandler(this, CampingMod.proxy);	
		LanguageRegistry.instance().addStringLocalization("itemGroup.customTab", "en_US", "Camping Stuff");
		
		ModBlocks.init();
		ModItems.init();
		ModTileEntitys.init();
		ModRecipies.init();
		ModAchievements.init();
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
	 * TODO: make the cloaks working.
	 * TODO: make directions with forge directions so the tent class can be very small
	 * TODO: complete the guide books.
	 * TODO: make the camping tool v2 that you can craft and open gui with a key.
	 * TODO: make backpacks wereable on the back and activateble with a key.
	 * TODO: make a camper AI for not run into campfires and stay with tents.
	 * TODO: make a flashlight
	 * TODO: larger storage in tents
	 * TODO: make tents with traped chests and ender chests
	 * TODO: place the foods in one metadata, place the tentContents in the tentParts metadata
	 * 
	 * TODO: fix the bugs
	 * 
	 * -camper skin change reload world (nbt)
	 * -camping bags not keeping inv when upgraded
	 * -when you get an itemstack it should spawn in the same slot were you made it.
	 * 
	 */
}
