package worldmap;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import camera.GameCamera;
import player.PlayerEntity;
import worldgeneration.WorldGenerator;

public class WorldMap {
	Map<Vector2, Boolean> hasBeenGenerated = new HashMap<Vector2, Boolean>();
	
	Vector2 centreChunk;
	
	WorldChunk m_activeChunks[][] = new WorldChunk[3][3];
	WorldGenerator m_worldGenerator;
	long worldSeed;
	
	Array<Polygon> worldCollisions;
	
	public WorldMap()
	{
		worldSeed = System.currentTimeMillis();
		centreChunk = new Vector2(0,0);
		m_worldGenerator = new WorldGenerator();
		worldCollisions = new Array<Polygon>();
		for(int x = -1; x <= 1; x++)
		{
			for(int y = -1; y <= 1; y++)
			{
				m_activeChunks[x + 1][y + 1] = m_worldGenerator.generateChunk(x, y, worldSeed);
				hasBeenGenerated.put(new Vector2(x,y),  true);
				m_activeChunks[x + 1][y + 1].generateCollisionShapes(x, y);

			}
		}
	}
	
	public void update(PlayerEntity playerEntity)
	{
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				m_activeChunks[x][y].update(playerEntity);
			}
		}
		
		m_activeChunks[1][1].updateTownCollisions(playerEntity);
		
		boolean hasMoved = false;
		
		if(GameCamera.getInstance().getPosition().x < 0)
		{
			//move chunks to the right
			centreChunk.x--;
			moveLeft(1);
			playerEntity.getPosition().x += 2048;
			GameCamera.getInstance().translate(2048,(int) GameCamera.getInstance().getPosition().y);
			hasMoved = true;

		} 
		if(GameCamera.getInstance().getPosition().x > 2048)
		{
			//move chunks to the right
			centreChunk.x++;
			moveRight(1);
			playerEntity.getPosition().x -= 2048;
			GameCamera.getInstance().translate(0,(int) GameCamera.getInstance().getPosition().y);
			hasMoved = true;
		}
		
		if(GameCamera.getInstance().getPosition().y > 1504)
		{
			//move chunks to the right
			centreChunk.y++;
			moveDown(1);
			playerEntity.getPosition().y -= 1504;
			GameCamera.getInstance().translate((int) GameCamera.getInstance().getPosition().x, 0);
			hasMoved = true;
		}
		
		if(GameCamera.getInstance().getPosition().y < 0)
		{
			//move chunks to the right
			centreChunk.y--;
			moveUp(1);
			playerEntity.getPosition().y += 1504;
			GameCamera.getInstance().translate((int) GameCamera.getInstance().getPosition().x, 1504);
			hasMoved = true;
		}
		
		//If the tiles have moved to a new one we want to generate the collision shape
		if(hasMoved)
		{
			worldCollisions.clear();
			
			for(int x = 0; x < 3; x++)
				for(int y = 0;y < 3; y++)
				{
					worldCollisions.addAll(m_activeChunks[x][y].generateCollisionShapes(x-1, y-1));
				}
		}
	}
	
	/**
	 * @param i
	 */
	private void moveRight(int i) {
		m_activeChunks[0][0] = m_activeChunks[1][0];
		m_activeChunks[0][1] = m_activeChunks[1][1];
		m_activeChunks[0][2] = m_activeChunks[1][2];
		
		m_activeChunks[1][0] = m_activeChunks[2][0];
		m_activeChunks[1][1] = m_activeChunks[2][1];
		m_activeChunks[1][2] = m_activeChunks[2][2];
		
		for(int y = -1; y <= 1; y++)
		{
			if(!hasBeenGenerated.containsKey(new Vector2(centreChunk.x + 1, centreChunk.y + y)))
				m_activeChunks[2][y + 1] = m_worldGenerator.generateChunk((int)centreChunk.x + 1, (int)centreChunk.y + y, worldSeed);
		//	else
			//	m_activeChunks[2][y + 1] = m_worldGenerator.recalculateChunk((int)centreChunk.x + 1, (int)centreChunk.y + y, worldSeed);
		}		
	}

	/**
	 * @param i
	 * @param j
	 */
	private void moveLeft(int i) {
		m_activeChunks[2][0] = m_activeChunks[1][0];
		m_activeChunks[2][1] = m_activeChunks[1][1];
		m_activeChunks[2][2] = m_activeChunks[1][2];
		
		m_activeChunks[1][0] = m_activeChunks[0][0];
		m_activeChunks[1][1] = m_activeChunks[0][1];
		m_activeChunks[1][2] = m_activeChunks[0][2];
		
		for(int y = -1; y <= 1; y++)
		{
			if(!hasBeenGenerated.containsKey(new Vector2(centreChunk.x - 1, centreChunk.y + y)))
				m_activeChunks[0][y + 1] = m_worldGenerator.generateChunk((int)centreChunk.x - 1, (int)centreChunk.y + y, worldSeed);
		}
		
	}
	
	private void moveDown(int i) {
		m_activeChunks[0][0] = m_activeChunks[0][1];
		m_activeChunks[1][0] = m_activeChunks[1][1];
		m_activeChunks[2][0] = m_activeChunks[2][1];
	
		m_activeChunks[0][1] = m_activeChunks[0][2];
		m_activeChunks[1][1] = m_activeChunks[1][2];
		m_activeChunks[2][1] = m_activeChunks[2][2];
		
		for(int y = -1; y <= 1; y++)
		{
			if(!hasBeenGenerated.containsKey(new Vector2(centreChunk.x + y, centreChunk.y - 1)))
				m_activeChunks[y + 1][2] = m_worldGenerator.generateChunk((int)centreChunk.x +y, (int)centreChunk.y - 1, worldSeed);
		}		
	}
	
	private void moveUp(int i) {
		m_activeChunks[0][2] = m_activeChunks[0][1];
		m_activeChunks[1][2] = m_activeChunks[1][1];
		m_activeChunks[2][2] = m_activeChunks[2][1];
		
		m_activeChunks[0][1] = m_activeChunks[0][0];
		m_activeChunks[1][1] = m_activeChunks[1][0];
		m_activeChunks[2][1] = m_activeChunks[2][0];
	

		
		for(int y = -1; y <= 1; y++)
		{
			if(!hasBeenGenerated.containsKey(new Vector2(centreChunk.x + y, centreChunk.y + 1)))
				m_activeChunks[y+1][0] = m_worldGenerator.generateChunk((int)centreChunk.x +y, (int)centreChunk.y + 1, worldSeed);
		}		
	}
	
	

	public void render()
	{
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				m_activeChunks[x][y].render(x-1, y-1);
			}
		}
	}
	
	public void renderUI() 
	{
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				m_activeChunks[x][y].renderUI(x-1, y-1);
			}
		}
	}

	/**
	 * @return
	 */
	public Array<Polygon> getCollisionList() {

		return worldCollisions;
	}
}
