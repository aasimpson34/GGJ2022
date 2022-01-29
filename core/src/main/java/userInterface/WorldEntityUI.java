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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import renderer.GameRenderer;
import townentity.TownEntity;

/**
 * @author aasim
 *
 */
public abstract class WorldEntityUI {

	private SpriteBatch batch;
	
	public abstract void update();
	public abstract void render();
	
	public void renderWindow(int x, int y, int width, int height, int textureType)
	{
		batch = GameRenderer.getInstance().getBatch();
		
		Texture background; 
		
		switch(textureType) {
			case 1:
				background = new Texture(Gdx.files.internal("user_interface/debug_white.png"));
				break;
			case 2:
				background = new Texture(Gdx.files.internal("user_interface/debug_red.png"));
				break;
			default:
				background = new Texture(Gdx.files.internal("user_interface/debug_black.png"));
				break;
		}
		
		
		batch.draw(background, x, y, width, height);
	}
	
	public boolean renderButton(String button_label, int x, int y, int width, int height)
	{
		batch = GameRenderer.getInstance().getBatch();
		Texture button = new Texture(Gdx.files.internal("user_interface/debug_white.png"));
		batch.draw(button, x, y, width, height);
		
		Color colour = Color.RED;
		renderLabel(button_label, x, y + (height / 2) - 5, width, colour, 2, 1);
		
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.input.getY();
			if(mouseX >= x && mouseX <= x + width) {
				if(mouseY > y && mouseY <= y + height) {
					return true;
				}
			}
	    }
		
		return false;
	}
	
	public void renderLabel(String label, int x, int y, int width, Color colour, int scale, int align)
	{
		batch = GameRenderer.getInstance().getBatch();
		BitmapFont font = new BitmapFont();
		font.setColor(colour);
		float lineHeight = font.getData().lineHeight;
		font.getData().setScale(scale);
		font.draw(batch, label, x, y + lineHeight, width, align, false);
	}
	
	public TownEntity townEntity;
	public void setTownEntity(TownEntity town) { this.townEntity = town; }
	public TownEntity getTownEntity() { return this.townEntity; }
}
