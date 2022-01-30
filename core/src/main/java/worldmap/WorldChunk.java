
package worldmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import assetmanager.ResourceLookup;
import camera.GameCamera;
import player.PlayerEntity;
import renderer.GameRenderer;
import renderer.TileRenderer;
import townentity.TownEntity;

public class WorldChunk {
	int m_xChunkPosition;
	int m_yChunkPosition;
	
	final int CHUNK_SIZE = 32;

	Array<Polygon> m_collision;
	
	boolean renderTownPrompt;
	Vector2 m_townPromptPosition;
	
	int tileId[][] = new int[CHUNK_SIZE][CHUNK_SIZE];
	
	//Array which contains the town entities for the chunk.
	Array<TownEntity> m_townEntity;
	BitmapFont m_bitmapFont;
	
	public WorldChunk(int x, int y)
	{
		m_xChunkPosition = x;
		m_yChunkPosition = y;
		m_collision = new Array<Polygon>();
		m_bitmapFont = new BitmapFont();
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
				else if(tileId[x][y] == 2)
					region = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("sand_dark");
				else if(tileId[x][y] == 3)
					region = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("water_light");

				TileRenderer.renderTile(region, x, y, xOffset, yOffset);
			}
		}
		for(TownEntity entity : m_townEntity) {
			entity.render(xOffset, yOffset);
		}
	}
	
	public void renderUI(int xOffset, int yOffset)
	{
		if(renderTownPrompt)
		{
			m_bitmapFont.draw(GameRenderer.getInstance().getBatch(), "Press E to interact", m_townPromptPosition.x * 64, m_townPromptPosition.y * 47);
		}
		for(TownEntity entity : m_townEntity) {
			entity.renderUI();
		}
		
	}
	
	public void update(PlayerEntity player)
	{
		
		for(TownEntity entity : m_townEntity)
		{
			entity.update();
			entity.updateEntityUI(entity);
			
			Vector2 global = player.getPosition().cpy();
			global.y+= 5;
			
			Vector2 playerLeft = global.cpy();
			playerLeft.x +=16;
			
			Vector2 playerRight = global.cpy();
			playerRight.x += 32;
			
			Polygon townCol = generateHexCollisionTile(entity.getPositionX(), entity.getPositionY(), m_xChunkPosition, m_yChunkPosition);
			if(townCol.contains(playerLeft) || townCol.contains(playerRight))
			{
				System.out.println("col");
				renderTownPrompt = true;
				m_townPromptPosition = new Vector2(entity.getPositionX(), entity.getPositionY());
				entity.updateUI();
				break;
			} else {
				entity.forceCloseUI();
				renderTownPrompt = false;
			}
		}
		
		
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

	/**
	 * 
	 */
	public Array<Polygon> generateCollisionShapes(int xOffset, int yOffset)
	{
		m_collision.clear();
		for(int x = 0; x < CHUNK_SIZE; x++)
		{
			for(int y = 0; y < CHUNK_SIZE; y++)
			{
				if(tileId[x][y] != 0 && tileId[x][y] != 2)
				{
					m_collision.add(generateHexCollisionTile(x, y, xOffset, yOffset));
				}
			}
		}
		
		m_xChunkPosition = xOffset;
		m_yChunkPosition = yOffset;
		return m_collision;
	}
	
	public Polygon generateHexCollisionTile(int x, int y, int xOffset, int yOffset)
	{
		
		float vertices[] = {
				32, 0,
				64, 16,
				64,	48,
				32,	64,
				0,	48,
				0,	16
		};
		
		Polygon polygon = new Polygon(vertices);
		polygon.translate(xOffset * 2048, yOffset * 1504);
		
		if(y%2 == 0)
			polygon.translate(x* 64 + 32, y * 47);
		else
			polygon.translate(x* 64, y * 47);
		
		return polygon;
		
	}
}
