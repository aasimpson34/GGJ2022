
package worldmap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import assetmanager.ResourceLookup;
import renderer.GameRenderer;
import townentity.TownEntity;

public class WorldChunk {
	int m_xChunkPosition;
	int m_yChunkPosition;
	
	final int CHUNK_SIZE = 32;
	final int TILE_X_SIZE = 64;
	final int HALF_TILE_X_SIZE = 32;

	final int TILE_Y_SIZE = 47;
	
	int tileId[][] = new int[CHUNK_SIZE][CHUNK_SIZE];
	
	//Array which contains the town entities for the chunk.
	Array<TownEntity> m_townEntity;
	
	public WorldChunk(int x, int y)
	{
		m_xChunkPosition = x * 2048;
		m_yChunkPosition = y * 1504;
	}
	
	public void render()
	{
		SpriteBatch batch = GameRenderer.getInstance().getBatch();
		for(int x = 0; x < CHUNK_SIZE; x++)
		{
			for(int y = 0; y < CHUNK_SIZE; y++)
			{
				AtlasRegion region = null;
				if(tileId[x][y] == 0)
					region = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("grass_dark");
			
				if(y%2 == 0)
					batch.draw(region, x * TILE_X_SIZE + HALF_TILE_X_SIZE + (m_xChunkPosition), y * TILE_Y_SIZE + (m_yChunkPosition));
				else
					batch.draw(region, x * TILE_X_SIZE + (m_xChunkPosition), y * TILE_Y_SIZE + (m_yChunkPosition));
			}
		}
	}
	
	public void update()
	{
		
	}
}
