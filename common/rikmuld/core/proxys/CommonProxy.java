package rikmuld.core.proxys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rikmuld.client.gui.container.GuiCampToolV2;
import rikmuld.client.gui.container.GuiCampfireCheapCooker;
import rikmuld.client.gui.container.GuiCampfireFastCooker;
import rikmuld.client.gui.container.GuiCampfireMultiCooker;
import rikmuld.client.gui.container.GuiCamping;
import rikmuld.client.gui.container.GuiCampingBag;
import rikmuld.client.gui.container.GuiTent;
import rikmuld.client.gui.screen.GuiGuide;
import rikmuld.core.handlers.PlayerHandler;
import rikmuld.core.lib.GuiIds;
import rikmuld.inventory.container.ContainerCampToolV2;
import rikmuld.inventory.container.ContainerCampfireCheapCooker;
import rikmuld.inventory.container.ContainerCampfireFastCooker;
import rikmuld.inventory.container.ContainerCampfireMultiCooker;
import rikmuld.inventory.container.ContainerCamping;
import rikmuld.inventory.container.ContainerCampingBag;
import rikmuld.inventory.container.ContainerTent;
import rikmuld.inventory.inventory.InventoryCamping;
import rikmuld.inventory.inventory.InventoryCampingBag;
import rikmuld.tileentity.TileEntityCampfireCheapCooker;
import rikmuld.tileentity.TileEntityCampfireFastCooker;
import rikmuld.tileentity.TileEntityCampfireMultiCooker;
import rikmuld.tileentity.TileEntityTent;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	public void initRenderingAndTextures() {} 
	public void registerRenderers() {}
	public void registerTileEntitySpecialRenderer(TileEntity tileentity){}
    public void registerKeyBindingHandler() {}
    public void setKeyBinding(String name, int value) {}   
    public void registerTickHandler() {}
	public void registerEntityRenderHandler() {}
	public void registerPlayerHandlers() {}
  
    public static GuiGuide guide;
    
    InventoryCampingBag CampingBagInv = null;
    public static InventoryCamping CampingInv = null;
    public static PlayerHandler playerHandler = new PlayerHandler();
    
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(ID == GuiIds.GuiCampfireMultiCooker)
		{
				return new ContainerCampfireMultiCooker(player.inventory, (TileEntityCampfireMultiCooker) tileEntity);
		}
		if(ID == GuiIds.GuiCampfireFastCooker)
		{
				return new ContainerCampfireFastCooker(player.inventory, (TileEntityCampfireFastCooker) tileEntity);
		}
		if(ID == GuiIds.GuiCampfireCheapCooker)
		{
				return new ContainerCampfireCheapCooker(player.inventory, (TileEntityCampfireCheapCooker) tileEntity);
		}
		if(ID == GuiIds.GuiTent)
		{
				return new ContainerTent(player.inventory, (TileEntityTent) tileEntity);
		}
		if (ID == GuiIds.GUICamping) 
		{
			CampingInv = new InventoryCamping(player);
			return new ContainerCamping(player.inventory, CampingInv);
		}
		if (ID == GuiIds.GUICampingBag) 
		{
			ItemStack backpack = player.getCurrentEquippedItem();
			CampingBagInv = new InventoryCampingBag(player, backpack);
            return new ContainerCampingBag(player.inventory, CampingBagInv, backpack);
		}
		if (ID == GuiIds.GUICampTool) 
		{
            return new ContainerCampToolV2(player.inventory, world, x, y, z);
     	}
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(ID == GuiIds.GuiCampfireMultiCooker)
		{
				return new GuiCampfireMultiCooker(player.inventory, (TileEntityCampfireMultiCooker) tileEntity);
		}
		if(ID == GuiIds.GuiCampfireFastCooker)
		{
				return new GuiCampfireFastCooker(player.inventory, (TileEntityCampfireFastCooker) tileEntity);
		}
		if(ID == GuiIds.GuiCampfireCheapCooker)
		{
				return new GuiCampfireCheapCooker(player.inventory, (TileEntityCampfireCheapCooker) tileEntity);
		}
		if(ID == GuiIds.GuiTent)
		{
				return new GuiTent(player.inventory, (TileEntityTent) tileEntity);
		}
		if (ID == GuiIds.GUICamping) 
		{
            return new GuiCamping(player.inventory, new InventoryCamping(player));
	    }
		if (ID == GuiIds.GUICampingBag) 
		{
			ItemStack backpack = player.getCurrentEquippedItem();
			CampingBagInv = new InventoryCampingBag(player, backpack);
            return new GuiCampingBag(player.inventory, CampingBagInv, backpack);
	    }
		if (ID == GuiIds.GUIGuide) 
		{
            guide = new GuiGuide();
            return guide;
	    }
		if (ID == GuiIds.GUICampTool) 
		{
            return new GuiCampToolV2(player, world, x, y, z);
     	}
		return null;	
	}
}
