package rikmuld.entity.ai;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.src.ModLoader;

public class EntityAIAvoidCampfire extends EntityAIBase{
	
	public EntityAIAvoidCampfire(EntityCreature entity, int par1, int par2, int par3)
	{
		theEntity = entity;
		X = par1;
		Y = par2;
		Z = par3;
	}
	
	EntityCreature theEntity;
	
	int X;
	int Y;
	int Z;
			
	int CampX = (int) X;
	int CampY = (int) Y;
	int CampZ = (int) Z+1;
	
	int[] CloseX = {-1, 1, 0, 0, 0};
	int[] CloseZ = {0, 0, 1, -1, 0};
	
	@Override
	public boolean shouldExecute() 
	{
		if(CamperIsCloseToFire())
		{
			return true;
		}
		return false;
	}

	private boolean CamperIsCloseToFire()
	{	
		for(int p = 0; p<5; p++)
		{
			if(Y==CampY||Y==CampY-1||Y==CampY+1)
			{
				if(X+CloseX[p]==CampX&&Z+CloseZ[p]==CampZ)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	 public void startExecuting()
	 {
		
	 }

}
