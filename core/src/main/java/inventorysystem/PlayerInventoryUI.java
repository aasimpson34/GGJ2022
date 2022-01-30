/**
 * Project Name: GGJ2022-core 
 * Created On: 30 Jan 2022
 * file: PlayerInventoryUI.java
 * Purpose of class: 
 *
 * Written by @author
 */
package inventorysystem;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import assetmanager.ResourceLookup;
import camera.UICamera;
import renderer.GameRenderer;
import userInterface.WorldEntityUI;

/**
 * @author aasim
 *
 */
public class PlayerInventoryUI extends WorldEntityUI{

	AtlasRegion[] m_regions = new AtlasRegion[RESOURCE_TYPES.E_MAX_COUNT.getValue()];
	BitmapFont m_font;
	/**
	 *  
	 */
	public PlayerInventoryUI() {
		TextureAtlas m_atlas = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas");
		for(int x = 0; x < RESOURCE_TYPES.E_MAX_COUNT.getValue(); x++)
		{
			m_regions[x] = m_atlas.findRegion("Resource", x+1);
		}
		m_font = ResourceLookup.getInstance().getFont("font30.ttf");
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
	
	}

	/**
	 * @param m_playerInventorySystem
	 */
	public void render(InventorySystem m_playerInventorySystem) {
		SpriteBatch batch = GameRenderer.getInstance().getBatch();
		batch.flush();
		batch.setProjectionMatrix(UICamera.getInstance().getProjection());
		int origin = 1100;
		int size = 196;
		
		for(int x = 0; x < RESOURCE_TYPES.E_MAX_COUNT.getValue(); x++)
		{
			
		
			batch.draw(m_regions[x], origin + x * size, 1012);
			m_font.draw(batch, RESOURCE_TYPES.get(x).getName(), origin + x * size + 64, 1075);
			m_font.draw(batch, m_playerInventorySystem.getResourceCount(RESOURCE_TYPES.get(x)) + "", origin + x * size + 64, 1045);

		}		
	}

}
