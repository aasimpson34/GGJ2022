package worldgeneration;

import java.util.Random;

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
		int numberOfTownsToGenerate = random_generator.nextInt(10);
		
		//Decide on town positions
		
		//Generate towns
	}
	
	/**
	 * Call this operation to generate a town 
	 * @param x tile position
	 * @param y tile position
	 */
	public void generateTown(int x, int y)
	{
		
	}

}
