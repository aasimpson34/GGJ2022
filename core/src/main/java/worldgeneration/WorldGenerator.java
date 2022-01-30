package worldgeneration;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

import noisegeneration.SimplexNoise;
import townentity.TownEntity;
import towngeneration.TownGeneratorHandler;
import worldmap.WorldChunk;

public class WorldGenerator {
	
	Random random_generator;
	boolean townGrid[][]= new boolean[32][32];
	
	/**
	 * This operation will generate a world chunk
	 * (where a chunk is a 32x32 tiled area.
	 * @param x
	 * @param y
	 */
	public WorldChunk generateChunk(int x, int y, long worldSeed)
	{
		long unique_seed_number = ((x *26261) + y) * 24317 + worldSeed;
		
		random_generator = new Random(unique_seed_number);
		
		//Clear town grid
		for(int xR = 0; xR < 32; xR++)
		{
			for(int yR = 0; yR < 32; yR++)
			{
				townGrid[xR][yR] = false;
			}
		}
		
		int tiles_id[][] = generateTerrain();
		Array<TownEntity> townEntities = generateTowns();

		WorldChunk generatedWorldChunk = new WorldChunk(x, y);
		generatedWorldChunk.setTownEntities(townEntities);
		generatedWorldChunk.setTiles(tiles_id);

		
		return generatedWorldChunk;
	}
	
	public WorldChunk recalculateChunk(int x, int y, long worldSeed)
	{
		//Unique seed given by : 1/2(a+b)(a+b+1)+b
		long unique_seed_number = ((x *26261) + y) * 24317 + worldSeed;
		
		random_generator = new Random(unique_seed_number);
		
		
		int tiles_id[][] = generateTerrain();
		
		WorldChunk generatedWorldChunk = new WorldChunk(x, y);
		generatedWorldChunk.setTiles(tiles_id);

		return generatedWorldChunk;
	}

	/**
	 * @return
	 */
	private int[][] generateTerrain() {
		final int WIDTH = 32;
		final int HEIGHT = 32;
		
		int tiles[][] = new int[32][32];
		
	
		int size = 2048;
		float freq = 1/64.0f + random_generator.nextFloat()*0.25f;
		float octs = 20.0f;
		
		for(float x = 0; x < 32; x++)
		{
			for (float y = 0; y < 32; y++)
			{
				int c = 4;
				int a = 1;
				double nx = (c+a*  Math.cos((x/WIDTH) * (2 * Math.PI))) 
							    * Math.cos((y/HEIGHT)* (2 * Math.PI)) ;
				
				double ny = ( c+a* Math.cos((x/WIDTH) * (2 * Math.PI))) 
								* Math.sin((y/HEIGHT)* (2 * Math.PI)) ;
				
				double nz =  a* Math.sin ((x/WIDTH)* (2* Math.PI))  ;
				
				double nw = octs  ;
				
				float height = (float) (fBm(nx *freq, nz*freq , ny*freq+ size  ,nw))+0.15f;  
				
				//Mountains
				if(height >  0.75f)
				{
					tiles[(int)x][(int)y] = 1;
					townGrid[(int)x][(int)y] = true;
				}
				else if(height > 0.025F)
				{
					tiles[(int)x][(int)y] = 0;
				}
				else if(height >  -0.25f)
				{
					tiles[(int)x][(int)y] = 2;
					townGrid[(int)x][(int)y] = true;
				}
				else 
				{
					tiles[(int)x][(int)y] = 3;
					townGrid[(int)x][(int)y] = true;
				}
				//Grass
				
			}
		}
		
		return tiles;
	}
	
	float fBm(double nx,double  ny,double  nz,double nw)
	{
	    double val = 0;
	    for (double o = 0; o < nw; o+=0.9f) {
	        //val += 0.5*o * SimplexNoise.noise((nx*2)*o, (ny*2)*o, (nz*2)*o);
	       // val += 0.5**o * noise(x*2**o, y*2**o, per*2**o)
	        val += (Math.pow(0.5, o) * SimplexNoise.noise(nx *Math.pow(2, o), ny*Math.pow(2,o),nz *Math.pow(2, o)));

	    }
	    return (float)val;
	}

	/**
	 * 
	 */
	private Array<TownEntity> generateTowns() {
		//Randomly select the number of towns that will be assigned
		int numberOfTownsToGenerate = random_generator.nextInt(10) + 4;
		
	

		Array<TownEntity> townEntities = new Array<TownEntity>();
		for(int townToGenerate = 0; townToGenerate < numberOfTownsToGenerate; townToGenerate++)
		{
			//Decide on town positions
			int town_x = 0, town_y = 0;
			int attempts = 0;
			do
			{
				town_x = random_generator.nextInt(32);
				town_y = random_generator.nextInt(32);
				
				attempts++;
				if(attempts >= 20)
					break;
				
			}while(townGrid[town_x][town_y]);
			
			if(attempts < 20)
			{
			//Generate towns
				townGrid[town_x][town_y] = true;
			
				townEntities.add(new TownGeneratorHandler().generateNewTown(town_x, town_y));
			}
			else
			{
				break;
			}
		}
		return townEntities;	
	}
}
