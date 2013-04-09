package rikmuld.core.handlers;

import java.util.EnumSet;
import java.util.logging.Level;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rikmuld.core.helper.KeyHelper;
import rikmuld.core.lib.ModInfo;
import rikmuld.core.register.ModLogger;
import rikmuld.network.PacketTypeHandler;
import rikmuld.network.packets.PacketKeyPressed;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class KeyHandler extends KeyBindingRegistry.KeyHandler {

    public KeyHandler() 
    {	
        super(KeyHelper.gatherKeyBindings(), KeyHelper.gatherIsRepeating());
    }

    @Override
    public String getLabel() 
    {
        return ModInfo.MOD_ID + ": " + this.getClass().getSimpleName();
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
    {
        if (tickEnd) 
        {
            if (FMLClientHandler.instance().getClient().inGameHasFocus) 
            {
                EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
                if (player != null) 
                {
                    ItemStack currentItem = player.getCurrentEquippedItem();
                    ItemStack armorItem = player.inventory.getStackInSlot(38);
                    if (currentItem != null) 
                    {
                        if (KeyHelper.hasKeyBind(currentItem)) 
                        {
                        	 PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
                        }
                    }
                    if (armorItem != null) 
                    {
                        if (KeyHelper.hasKeyBind(armorItem)) 
                        {
                        	 PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
    {

    }

    @Override
    public EnumSet<TickType> ticks() 
    {
        return EnumSet.of(TickType.CLIENT);
    }
}