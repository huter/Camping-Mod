package rikmuld.core.helper;

import java.util.ArrayList;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class KeyHelper {

public static ArrayList<Integer> keyItems = new ArrayList<Integer>();

	public static String keyCraft = "camping.craftKey";
	public static String keyBackpack = "camping.backpackKey";
	
 	public static ArrayList<KeyBinding> keyBindingsList;
	public static ArrayList<Boolean> isRepeatingList;

	public static void addKeyItem(Item item)
	{		
		keyItems.add(item.itemID);
		LanguageRegistry.instance().addStringLocalization(keyCraft, "en_US", "Crafting Key");
		LanguageRegistry.instance().addStringLocalization(keyBackpack, "en_US", "Open Backpack");
	}
	
	public static boolean hasKeyBind(ItemStack item)
	{		
		if (keyItems.contains(item.itemID)) return true;
		else return false;
	}
	    
	public static void addKeyBinding(String name, int value) 
    {
        if (keyBindingsList == null) 
        {
            keyBindingsList = new ArrayList<KeyBinding>();
        }
        keyBindingsList.add(new KeyBinding(name, value));
    }

    public static void addIsRepeating(boolean value) 
    {
        if (isRepeatingList == null)
        {
            isRepeatingList = new ArrayList<Boolean>();
        }
        isRepeatingList.add(value);
    }

    public static KeyBinding[] gatherKeyBindings() 
    {
        return keyBindingsList.toArray(new KeyBinding[keyBindingsList.size()]);
    }

    public static boolean[] gatherIsRepeating() 
    {
        boolean[] isRepeating = new boolean[isRepeatingList.size()];

        for (int x = 0; x < isRepeating.length; x++) {
            isRepeating[x] = isRepeatingList.get(x).booleanValue();
        }
        return isRepeating;
    }
}

