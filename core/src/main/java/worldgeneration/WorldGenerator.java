package worldgeneration;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

import townentity.TownEntity;
import towngeneration.TownGeneratorHandler;

public class WorldGenerator {
	
	/**
	 * This operation will generate a world chunk
	 * (where a chunk is a 32x32 tiled area.
	 * @param x
	 * @param y
	 */
	public void generateChunk(int x, int y)
	{
		//Unique seed given by : 1/2(a+b)(a+b+1)+b
		long unique_seed_number = (long)(0.5F  * (x + y) * (x + y + 1) + y);
		
		Random random_generator = new Random(unique_seed_number);
		
		//Randomly select the number of towns that will be assigned
		int numberOfTownsToGenerate = random_generator.nextInt(5);
		
		boolean townGrid[][]= new boolean[32][32];
		
		Array<TownEntity> townEntities = new Array<TownEntity>();
		for(int townToGenerate = 0; townToGenerate < numberOfTownsToGenerate; townToGenerate++)
		{
			//Decide on town positions
			int town_x = 0, town_y = 0;
			do
			{
				town_x = random_generator.nextInt(32);
				town_y = random_generator.nextInt(32);
				
			}while(townGrid[town_x][town_y]);
			
			//Generate towns
			
			townEntities.add(new TownGeneratorHandler().generateNewTown(town_x, town_y));
		}
	}
}
