package entity;

import com.badlogic.gdx.utils.Array;

public class GameObjectEntityHandler {

	static GameObjectEntityHandler m_instance;
	
	/**
	 * Get instance of the game object entity handler
	 * @return
	 */
	public static GameObjectEntityHandler getInstance()
	{
		if(m_instance == null)
			m_instance = new GameObjectEntityHandler();
		return m_instance;
	}
	
	Array<GameObjectEntity> m_gameObjects;
	
	public GameObjectEntityHandler()
	{
		m_gameObjects = new Array<GameObjectEntity>();
	}
	
	public void addGameObject(GameObjectEntity entityToAdd)
	{
		m_gameObjects.add(entityToAdd);
	}
	
	public void update()
	{
		Array<GameObjectEntity> m_listOfObjectsToDelete = new Array<GameObjectEntity>();
		
		//Loop for each game object to update the entity
		for(GameObjectEntity entity : m_gameObjects)
		{
			if(entity.update())
			{
				m_listOfObjectsToDelete.add(entity);
			}
		}
		
		//loop for any game objects marked for deletion
		for(GameObjectEntity entity : m_listOfObjectsToDelete)
		{
			m_gameObjects.removeValue(entity, true);
		}
		
		//clear the list of objects to delete (i don't know if this should become a member variable)
		m_listOfObjectsToDelete.clear();
		
	}
	
	public void render()
	{
		//Loop for the number of game objects and render them.
		for(GameObjectEntity entity : m_gameObjects)
		{
			entity.render();
		}
	}
}
