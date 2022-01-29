/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: TileRenderer.java
 * Purpose of class: 
 *
 * Written by @author
 */
package renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;



/**
 * @author aasim
 *
 */
public class TileRenderer {
	final static int CHUNK_SIZE = 32;
	final static int TILE_X_SIZE = 64;
	final static int HALF_TILE_X_SIZE = 32;
	final static int TILE_Y_SIZE = 47;

	final static int TOTAL_CHUNK_WIDTH = TILE_X_SIZE * CHUNK_SIZE;
	final static int TOTAL_CHUNK_HEIGHT = TILE_Y_SIZE * CHUNK_SIZE;
	
	public static void renderTile(AtlasRegion texture,int x, int y, int xOffset, int yOffset)
	{
		SpriteBatch batch = GameRenderer.getInstance().getBatch();
		
		if(y%2 == 0)
			batch.draw(texture, x * TILE_X_SIZE + HALF_TILE_X_SIZE + (xOffset * TOTAL_CHUNK_WIDTH), y * TILE_Y_SIZE + (yOffset * TOTAL_CHUNK_HEIGHT));
		else
			batch.draw(texture, x * TILE_X_SIZE + (xOffset * TOTAL_CHUNK_WIDTH), y * TILE_Y_SIZE + (yOffset * TOTAL_CHUNK_HEIGHT));
	}
}
