package rikmuld.client.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGuideTent extends GuiGuide{

    private RenderItem itemRender;
    int update = 0;
    int toolNum = 0;
    
	public GuiGuideTent()
	{
		this.BookPagesTotaal = 4;
        this.itemRender = new RenderItem();
	}
	
	@Override
	public void addTextByPage(int page)
	{
		 int var1 = MathHelper.floor_float((this.width - this.bookImageWidth) / 2.2F);
		 int var2 = MathHelper.floor_float((this.height - this.bookImageHeight) / 2.2F);
		 
		 int var3 = MathHelper.floor_float((this.width - this.bookImageWidth) / 1.2F);
		 int var4 = MathHelper.floor_float((this.height - this.bookImageHeight) / 1.2F);
	
		 GL11.glPushMatrix();
		 GL11.glScalef(1.10F, 1.10F, 1.10F);
		 if(page==1)
		 {		
			 
		 }	
		 if(page==2)
		 {
			 
		 }	
		 if(page==3)
		 {
		
		 }
		 if(page==4)
		 {
			
		 }
		 GL11.glPopMatrix();
		 
		 GL11.glPushMatrix();
		 GL11.glScalef(0.6F, 0.6F, 0.6F);
		 if(page==1)
		 {
		
		 }
		 if(page==2)
		 {
			
		 }
		 if(page==3)
		 {
		
		 }
		 if(page==4)
		 {
		
		 }
		 GL11.glPopMatrix();
	}
    
	@Override
	public void addImgByPage(int page) 
	{
		if(page==0)
		{

		}
		else
		{
			 int a = 0;
			 for(int i=1; i<5;i++)
			 {	 
				if(page==i)
				{
					ItemStack tool = new ItemStack(ToolHelper.tools.get(toolNum), 1, 0);
					GL11.glDisable(GL11.GL_LIGHTING);
		            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		            GL11.glEnable(GL11.GL_LIGHTING);
					this.itemRender.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, tool, ((this.width - this.bookImageWidth) / 2)+50, ((this.height - this.bookImageHeight) / 2)+75);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glDepthMask(true);
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
				
					this.mc.renderEngine.bindTexture(Textures.GUI_LOCATIONS + Textures.GUI_COMPONENTS);
					
				}
				a+=50;
			}
		}
	}
	
	@Override
	public void addButtonByPage(int page)
	{
		int var1 = (this.width - this.bookImageWidth) / 2;
		int var2 = (this.height - this.bookImageHeight) / 2;
		
		if(page == 0)
		{
	     
		}
	}
	
	@Override 
	public void triggerButtons(GuiButton button)
	{
		switch(button.id)
		{

		}
	}
	
	@Override
	public void updateTick()
	{
		update++;
		if(update>100)
		{
			update=0;
			toolNum++;
			if(toolNum>1)toolNum=0;
			addImgByPage(this.BookPages); 
		}
	}
}
