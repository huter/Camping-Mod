package rikmuld.core.handlers;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import rikmuld.core.helper.CloakHelper;
import rikmuld.core.lib.Config;
import rikmuld.core.lib.Textures;
import rikmuld.core.proxys.ClientProxy;
import rikmuld.core.proxys.CommonProxy;
import rikmuld.core.register.ModBlocks;
import rikmuld.core.register.ModLogger;

public class PlayerRenderingHandler extends RenderPlayer{
		
		private ModelBiped modelBipedMain;

		public static boolean hasUsers = false;
	
		public static String[] developers;
		public static String[] codinghelp;
		public static String[] help;
	    
	    public PlayerRenderingHandler()
	    {
	    	 super();
	    	 this.modelBipedMain = (ModelBiped)this.mainModel;
	    }
	    
	    @Override
	    protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2)
	    {	
	    	ItemStack itemstack2 = null;
			
			if(CommonProxy.CampingInv!=null) itemstack2 = CommonProxy.CampingInv.getStackInSlot(0);
			
			if(itemstack2!=null)
			{
				ClientProxy.backpackRenderer.renderItemOnBack(par1EntityPlayer, itemstack2);
			}
			
	    	if(!hasUsers&&Config.GENERAL_XML_INTERACTION)CloakHelper.getCloakUsers(this);
	    	
	    	String cloakFile = null;
	    	
	    	if(developers!=null&&codinghelp!=null&&help!=null)
	    	{
		    	for(int x = 0; x<developers.length; x++)
		    	{
					 if(par1EntityPlayer.username.equals(developers[x]))
					 {
						 cloakFile = Textures.CLOAK_LOCATION + Textures.CLOAK_DEV;
					 }
		    	}
			 
		    	for(int x = 0; x<codinghelp.length; x++)
		    	{
		    		if(par1EntityPlayer.username.equals(codinghelp[x]))
		    		{
		    			 cloakFile = Textures.CLOAK_LOCATION + Textures.CLOAK_CHELP;
		    		}
			 	}
			 
		    	for(int x = 0; x<help.length; x++)
		    	{
					 if(par1EntityPlayer.username.equals(help[x]))
					 {
						 cloakFile = Textures.CLOAK_LOCATION + Textures.CLOAK_HELP;
					 }
		    	}
	    	}
			 
	        float f1 = 1.0F;
	        GL11.glColor3f(f1, f1, f1);
	        ItemStack itemstack = par1EntityPlayer.inventory.armorItemInSlot(3);


	        if (itemstack != null)
	        {
	            GL11.glPushMatrix();
	            this.modelBipedMain.bipedHead.postRender(0.0625F);
	            float f2;

	            if (itemstack != null && itemstack.getItem() instanceof ItemBlock)
	            {
	                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
	                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

	                if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
	                {
	                    f2 = 0.625F;
	                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
	                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
	                    GL11.glScalef(f2, -f2, -f2);
	                }

	                this.renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack, 0);
	            }
	            else if (itemstack.getItem().itemID == Item.skull.itemID)
	            {
	                f2 = 1.0625F;
	                GL11.glScalef(f2, -f2, -f2);
	                String s = "";

	                if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("SkullOwner"))
	                {
	                    s = itemstack.getTagCompound().getString("SkullOwner");
	                }

	                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), s);
	            }

	            GL11.glPopMatrix();
	        }

	        float f3;
	        float f4;

	        if (par1EntityPlayer.username.equals("deadmau5") && this.loadDownloadableImageTexture(par1EntityPlayer.skinUrl, (String)null))
	        {
	            for (int i = 0; i < 2; ++i)
	            {
	                float f5 = par1EntityPlayer.prevRotationYaw + (par1EntityPlayer.rotationYaw - par1EntityPlayer.prevRotationYaw) * par2 - (par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2);
	                f3 = par1EntityPlayer.prevRotationPitch + (par1EntityPlayer.rotationPitch - par1EntityPlayer.prevRotationPitch) * par2;
	                GL11.glPushMatrix();
	                GL11.glRotatef(f5, 0.0F, 1.0F, 0.0F);
	                GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
	                GL11.glTranslatef(0.375F * (float)(i * 2 - 1), 0.0F, 0.0F);
	                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
	                GL11.glRotatef(-f3, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(-f5, 0.0F, 1.0F, 0.0F);
	                f4 = 1.3333334F;
	                GL11.glScalef(f4, f4, f4);
	                this.modelBipedMain.renderEars(0.0625F);
	                GL11.glPopMatrix();
	            }
	        }

	        float f6;

	        if (cloakFile!=null&& !par1EntityPlayer.isInvisible() && !par1EntityPlayer.getHideCape())
	        {
	        	RenderEngine renderengine = this.renderManager.renderEngine;
	        	int i = renderengine.getTexture(cloakFile);
	            GL11.glBindTexture(GL11.GL_TEXTURE_2D, i);
	            
	            GL11.glPushMatrix();
	            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
	            double d0 = par1EntityPlayer.field_71091_bM + (par1EntityPlayer.field_71094_bP - par1EntityPlayer.field_71091_bM) * (double)par2 - (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX) * (double)par2);
	            double d1 = par1EntityPlayer.field_71096_bN + (par1EntityPlayer.field_71095_bQ - par1EntityPlayer.field_71096_bN) * (double)par2 - (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY) * (double)par2);
	            double d2 = par1EntityPlayer.field_71097_bO + (par1EntityPlayer.field_71085_bR - par1EntityPlayer.field_71097_bO) * (double)par2 - (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ) * (double)par2);
	            f6 = par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2;
	            double d3 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
	            double d4 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
	            float f7 = (float)d1 * 10.0F;

	            if (f7 < -6.0F)
	            {
	                f7 = -6.0F;
	            }

	            if (f7 > 32.0F)
	            {
	                f7 = 32.0F;
	            }

	            float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
	            float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;

	            if (f8 < 0.0F)
	            {
	                f8 = 0.0F;
	            }

	            float f10 = par1EntityPlayer.prevCameraYaw + (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw) * par2;
	            f7 += MathHelper.sin((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f10;

	            if (par1EntityPlayer.isSneaking())
	            {
	                f7 += 25.0F;
	            }

	            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
	            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
	            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            this.modelBipedMain.renderCloak(0.0625F);
	            GL11.glPopMatrix();
	        }
	        if (this.loadDownloadableImageTexture(par1EntityPlayer.cloakUrl, (String)null) && !par1EntityPlayer.isInvisible() && !par1EntityPlayer.getHideCape())
	        {
	            GL11.glPushMatrix();
	            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
	            double d0 = par1EntityPlayer.field_71091_bM + (par1EntityPlayer.field_71094_bP - par1EntityPlayer.field_71091_bM) * (double)par2 - (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX) * (double)par2);
	            double d1 = par1EntityPlayer.field_71096_bN + (par1EntityPlayer.field_71095_bQ - par1EntityPlayer.field_71096_bN) * (double)par2 - (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY) * (double)par2);
	            double d2 = par1EntityPlayer.field_71097_bO + (par1EntityPlayer.field_71085_bR - par1EntityPlayer.field_71097_bO) * (double)par2 - (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ) * (double)par2);
	            f6 = par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2;
	            double d3 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
	            double d4 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
	            float f7 = (float)d1 * 10.0F;

	            if (f7 < -6.0F)
	            {
	                f7 = -6.0F;
	            }

	            if (f7 > 32.0F)
	            {
	                f7 = 32.0F;
	            }

	            float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
	            float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;

	            if (f8 < 0.0F)
	            {
	                f8 = 0.0F;
	            }

	            float f10 = par1EntityPlayer.prevCameraYaw + (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw) * par2;
	            f7 += MathHelper.sin((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f10;

	            if (par1EntityPlayer.isSneaking())
	            {
	                f7 += 25.0F;
	            }

	            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
	            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
	            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            this.modelBipedMain.renderCloak(0.0625F);
	            GL11.glPopMatrix();
	        }

	        ItemStack itemstack1 = par1EntityPlayer.inventory.getCurrentItem();

	        if (itemstack1 != null)
	        {
	            GL11.glPushMatrix();
	            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
	            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

	            if (par1EntityPlayer.fishEntity != null)
	            {
	                itemstack1 = new ItemStack(Item.stick);
	            }

	            EnumAction enumaction = null;

	            if (par1EntityPlayer.getItemInUseCount() > 0)
	            {
	                enumaction = itemstack1.getItemUseAction();
	            }

	            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
	            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));
	            boolean isBlock = itemstack1.itemID < Block.blocksList.length && itemstack1.getItemSpriteNumber() == 0;

	            if (is3D || (isBlock && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack1.itemID].getRenderType())))
	            {
	                f3 = 0.5F;
	                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
	                f3 *= 0.75F;
	                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	                GL11.glScalef(-f3, -f3, f3);
	            }
	            else if (itemstack1.itemID == Item.bow.itemID)
	            {
	                f3 = 0.625F;
	                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
	                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
	                GL11.glScalef(f3, -f3, f3);
	                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	            }
	            else if (Item.itemsList[itemstack1.itemID].isFull3D())
	            {
	                f3 = 0.625F;

	                if (Item.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering())
	                {
	                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
	                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
	                }

	                if (par1EntityPlayer.getItemInUseCount() > 0 && enumaction == EnumAction.block)
	                {
	                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
	                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
	                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
	                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
	                }

	                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
	                GL11.glScalef(f3, -f3, f3);
	                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	            }
	            else
	            {
	                f3 = 0.375F;
	                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
	                GL11.glScalef(f3, f3, f3);
	                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
	                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
	                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
	            }

	            float f11;
	            int j;
	            float f12;

	            if (itemstack1.getItem().requiresMultipleRenderPasses())
	            {
	                for (j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++j)
	                {
	                    int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
	                    f12 = (float)(k >> 16 & 255) / 255.0F;
	                    f11 = (float)(k >> 8 & 255) / 255.0F;
	                    f6 = (float)(k & 255) / 255.0F;
	                    GL11.glColor4f(f12, f11, f6, 1.0F);
	                    this.renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack1, j);
	                }
	            }
	            else
	            {
	                j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
	                f4 = (float)(j >> 16 & 255) / 255.0F;
	                f12 = (float)(j >> 8 & 255) / 255.0F;
	                f11 = (float)(j & 255) / 255.0F;
	                GL11.glColor4f(f4, f12, f11, 1.0F);
	                this.renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack1, 0);
	            }

	            GL11.glPopMatrix();
	        }
	    }
}