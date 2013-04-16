package rikmuld.client.gui.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import rikmuld.core.lib.Textures;
import rikmuld.inventory.container.ContainerCampingBag;
import rikmuld.inventory.inventory.InventoryCampingBag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCampingBag extends GuiContainer {
   	
	InventoryCampingBag inventory;
	
	public GuiCampingBag(InventoryPlayer par1InventoryPlayer,	InventoryCampingBag iInventory) 
	{
		super(new ContainerCampingBag(par1InventoryPlayer, iInventory));
		inventory = iInventory;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Camping Bag", 10, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(Textures.GUI_LOCATIONS + Textures.GUI_BAG_SMALL);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);	
	}
	
	protected void keyTyped(char par1, int par2) 
	{
		if (par2 == 18 || par2 == 1) 
		{
			super.mc.setIngameFocus();
		}
	}
}