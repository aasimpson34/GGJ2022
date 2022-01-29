
package worldmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import assetmanager.ResourceLookup;
import renderer.GameRenderer;
import renderer.TileRenderer;
import townentity.TownEntity;

public class WorldChunk {
	int m_xChunkPosition;
	int m_yChunkPosition;
	
	final int CHUNK_SIZE = 32;

	
	int tileId[][] = new int[CHUNK_SIZE][CHUNK_SIZE];
	
	//Array which contains the town entities for the chunk.
	Array<TownEntity> m_townEntity;
	
	public WorldChunk(int x, int y)
	{
		m_xChunkPosition = x * 2048;
		m_yChunkPosition = y * 1504;
	}
	
	public void render(int xOffset, int yOffset)
	{
		for(int x = 0; x < CHUNK_SIZE; x++)
		{
			for(int y = 0; y < CHUNK_SIZE; y++)
			{
				AtlasRegion region = null;
				if(tileId[x][y] == 0)
					region = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("grass_light");
				else if(tileId[x][y] == 1)
					region = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("mountain");

				TileRenderer.renderTile(region, x, y, xOffset, yOffset);
			}
		}
		
		for(TownEntity entity : m_townEntity)
			entity.render(xOffset, yOffset);
	}
	
	public void update()
	{
		for(TownEntity entity : m_townEntity)
			entity.update();
		
		//Check if the player is within a town entity hex
			//tell the town that the player is near (draw a prompt)
			//if the player interacts then tell the town entity to render its
			// ui.
		
	}

	/**
	 * @param townEntities
	 */
	public void setTownEntities(Array<TownEntity> townEntities) {
		m_townEntity = townEntities;		
	}

	/**
	 * @param tiles_id
	 */
	public void setTiles(int[][] tiles_id) {
		tileId = tiles_id;		
	}
}
