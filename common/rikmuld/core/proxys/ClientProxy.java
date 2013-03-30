package rikmuld.core.proxys;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import rikmuld.client.renderer.tileentity.TileEntityCampfireRenderer;
import rikmuld.client.renderer.tileentity.TileEntityTentRenderer;
import rikmuld.core.handlers.KeyHandler;
import rikmuld.core.handlers.TickHandler;
import rikmuld.core.helper.CloakHelper;
import rikmuld.core.helper.KeyHelper;
import rikmuld.core.lib.Textures;
import rikmuld.tileentity.TileEntityCampfire;
import rikmuld.tileentity.TileEntityTent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
		
	public void initRenderingAndTextures() {}
	
	public void registerRenderers () 
	{
		MinecraftForgeClient.preloadTexture(Textures.SPRITE_LOCATION + Textures.SPRITE_PARTICLES);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTent.class, new TileEntityTentRenderer());
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
    public void registerCloaks()
    {
    	MinecraftForge.EVENT_BUS.register(new CloakHelper());
    }
}