package rikmuld.client.gui.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rikmuld.core.lib.Textures;
import rikmuld.inventory.container.ContainerCampToolV2;

public class GuiCampToolV2 extends GuiContainer {

    public GuiCampToolV2(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerCampToolV2(player.inventory, world, x, y, z));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) 
    {
        fontRenderer.drawString(StatCollector.translateToLocal("Workbench: Camper's Tool"), 28, 6, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) 
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_LOCATIONS + Textures.GUI_TOOL_CAMP);
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed() 
    {
        super.onGuiClosed();
    }
}