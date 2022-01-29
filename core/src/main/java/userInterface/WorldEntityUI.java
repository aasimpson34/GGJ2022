/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: WorldEntityUI.java
 * Purpose of class: 
 *
 * Written by @author
 */
package userInterface;

/**
 * @author aasim
 *
 */
public abstract class WorldEntityUI {

	public abstract void update();
	public abstract void render();
	
	public void renderWindow(int x, int y, int width, int height)
	{
		//have a look at potentially using a nine-patch texture.
	}
	
	public boolean renderButton(String button_label, int x, int y, int width, int height)
	{
		//If pressed return true.
		return false;
	}
	
	public void renderLabel(String label, int x, int y)
	{
		
	}
}
