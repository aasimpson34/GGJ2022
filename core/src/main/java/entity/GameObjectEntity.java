package entity;

public abstract class GameObjectEntity {

	/**
	 * Calls the render functionality of the sprite batch
	 */
	public abstract void render();
	
	
	/**
	 * Renders any UI specific to the game object (could be more elegant but we're running out of time)
	 */
	public abstract void renderUI();
	
	/**
	 * 
	 * @return whether the game object is alive
	 */
	public abstract boolean update();
	
}
