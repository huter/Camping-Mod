package rikmuld.client.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import rikmuld.client.model.item.BackpackModel;
import rikmuld.core.lib.Textures;

public class ItemCampingBagRenderer implements IItemRenderer {

	public BackpackModel model;
	public static Minecraft mc;
	
	public ItemCampingBagRenderer()
	{
		model = new BackpackModel();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if(type!=null)
		{
			switch(type)
			{
				case EQUIPPED: break;
				case FIRST_PERSON_MAP: break;
				case ENTITY: break;
				case INVENTORY: break;
				default: break;
			}
		}
	}
	
	public void renderItemOnBack(EntityPlayer player, ItemStack item)
	{
		GL11.glPushMatrix();
		
		GL11.glScalef(1.26F, 1.525F, 1.20F);
		
		GL11.glTranslatef(-0.189F, 0.0F, 0.1F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(Textures.MODEL_LOCATION + Textures.MODEL_BACKPACK);
		model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	
		GL11.glPopMatrix();
	}
}
