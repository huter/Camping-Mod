package rikmuld.client.gui.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import rikmuld.core.lib.Textures;
import rikmuld.inventory.container.ContainerCampingBag;
import rikmuld.inventory.inventory.InventoryCampingBag;

public class GuiCampingBag extends GuiContainer {
   	
    protected int ySize = 166;
    
    InventoryCampingBag inventory;
	ItemStack backpack;
	
	public GuiCampingBag(InventoryPlayer par1InventoryPlayer,	InventoryCampingBag iInventory, ItemStack back) 
	{
		super(new ContainerCampingBag(par1InventoryPlayer, iInventory, back));
		inventory = iInventory;
		backpack = back;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Camping Bag", 58, 5, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(Textures.GUI_LOCATIONS + Textures.GUI_SINGLE_CAMPINGBAG);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		addImg();
	}
	
	private void addImg() 
	{
		switch (backpack.getItemDamage())
		{
			case 0: drawTexturedModalRect(((this.width - this.xSize) / 2)+61, ((this.height - this.ySize) / 2)+17, 0, 167, 54, 54); break;
			case 1: drawTexturedModalRect(((this.width - this.xSize) / 2)+34, ((this.height - this.ySize) / 2)+17, 0, 167, 108, 54); break;
			case 2: drawTexturedModalRect(((this.width - this.xSize) / 2)+7, ((this.height - this.ySize) / 2)+17, 0, 167, 162, 54); break;
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