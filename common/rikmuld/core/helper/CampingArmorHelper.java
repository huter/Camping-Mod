package rikmuld.core.helper;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import rikmuld.core.register.ModEvents;

public class CampingArmorHelper {

	static Random random = new Random();
	
	public static void applyHelmEffects(EntityPlayer player) 
	{
		if (player.isJumping&&player.motionY>=0.02F) player.motionY += 0.01F;
		player.landMovementFactor += 0.0125F;
		player.jumpMovementFactor += 0.005F;
	}

	public static void applyChestEffects(EntityPlayer player)
	{
		if (player.isJumping&&player.motionY>=0.02F) player.motionY += 0.01F;
		player.landMovementFactor += 0.0125F;
		player.jumpMovementFactor += 0.005F;
	}

	public static void applyLegEffects(EntityPlayer player) 
	{
		if (player.isJumping&&player.motionY>=0.02F) player.motionY += 0.01F;
		player.landMovementFactor += 0.0125F;
		player.jumpMovementFactor += 0.005F;
		
		player.stepHeight = 1;
	}

	public static void applyBootEffects(EntityPlayer player) 
	{
		if (player.isJumping&&player.motionY>=0.02F) player.motionY += 0.01F;
		player.landMovementFactor += 0.0125F;		
		player.jumpMovementFactor += 0.005F;
	
		ModEvents.decreaseFallDamage(4.0F);
	}
}
