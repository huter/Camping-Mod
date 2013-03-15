package rikmuld.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import rikmuld.entity.Camper;

public class EntityAITradePlayerCamper extends EntityAIBase
{
    private Camper Camper;

    public EntityAITradePlayerCamper(Camper par1EntityCamper)
    {
        this.Camper = par1EntityCamper;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.Camper.isEntityAlive())
        {
            return false;
        }
        else if (this.Camper.isInWater())
        {
            return false;
        }
        else if (!this.Camper.onGround)
        {
            return false;
        }
        else if (this.Camper.velocityChanged)
        {
            return false;
        }
        else
        {
            EntityPlayer entityplayer = this.Camper.getCustomer();
            return entityplayer == null ? false : (this.Camper.getDistanceSqToEntity(entityplayer) > 16.0D ? false : entityplayer.openContainer instanceof Container);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.Camper.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.Camper.setCustomer((EntityPlayer)null);
    }
}
