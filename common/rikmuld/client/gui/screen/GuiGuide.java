package rikmuld.client.gui.screen;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import rikmuld.client.gui.button.GuiButtonGuideButton;
import rikmuld.client.gui.button.GuiButtonGuidePage;
import rikmuld.core.helper.IntegerHelper;
import rikmuld.core.helper.ToolHelper;
import rikmuld.core.lib.Textures;
import rikmuld.item.normal.GuideBook;

public class GuiGuide extends GuiScreen{
    
	private RenderItem itemRender;
    int update = 0;
    int toolNum = 0;
    int woolNum = 0;
    int woolNum1 = 0;
    int woolNum2 = 0;
	 
	public int bookImageWidth = 188;
    public int bookImageHeight = 192;
    public GuiButtonGuidePage buttonNextPage;
    public GuiButtonGuidePage buttonPreviousPage;
    public int BookPages;
    public int BookPagesTotaal = 10;
    public GuiButtonGuideButton buttonIcon;

	public void initGui()
	{
		buttonList.clear();
		int var1 = (this.width - this.bookImageWidth) / 2;
		int var2 = (this.height - this.bookImageHeight) / 2;
        this.buttonList.add(this.buttonNextPage = new GuiButtonGuidePage(0, var1 + 132, var2 + 140, true, BookPages));
        this.buttonList.add(this.buttonPreviousPage = new GuiButtonGuidePage(1, var1 + 26, var2 + 140, false, BookPages));
        this.itemRender = new RenderItem();
		addButtonByPage(BookPages);
	}
	
	public void addImgByPage(int page) 
	{
		if(page==0)
		{
			this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+50, ((this.height - this.bookImageHeight) / 2)+28, 49, 194, 82, 12);
		}
		else if(page<6)
		{
			 this.mc.renderEngine.bindTexture(Textures.GUI_LOCATION + Textures.GUI_COMPONENTS);
			 int a = 0;
			 
			 for(int i=1; i<6;i++)
			 {	 
				if(page==i)
				{
					this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+108, ((this.height - this.bookImageHeight) / 2)+85, a+i, 0, 50, 50);
					this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+131, ((this.height - this.bookImageHeight) / 2)+8, a+i, 51, 25, 25);					 
				} 
				a+=50;	
			 }
		}
		
		if(page==6)
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
			
			this.mc.renderEngine.bindTexture(Textures.GUI_LOCATION + Textures.GUI_COMPONENTS);
			
			for(int i=7; i<11;i++)
			{  	
               if(page==i)
               {      			
					this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+108, ((this.height - this.bookImageHeight) / 2)+85, a+i-6, 76, 50, 50);
					this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+131, ((this.height - this.bookImageHeight) / 2)+8, a+i-6, 126, 25, 25);					 			
               }
               a+=50;
			}
			
			if(page==7||page==8)
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
			if(page==9)
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
	

	public void addTextByPage(int page)
	{
		 int var1 = MathHelper.floor_float((this.width - this.bookImageWidth) / 2.2F);
		 int var2 = MathHelper.floor_float((this.height - this.bookImageHeight) / 2.2F);
		 
		 int var3 = MathHelper.floor_float((this.width - this.bookImageWidth) / 1.2F);
		 int var4 = MathHelper.floor_float((this.height - this.bookImageHeight) / 1.2F);
	
		 GL11.glPushMatrix();
		 GL11.glScalef(1.10F, 1.10F, 1.10F);	
		 if(page!=0&&page<6)
		 {
			 	fontRenderer.drawString("Campfire", var1+47, var2+21, 0x2b2b2b, false);
		 }
		 if(page==1)
		 {		
			 	fontRenderer.drawString("The Decoration", var1+28, var2+11, 0x2b2b2b, false);		 
		 }	
		 if(page==2)
		 {
			 	fontRenderer.drawString("The Multi-Cooking", var1+24, var2+11, 0x2b2b2b, false);
		 }	
		 if(page==3)
		 {
			 	fontRenderer.drawString("The Fast-Cooking", var1+24, var2+11, 0x2b2b2b, false);
		 }
		 if(page==4)
		 {
			 	fontRenderer.drawString("The Cheap-Cooking", var1+21, var2+11, 0x2b2b2b, false);
		 }
		 if(page==5)
		 {
			 	fontRenderer.drawString("The Insta-Cooking", var1+22, var2+11, 0x2b2b2b, false);
		 }
		 GL11.glPopMatrix();
		 
		 GL11.glPushMatrix();
		 GL11.glScalef(0.6F, 0.6F, 0.6F);
		 if(page==1)
		 {
			 fontRenderer.drawString("The Decoration campfire is the first", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("campfire you can make as a camper.", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("Every campfire has its one special ", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("abilities, however evry special campfire", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("has the abilities of this one.", var3+32, var4+105, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+120, 0x2b2b2b, false);
			 fontRenderer.drawString("Can change 2 sticks into 1 torch.", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("Has a normal flame particle.", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("It drops 3 torches when", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("broken, but you can use a", var3+32, var4+165, 0x2b2b2b, false);
			 fontRenderer.drawString("camper's tool to retrieve it.",var3+32, var4+175, 0x2b2b2b, false);
			 fontRenderer.drawString("It will burn entities.", var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("It lights the environment.", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("Can be colored with every", var3+32, var4+205, 0x2b2b2b, false);
			 fontRenderer.drawString("minecraft color.", var3+32, var4+215, 0x2b2b2b, false);
		 }
		 if(page==2)
		 {
			 fontRenderer.drawString("The Multi-Cooking campfire is the best", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("campfire to use if you want to cook", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("a lot of food, because you can cook 6", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("things at the same time.", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+110, 0x2b2b2b, false);
			 fontRenderer.drawString("It can cook 6 things at the same time.", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("it uses 1 coal for 3 cooking cycles.", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("It has a GUI", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("Has a green flame particle.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("It cooks 2.5 times slower", var3+32, var4+165, 0x2b2b2b, false);
			 fontRenderer.drawString("than a regular furnace.", var3+32, var4+175, 0x2b2b2b, false);
		 }
		 if(page==3)
		 {
			 fontRenderer.drawString("The Fast-Cooking campfire is the best", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("campfire to use if you want to cook", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("very fast, because you can cook 5", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("times faster then normal.", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+110, 0x2b2b2b, false);
			 fontRenderer.drawString("It can cook 1 thing at the same time.", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("it uses 1 coal for 5 cooking cycles.", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("It has a GUI", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("Has a blue flame particle.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("It cooks 5 times faster", var3+32, var4+165, 0x2b2b2b, false);
			 fontRenderer.drawString("than a regular furnace.", var3+32, var4+175, 0x2b2b2b, false);
		 }
		 if(page==4)
		 {
			 fontRenderer.drawString("The Cheap-Cooking campfire is the best", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("campfire to save you some coal and", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("get the best out of your ores with", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("the newest ender pearl technology.", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+110, 0x2b2b2b, false);
			 fontRenderer.drawString("It can cook 2 things at the same time.", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("it doesn't use any coal.", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("It has an gui", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("Has a gray flame particle.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("It cooks 5 times slower", var3+32, var4+165, 0x2b2b2b, false);
			 fontRenderer.drawString("than a regular furnage.", var3+32, var4+175, 0x2b2b2b, false);
			 fontRenderer.drawString("Because of the ender pearl", var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("technology you have a 15%", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("change that your output get", var3+32, var4+205, 0x2b2b2b, false);
			 fontRenderer.drawString("doubled, and a 25% that your", var3+32, var4+215, 0x2b2b2b, false);
			 fontRenderer.drawString("imput will stay after a cooking cycle", var3+32, var4+225, 0x2b2b2b, false);
		 }
		 if(page==5)
		 {
			 fontRenderer.drawString("The Insta-Cooking campfire is the best", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("campfire for cooking food very fast.", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("You can cook every food instandly.", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("Be aware it can only cook food with it.", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+110, 0x2b2b2b, false);
			 fontRenderer.drawString("It can cook a stack at once", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("it doesn't use any coal.", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("Has a red flame particle.", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("It cooks food instantly,", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("with just a right click", var3+32, var4+165, 0x2b2b2b, false);	 
		 }
		 GL11.glPopMatrix();
		 
		 var1 = MathHelper.floor_float((this.width - this.bookImageWidth) / 2.4F);
		 var2 = MathHelper.floor_float((this.height - this.bookImageHeight) / 2.4F);
		 
		 var3 = MathHelper.floor_float((this.width - this.bookImageWidth) / 1.2F);
		 var4 = MathHelper.floor_float((this.height - this.bookImageHeight) / 1.2F);
	
		 
		 GL11.glPushMatrix();
		 GL11.glScalef(1.20F, 1.20F, 1.20F);
		 if(page==7)
		 {		
			 	fontRenderer.drawString("Tent Pegs", var1+38, var2+11, 0x2b2b2b, false);		 
		 }	
		 if(page==8)
		 {
			 	fontRenderer.drawString("Canvas", var1+48, var2+11, 0x2b2b2b, false);
		 }	
		 if(page==9)
		 {
			 	fontRenderer.drawString("Sleeping Bag", var1+30, var2+11, 0x2b2b2b, false);
		 }
		 if(page==10)
		 {
			 	fontRenderer.drawString("The Tent", var1+40, var2+11, 0x2b2b2b, false);
		 }
		 GL11.glPopMatrix();
		 
		 
		 GL11.glPushMatrix();
		 GL11.glScalef(0.6F, 0.6F, 0.6F);
		 if(page==7)
		 {
			 fontRenderer.drawString("If you ever want to make a tent, you first", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("need tent pegs. Tent pegs are very easy to", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("make as you only need a camper's tool and a", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("little bit of iron. Furthermore, every time", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("you craft tent pegs you will get 4 of them", var3+32, var4+105, 0x2b2b2b, false);
			 fontRenderer.drawString("and the camper's tool only takes 1 damage,", var3+32, var4+115, 0x2b2b2b, false);
			 fontRenderer.drawString("so you can make a total of", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("400 tent pegs with only 1", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("camper's tool, but you still", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("need 100 iron to do that.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+170, 0x2b2b2b, false);
			 fontRenderer.drawString("The recipe is shapeless.", var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("The tool will take 1 damage.", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("1 tent needs 2 tent pegs.",var3+32, var4+215, 0x2b2b2b, false);
			 fontRenderer.drawString("You will get 4 tent pegs.", var3+32, var4+205, 0x2b2b2b, false);
		 }
		 if(page==8)
		 {
			 fontRenderer.drawString("If you ever want to make a tent you also", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("need canvas. Canvas is a little harder to", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("make than the tent pegs. You need a camper's", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("tool and 6 string. However every time you", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("craft canvas you will get 10 of them", var3+32, var4+105, 0x2b2b2b, false);
			 fontRenderer.drawString("and the camper's tool only takes 1 damage,", var3+32, var4+115, 0x2b2b2b, false);
			 fontRenderer.drawString("so you can make a total of", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("1000 canvas with only 1", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("camper's tool, but you still", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("need 600 string to do that.", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("Specifications:", var3+32, var4+170, 0x2b2b2b, false);
			 fontRenderer.drawString("The Recipe is shapeless.", var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("The tool will take 1 damage.", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("1 tent needs 5 canvas.",var3+32, var4+205, 0x2b2b2b, false);
			 fontRenderer.drawString("You will get 10 canvas.", var3+32, var4+215, 0x2b2b2b, false);
		 }
		 if(page==9)
		 {
			 fontRenderer.drawString("Tents and storage tents are fine and great,", var3+32, var4+55, 0x2b2b2b, false);
			 fontRenderer.drawString("but what if we want to sleep in them.", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("No worries. You only need to make yourself a", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("sleeping bag and let your camping dreams begin.", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("To craft it, you will need 3 wool of any type.", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("Then, if you have an tent and a sleeping bag,", var3+32, var4+105, 0x2b2b2b, false);
			 fontRenderer.drawString("you need to right-click the tent with the", var3+32, var4+115, 0x2b2b2b, false);
			 fontRenderer.drawString("sleepingbag and you will get an sleeping tent.", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("now if you are done sleeping.", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("you can retrieve the", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("sleeping bag with a right-click", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("on the front of the sleeping", var3+32, var4+165, 0x2b2b2b, false);
			 fontRenderer.drawString("tent with an camper's tool,", var3+32, var4+175, 0x2b2b2b, false);
			 fontRenderer.drawString("and your sleeping tent will",var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("change back into an normal", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("tent and you will get back", var3+32, var4+205, 0x2b2b2b, false);
			 fontRenderer.drawString("your sleeping bag.", var3+32, var4+215, 0x2b2b2b, false);
		 }
		 if(page==10)
		 {
			 fontRenderer.drawString("Tents are very handy if it comes to surviving", var3+32, var4+55, 0x2b2b2b, false);
			 fontRenderer.drawString("in the wild, and there are 3 types of tents.", var3+32, var4+65, 0x2b2b2b, false);
			 fontRenderer.drawString("Normal tents, storage tents, and sleeping", var3+32, var4+75, 0x2b2b2b, false);
			 fontRenderer.drawString("tents. To get the last two you first need", var3+32, var4+85, 0x2b2b2b, false);
			 fontRenderer.drawString("to make the basic one, and to craft it you", var3+32, var4+95, 0x2b2b2b, false);
			 fontRenderer.drawString("will need 2 tent pegs and 5 canvas. To get", var3+32, var4+105, 0x2b2b2b, false);			 
			 fontRenderer.drawString("a storage tent, you need to right-clik, the", var3+32, var4+115, 0x2b2b2b, false);
			 fontRenderer.drawString("tent with a chest. To make a sleeping tent", var3+32, var4+125, 0x2b2b2b, false);
			 fontRenderer.drawString("you need to right-clik the tent", var3+32, var4+135, 0x2b2b2b, false);
			 fontRenderer.drawString("with an sleeping bag. Tents", var3+32, var4+145, 0x2b2b2b, false);
			 fontRenderer.drawString("can be turned if you right-", var3+32, var4+155, 0x2b2b2b, false);
			 fontRenderer.drawString("click them with an camper's", var3+32, var4+165, 0x2b2b2b, false);
			 fontRenderer.drawString("tool. You also can get the", var3+32, var4+175, 0x2b2b2b, false);
			 fontRenderer.drawString("stuff in a tent back with",var3+32, var4+185, 0x2b2b2b, false);
			 fontRenderer.drawString("a right-click with an", var3+32, var4+195, 0x2b2b2b, false);
			 fontRenderer.drawString("camper's tool at the front", var3+32, var4+205, 0x2b2b2b, false);
			 fontRenderer.drawString("of a tent. The storage", var3+32, var4+215, 0x2b2b2b, false);
			 fontRenderer.drawString("tent can store up to 55 slots.", var3+32, var4+225, 0x2b2b2b, false);
		 }
		 GL11.glPopMatrix();
	}
	
	public void addButtonByPage(int page)
	{
		int var1 = (this.width - this.bookImageWidth) / 2;
		int var2 = (this.height - this.bookImageHeight) / 2;
		
		if(page == 6)
		{
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(7, var1+35, var2+75, 10));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(8, var1+75, var2+55, 12));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(9, var1+115, var2+75, 14));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(10, var1+75, var2+95, 16));
		}
		
		if(page == 0)
		{
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(2, var1+35, var2+55, 0));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(3, var1+75, var2+55, 2));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(4, var1+115, var2+55, 4));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(5, var1+55, var2+95, 6));
	        this.buttonList.add(this.buttonIcon = new GuiButtonGuideButton(6, var1+95, var2+95, 8));
		}
	}

	public void triggerButtons(GuiButton button)
	{
		switch(button.id)
		{
			case 2:	this.BookPages = 1;	break;
			case 3:	this.BookPages = 2;	break;
			case 4:	this.BookPages = 3;	break;
			case 5:	this.BookPages = 4;	break;
			case 6:	this.BookPages = 5;	break;
			case 7:	this.BookPages = 7;	break;
			case 8:	this.BookPages = 8;	break;
			case 9:	this.BookPages = 9;	break;
			case 10: this.BookPages = 10; break;
		}
	}
	
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

	@Override
	public void actionPerformed(GuiButton button)
	{
		if(button.id==0&&BookPages<BookPagesTotaal) 
		{
			BookPages+=1;
		}
		else {}
		
		if(button.id==1&&BookPages>0)
		{
			BookPages-=1;
		}
		else {}
		
		this.triggerButtons(button);
		this.initGui();
		this.updateScreen();
	}

	public void drawScreen(int par1, int par2, float par3)
	{
		 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		 this.mc.renderEngine.bindTexture(Textures.GUI_LOCATION + Textures.GUI_BOOK_GUIDE);
		 int var5 = (this.width - this.bookImageWidth) / 2;
		 int var6 = ((this.height - this.bookImageHeight) / 2)-10;
		 this.drawTexturedModalRect(var5, var6, 0, 0, this.bookImageWidth, this.bookImageHeight);
		 int var1 = (this.width - this.bookImageWidth) / 2;
		 int var2 = (this.height - this.bookImageHeight) / 2;
		 int var3 = 0;
		 addImgByPage(BookPages);
		 addTextByPage(BookPages);
		 if(BookPages==0)this.drawTexturedModalRect(((this.width - this.bookImageWidth) / 2)+22, ((this.height - this.bookImageHeight) / 2)+11, 0, 240, 136, 15);
		 if(BookPages>=9)
		 {
			 var3 = 4;
		 }
		 fontRenderer.drawString(Integer.toString(BookPages+1), var1+89-var3, var2 + 145, 0x2b2b2b, false);
	     super.drawScreen(par1, par2, par3);
	}
	
	@Override
	public void onGuiClosed()
	{
		GuideBook.isGuiOpen = false;
	}
}
