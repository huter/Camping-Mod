package rikmuld.entity.ai;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import rikmuld.entity.Camper;

public class EntityAILookAtTradePlayerCamper extends EntityAIWatchClosest {

	 private final Camper theMerchant;

	    public EntityAILookAtTradePlayerCamper(Camper par1EntityCamper)
	    {
	        super(par1EntityCamper, EntityPlayer.class, 8.0F);
	        this.theMerchant = par1EntityCamper;
	    }

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute()
	    {
	        if (this.theMerchant.isTrading())
	        {
	            this.closestEntity = this.theMerchant.getCustomer();
	            return true;
	        }
	        else
	        {
	            return false;
	        }
	    }
	}
