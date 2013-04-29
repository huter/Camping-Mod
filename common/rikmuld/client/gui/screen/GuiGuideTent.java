package rikmuld.client.gui.screen;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import rikmuld.client.gui.button.GuiButtonGuideButton;
import rikmuld.core.helper.IntegerHelper;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGuideTent extends GuiGuide{

    private RenderItem itemRender;
    int update = 0;
    int toolNum = 0;
    int woolNum = 0;
    int woolNum1 = 0;
    int woolNum2 = 0;
    
	public GuiGuideTent()
	{
		this.BookPagesTotaal = 4;
        this.itemRender = new RenderItem();
	}
	
	@Override
	public void addTextByPage(int page)
	{
		 int var1 = MathHelper.floor_float((this.width - this.bookImageWidth) / 2.4F);
		 int var2 = MathHelper.floor_float((this.height - this.bookImageHeight) / 2.4F);
		 
		 int var3 = MathHelper.floor_float((this.width - this.bookImageWidth) / 1.2F);
		 int var4 = MathHelper.floor_float((this.height - this.bookImageHeight) / 1.2F);
	
		 GL11.glPushMatrix();
		 GL11.glScalef(1.20F, 1.20F, 1.20F);
		 if(page==1)
		 {		
			 	fontRenderer.drawString("Tent Pegs", var1+38, var2+11, 0x2b2b2b, false);		 
		 }	
		 if(page==2)
		 {
			 	fontRenderer.drawString("Canvas", var1+48, var2+11, 0x2b2b2b, false);
		 }	
		 if(page==3)
		 {
			 	fontRenderer.drawString("Sleeping Bag", var1+30, var2+11, 0x2b2b2b, false);
		 }
		 if(page==4)
		 {
			 	fontRenderer.drawString("The Tent", var1+40, var2+11, 0x2b2b2b, false);
		 }
		 GL11.glPopMatrix();
		 
		 
		 GL11.glPushMatrix();
		 GL11.glScalef(0.6F, 0.6F, 0.6F);
		 if(page==1)
		 {
			 fontRenderer.drawString("If you ever want to make a tent you first", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("need tent pegs, tent pegs are very easy to", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("make you only need a camper's tool and a", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("little bit of iron. Furthermore every time", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("you craft tent pegs you will get 4 of them", var3+32, var4+105, 0x2b2b2b, false);
			 fontRenderer.drawString("and the camper's tool only takes 1 damage,", var3+32, var4+115, 0x2b2b2b, false);
			 fontRenderer.drawString("so you can make a total of", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("400 tent pegs with only 1", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("camper's tool, but you still", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("need 100 iron to do that.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+170, 0x2b2b2b, false);
			 fontRenderer.drawString("The Recipie is shapless.", var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("The tool will take 1 damage.", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("1 tent needs 2 tent pegs.",var3+32, var4+215, 0x2b2b2b, false);
			 fontRenderer.drawString("You will get 4 tent pegs.", var3+32, var4+205, 0x2b2b2b, false);
		 }
		 if(page==2)
		 {
			 fontRenderer.drawString("If you ever want to make a tent you also", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("need canvas, canvas is a little harder to", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("make than the tent pegs, you need a camper's", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("tool and 6 string. However every time you", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("craft canvas you will get 10 of them", var3+32, var4+105, 0x2b2b2b, false);
			 fontRenderer.drawString("and the camper's tool only takes 1 damage,", var3+32, var4+115, 0x2b2b2b, false);
			 fontRenderer.drawString("so you can make a total of", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("1000 canvas with only 1", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("camper's tool, but you still", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("need 600 string to do that.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+170, 0x2b2b2b, false);
			 fontRenderer.drawString("The Recipie is shapless.", var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("The tool will take 1 damage.", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("1 tent needs 5 canvas.",var3+32, var4+205, 0x2b2b2b, false);
			 fontRenderer.drawString("You will get 10 canvas.", var3+32, var4+215, 0x2b2b2b, false);
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
			this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+70, ((this.height - this.bookImageHeight) / 2)+28, 180, 194, 40, 12);
		}
		else
		{
			int a = 0;
			ItemStack tool = new ItemStack(ToolHelper.tools.get(toolNum), 1, 0);
			
			ItemStack wool = new ItemStack(Block.cloth, 1, IntegerHelper.getLimitedNumber(woolNum, 0, 15));
			ItemStack wool1 = new ItemStack(Block.cloth, 1, IntegerHelper.getLimitedNumber(woolNum1, 0, 15));
			ItemStack wool2 = new ItemStack(Block.cloth, 1, IntegerHelper.getLimitedNumber(woolNum2, 0, 15));
			
			this.mc.renderEngine.bindTexture(Textures.GUI_LOCATIONS + Textures.GUI_COMPONENTS);
			
			for(int i=1; i<5;i++)
			{  	
               if(page==i)
               {      			
					this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+108, ((this.height - this.bookImageHeight) / 2)+85, a+i, 76, 50, 50);
					this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+131, ((this.height - this.bookImageHeight) / 2)+8, a+i, 126, 25, 25);					 			
               }
               a+=50;
			}
			
			if(page==1||page==2)
	        {
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        		GL11.glEnable(GL11.GL_LIGHTING);
					this.itemRender.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, tool, ((this.width - this.bookImageWidth) / 2)+107, ((this.height - this.bookImageHeight) / 2)+85);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDepthMask(true);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
	        }
			if(page==3)
	        {
					GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        		RenderHelper.enableGUIStandardItemLighting();
					this.itemRender.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, wool, ((this.width - this.bookImageWidth) / 2)+107, ((this.height - this.bookImageHeight) / 2)+102);
					this.itemRender.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, wool1, ((this.width - this.bookImageWidth) / 2)+124, ((this.height - this.bookImageHeight) / 2)+102);
					this.itemRender.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, wool2, ((this.width - this.bookImageWidth) / 2)+141, ((this.height - this.bookImageHeight) / 2)+102);
					GL11.glDepthMask(true);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
//					RenderHelper.enableStandardItemLighting();
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
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(2, var1+35, var2+75, 10));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(3, var1+75, var2+55, 12));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(4, var1+115, var2+75, 14));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(5, var1+75, var2+95, 16));
		}
	}
	
	@Override 
	public void triggerButtons(GuiButton button)
	{
		switch(button.id)
		{
			case 2:	this.BookPages = 1;	break;
			case 3:	this.BookPages = 2;	break;
			case 4:	this.BookPages = 3;	break;
			case 5:	this.BookPages = 4;	break;
		}
	}
	
	@Override
	public void updateTick()
	{
		update++;
		if(update>50)
		{
			update=0;
			toolNum++;
			woolNum = (int)(Math.random()*100+1);
			woolNum1 = (int)(Math.random()*100+1);
			woolNum2 = (int)(Math.random()*100+1);
			if(toolNum>1)toolNum=0;
			addImgByPage(this.BookPages); 
		}
	}
}
