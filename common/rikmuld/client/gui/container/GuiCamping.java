package rikmuld.client.gui.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import rikmuld.core.lib.Textures;
import rikmuld.inventory.container.ContainerCamping;
import rikmuld.inventory.inventory.InventoryCamping;

public class GuiCamping extends GuiContainer {
   	
    protected int ySize = 179;
	InventoryCamping inventory;
	
	public GuiCamping(InventoryPlayer par1InventoryPlayer,	InventoryCamping iInventory) 
	{
		super(new ContainerCamping(par1InventoryPlayer, iInventory));
		inventory = iInventory;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Camping Inventory", 42, 4, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(Textures.GUI_LOCATIONS + Textures.GUI_CAMPINGBAG);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		addImg();
	}
	
	private void addImg() 
	{
		drawTexturedModalRect(((this.width - this.xSize)/2)+16, ((this.height - this.ySize)/2)+7, 7, 96, 18, 18);
		drawTexturedModalRect(((this.width - this.xSize)/2)+141, ((this.height - this.ySize)/2)+7, 7, 96, 18, 18);
		drawTexturedModalRect(((this.width - this.xSize) / 2)+178, ((this.height - this.ySize) / 2), 176, 0, 73, 133); 
		drawTexturedModalRect(((this.width - this.xSize) / 2)-75, ((this.height - this.ySize) / 2), 176, 0, 73, 133); 
		
		if(inventory.getStackInSlot(0)!=null)
		{
			switch (inventory.getStackInSlot(0).getItemDamage())
			{
				case 0: drawTexturedModalRect(((this.width - this.xSize) / 2)+7, ((this.height - this.ySize) / 2)+30, 7, 96, 162, 18); break;
				case 1: drawTexturedModalRect(((this.width - this.xSize) / 2)+7, ((this.height - this.ySize) / 2)+30, 7, 96, 162, 36); break;
				case 2: drawTexturedModalRect(((this.width - this.xSize) / 2)+7, ((this.height - this.ySize) / 2)+30, 7, 96, 162, 54); break;
			}
		}
		if (inventory.getStackInSlot(1)!=null)
		{
			drawTexturedModalRect(((this.width - this.xSize) / 2)+188, ((this.height - this.ySize) / 2)+9, 7, 96, 54, 54); 
			drawTexturedModalRect(((this.width - this.xSize) / 2)-65, ((this.height - this.ySize) / 2)+9, 7, 96, 54, 54); 
		}
	}

	protected void keyTyped(char par1, int par2) 
	{
		if (par2 == 18 || par2 == 1) 
		{
			super.mc.setIngameFocus();
		}
	}
}