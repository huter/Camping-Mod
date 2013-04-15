package rikmuld.core.proxys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rikmuld.client.gui.GuiCampToolV2;
import rikmuld.client.gui.GuiCampfireCheapCooker;
import rikmuld.client.gui.GuiCampfireFastCooker;
import rikmuld.client.gui.GuiCampfireMultiCooker;
import rikmuld.client.gui.GuiCampingBagLarge;
import rikmuld.client.gui.GuiCampingBagNormal;
import rikmuld.client.gui.GuiCampingBagSmall;
import rikmuld.client.gui.GuiTent;
import rikmuld.client.gui.screen.GuiGuideCampfire;
import rikmuld.client.gui.screen.GuiGuideEquipment;
import rikmuld.client.gui.screen.GuiGuideFood;
import rikmuld.client.gui.screen.GuiGuideTent;
import rikmuld.client.gui.screen.GuiGuideWorld;
import rikmuld.core.lib.GuiIds;
import rikmuld.inventory.container.ContainerCampToolV2;
import rikmuld.inventory.container.ContainerCampfireCheapCooker;
import rikmuld.inventory.container.ContainerCampfireFastCooker;
import rikmuld.inventory.container.ContainerCampfireMultiCooker;
import rikmuld.inventory.container.ContainerCampingBag;
import rikmuld.inventory.container.ContainerTent;
import rikmuld.inventory.inventory.InventoryCampingBag;
import rikmuld.item.armor.ArmorBackpack;
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
    public void registerCloaks() {}
  
    public static GuiGuideCampfire guideCamp;
    public static GuiGuideTent guideTent;
    public static GuiGuideEquipment guideEquipment;
    public static GuiGuideFood guideFood;
    public static GuiGuideWorld guideWorld;
 
    
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
		if (ID == GuiIds.GUICampingBagLarge||ID == GuiIds.GUICampingBagNormal||ID == GuiIds.GUICampingBagSmall) 
		{
		 	ItemStack backpack = player.inventory.getStackInSlot(38);
            return new ContainerCampingBag(player.inventory, (InventoryCampingBag) ArmorBackpack.getBackpackInv(player), backpack);
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
		
		if (ID == GuiIds.GUICampingBagLarge) 
		{
		 	ItemStack backpack = player.inventory.getStackInSlot(38);
            return new GuiCampingBagLarge(player.inventory, (InventoryCampingBag) ArmorBackpack.getBackpackInv(player), backpack);
	    }
		if (ID == GuiIds.GUICampingBagNormal) 
		{
		 	ItemStack backpack = player.inventory.getStackInSlot(38);
            return new GuiCampingBagNormal(player.inventory, (InventoryCampingBag) ArmorBackpack.getBackpackInv(player), backpack);
	    }
		if (ID == GuiIds.GUICampingBagSmall) 
		{
		 	ItemStack backpack = player.inventory.getStackInSlot(38);
            return new GuiCampingBagSmall(player.inventory, (InventoryCampingBag) ArmorBackpack.getBackpackInv(player), backpack);
	    }	
		if (ID == GuiIds.GUIGuideCampfire) 
		{
            guideCamp = new GuiGuideCampfire();
            return guideCamp;
	    }
		if (ID == GuiIds.GUIGuideTent) 
		{
            guideTent = new GuiGuideTent();
            return guideTent;
	    }
		if (ID == GuiIds.GUIGuideEquipment) 
		{
            guideEquipment = new GuiGuideEquipment();
            return guideEquipment;
	    }
		if (ID == GuiIds.GUIGuideFood) 
		{
            guideFood = new GuiGuideFood();
            return guideFood;
	    }
		if (ID == GuiIds.GUIGuideWorld) 
		{
            guideWorld = new GuiGuideWorld();
            return guideWorld;
	    }
		if (ID == GuiIds.GUICampTool) 
		{
            return new GuiCampToolV2(player, world, x, y, z);
     	}
		return null;	
	}
}
