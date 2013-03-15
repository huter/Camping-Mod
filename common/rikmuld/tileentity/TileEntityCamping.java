package rikmuld.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCamping extends TileEntity {

	 private int direction = 0;
	 
	 public TileEntityCamping() 
	 {
		  
	 }
 
	 public int getDirection() 
	 {
		 return direction;
	 }

	 public void setDirection(int direction2) 
	 {
        this.direction = direction2;
	 }
	 
	 public boolean isUseableByPlayer(EntityPlayer player) 
	 {
		 return true;
	 }

	 public void readFromNBT(NBTTagCompound nbtTagCompound) 
	 {
		 super.readFromNBT(nbtTagCompound);
		 direction = nbtTagCompound.getInteger("direction");
	 }

	 public void writeToNBT(NBTTagCompound nbtTagCompound) 
	 {
		 super.writeToNBT(nbtTagCompound);
		 nbtTagCompound.setInteger("direction", direction);	    
	 }
}
