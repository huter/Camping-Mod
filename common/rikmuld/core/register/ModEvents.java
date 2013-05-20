package rikmuld.core.register;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import rikmuld.block.plant.RadishCrop;
import rikmuld.core.helper.CampingArmorHelper;

public class ModEvents {
	
	public static float decreseFallDamage = 0;

	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new ModEvents());
	}

	@ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event)
    {
        if (event.ID == ModBlocks.RadishCrop.blockID)
        {
        	((RadishCrop)ModBlocks.RadishCrop).Grow(event.world, event.X, event.Y, event.Z, event);
        }
    }
	
	@ForgeSubscribe
    public void onPlayerDead(PlayerDropsEvent event)
	{	
		NBTTagList backpack = event.entityPlayer.getEntityData().getCompoundTag("CampingInventory").getTagList("CampingItems");
		for (int i = 0; i < backpack.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) backpack.tagAt(i);
			int j = slotEntry.getByte("CampingSlot") & 0xff;
			if (j >= 0 && j < 2) 
			{
				event.entityPlayer.dropPlayerItemWithRandomChoice(ItemStack.loadItemStackFromNBT(slotEntry), true);
			}
		}
		NBTTagList backpack2 = event.entityPlayer.getEntityData().getCompoundTag("CampingCraftInventory").getTagList("CampingCraftItems");
		for (int i = 0; i < backpack2.tagCount(); i++) 
		{
			NBTTagCompound slotEntry = (NBTTagCompound) backpack2.tagAt(i);
			int j = slotEntry.getByte("CampingCraftSlot") & 0xff;
			if (j >= 0 && j < 18) 
			{
				event.entityPlayer.dropPlayerItemWithRandomChoice(ItemStack.loadItemStackFromNBT(slotEntry), true);
			}
		}
	}
	
	@ForgeSubscribe
	public void onEntityFall(LivingFallEvent event)
	{	
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			
			player.motionY-=0.05F;
			event.distance -= decreseFallDamage;
		}
	}

	public static void decreaseFallDamage(float decrese) 
	{
		decreseFallDamage  = decrese;
	}
}
