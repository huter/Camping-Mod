package rikmuld.core.register;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
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
}
