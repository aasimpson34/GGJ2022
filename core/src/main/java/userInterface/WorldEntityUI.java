/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: WorldEntityUI.java
 * Purpose of class: 
 *
 * Written by @author
 */
package userInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import renderer.GameRenderer;
import townentity.TownEntity;

/**
 * @author aasim
 *
 */
public abstract class WorldEntityUI {

	public abstract void update();
	public abstract void render();
	
	public void renderWindow(int x, int y, int width, int height)
	{
		SpriteBatch batch = GameRenderer.getInstance().getBatch();
		
		Texture background = new Texture(Gdx.files.internal("user_interface/debug_black.png"));
		
		batch.draw(background, x, y, width, height);
	}
	
	public boolean renderButton(String button_label, int x, int y, int width, int height)
	{
		//If pressed return true.
		return false;
	}
	
	public void renderLabel(String label, int x, int y)
	{
		
	}
	
	public TownEntity townEntity;
	public void setTownEntity(TownEntity town) { this.townEntity = town; }
	public TownEntity getTownEntity() { return this.townEntity; }
}
