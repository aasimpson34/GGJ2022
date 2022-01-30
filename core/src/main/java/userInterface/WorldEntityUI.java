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
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import assetmanager.ResourceLookup;
import camera.GameCamera;
import inventorysystem.RESOURCE_TYPES;
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
	private TownEntity townEntity;
	
	public abstract void update();
	public abstract void render();
	
	public void setIsShowing(boolean x) { this.isShowing = x; }
	public boolean getIsShowing() { return this.isShowing; }
	
	public void renderWindow(float x, float y, float width, float height)
	{
		batch = GameRenderer.getInstance().getBatch();
		
		
		NinePatch background = new NinePatch(new Texture(Gdx.files.internal("user_interface/window_9patch.png")), 10, 10, 10, 10);
		background.draw(batch, x, y, width, height);
	}
	
	public boolean renderButton(String button_label, float x, float y, float width, float height)
	{
		batch = GameRenderer.getInstance().getBatch();
		
		NinePatch button = new NinePatch(new Texture(Gdx.files.internal("user_interface/button_9patch.png")), 10, 10, 10, 10);
		button.draw(batch, x, y, width, height);
		renderLabel(button_label, x, y + (height / 2) - 8, width, Color.BLACK, 1.5f, 1, true);
		
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
	
	public void renderIcon(int type, float x, float y, float width, float height) {
		TextureAtlas atlas = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas");
		AtlasRegion icon = atlas.findRegion("Resource", type);
		batch.draw(icon, x, y, width, height);
	}
	
	public void renderLabel(String label, float x, float y, float width, Color colour, float scale, int align, boolean gameFont)
	{
		
		batch = GameRenderer.getInstance().getBatch();
		BitmapFont font = new BitmapFont();
		if(gameFont) {			
			font = ResourceLookup.getInstance().getFont("font.ttf");
		}
		font.setColor(colour);
		float lineHeight = font.getData().lineHeight;
		//font.getData().setScale(scale);
		font.draw(batch, label, x, y + lineHeight, width, align, false);
	}
	
	public void setOriginPoints() {
		GameCamera camera = GameCamera.getInstance();
		Vector2 cameraPos = camera.getPosition();
		this.originPoint = cameraPos;
	}
	public Vector2 getOrigin() { return this.originPoint; }
	public void setTownEntity(TownEntity town) { this.townEntity = town; }
	public TownEntity getTownEntity() { return this.townEntity; }
}
