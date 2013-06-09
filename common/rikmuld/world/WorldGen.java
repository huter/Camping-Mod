package rikmuld.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import rikmuld.core.lib.Config;
import rikmuld.world.structures.CampsiteSimple;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {
	
	Random random = new Random();
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
			case -1: generateNether(world, random, chunkX*16, chunkZ*16);
			case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}

	private void generateSurface(World world, Random random, int blockX, int blockZ) 
	{ 
		if (Config.WORLD_GEN_ENABLED)
		{
			if(random.nextInt(Config.WORLD_GEN_SMALL_CAMP_SPAWN_RARENESS)<2)
			{
				int Xcoord1 = blockX + random.nextInt(16);
				int Ycoord1 = random.nextInt(80);
				int Zcoord1 = blockZ + random.nextInt(16);
				
				(new CampsiteSimple()).generate(world, random, Xcoord1, Ycoord1, Zcoord1);
			}
		}
	}
 
	private void generateNether(World world, Random random, int blockX, int blockZ) {}
}
