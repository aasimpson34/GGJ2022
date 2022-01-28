package entity;

public abstract class GameObjectEntity {

	/**
	 * Calls the render functionality of the sprite batch
	 */
	public abstract void render();
	
	/**
	 * 
	 * @return whether the game object is alive
	 */
	public abstract boolean update();
	
}
