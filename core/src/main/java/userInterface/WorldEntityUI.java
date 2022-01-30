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
import com.badlogic.gdx.math.Vector2;

import camera.GameCamera;
import renderer.GameRenderer;
import townentity.TownEntity;

/**
 * @author aasim
 *
 */
public abstract class WorldEntityUI {

	private SpriteBatch batch;
	private boolean isShowing = false;
	private Vector2 originPoint = new Vector2(0, 0);
	
	public abstract void update();
	public abstract void render();
	
	public void setIsShowing(boolean x) { this.isShowing = x; }
	public boolean getIsShowing() { return this.isShowing; }
	
	public void renderWindow(float x, float y, float width, float height, int textureType)
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
	
	public boolean renderButton(String button_label, float x, float y, float width, float height)
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
	
	public void renderLabel(String label, float x, float y, float width, Color colour, int scale, int align)
	{
		batch = GameRenderer.getInstance().getBatch();
		BitmapFont font = new BitmapFont();
		font.setColor(colour);
		float lineHeight = font.getData().lineHeight;
		font.getData().setScale(scale);
		font.draw(batch, label, x, y + lineHeight, width, align, false);
	}
	
	public void setOriginPoints() {
		GameCamera camera = GameCamera.getInstance();
		Vector2 cameraPos = camera.getPosition();
		this.originPoint = cameraPos;
	}
	public Vector2 getOrigin() { return this.originPoint; }
	public TownEntity townEntity;
	public void setTownEntity(TownEntity town) { this.townEntity = town; }
	public TownEntity getTownEntity() { return this.townEntity; }
}
