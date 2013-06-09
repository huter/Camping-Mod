package rikmuld.core.proxys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import rikmuld.client.renderer.item.ItemCampingBagRenderer;
import rikmuld.client.renderer.tileentity.TileEntityCampfireRenderer;
import rikmuld.client.renderer.tileentity.TileEntityTentRenderer;
import rikmuld.core.handlers.KeyHandler;
import rikmuld.core.handlers.PlayerRenderingHandler;
import rikmuld.core.handlers.TickHandler;
import rikmuld.core.helper.KeyHelper;
import rikmuld.core.lib.Config;
import rikmuld.core.register.ModItems;
import rikmuld.tileentity.TileEntityCampfire;
import rikmuld.tileentity.TileEntityTent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	public static ItemCampingBagRenderer backpackRenderer;
		
	@Override
	public void initRenderingAndTextures() {}
	
	@Override
	public void registerRenderers () 
	{
		backpackRenderer = new ItemCampingBagRenderer();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTent.class, new TileEntityTentRenderer());
		
		MinecraftForgeClient.registerItemRenderer(ModItems.CampingBag.itemID, backpackRenderer);
	}
	
	@Override
    public void setKeyBinding(String name, int value) 
    {
        KeyHelper.addKeyBinding(name, value);
        KeyHelper.addIsRepeating(false);
    }
    
    @Override
    public void registerKeyBindingHandler() 
    {
        KeyBindingRegistry.registerKeyBinding(new KeyHandler());
    }
    
    @Override
    public void registerTickHandler()
    {
    	TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
    }
    
    @Override
	public void registerEntityRenderHandler()
	{
    	if(!Config.GENERAL_CAMPING_RENDERING_DISABLED)
    	{
    		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new PlayerRenderingHandler());
    	}
	}
    
    @Override
    public void registerPlayerHandlers()
    {
	    GameRegistry.registerPlayerTracker(playerHandler);
	    MinecraftForge.EVENT_BUS.register(playerHandler);
    }
}