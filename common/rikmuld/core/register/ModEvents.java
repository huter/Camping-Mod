package rikmuld.core.register;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import rikmuld.block.plant.RadishCrop;

public class ModEvents {
	
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
				event.drops.add(new EntityItem(event.entityPlayer.worldObj, event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ, ItemStack.loadItemStackFromNBT(slotEntry)));
			}
		}
    }	
}
