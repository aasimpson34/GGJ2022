/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: loadingscreen.java
 * Purpose of class: 
 *
 * Written by @author
 */
package assetmanager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import renderer.GameRenderer;

/**
 * @author aasim
 *
 */
public class LoadingScreen{
	
	BitmapFont temporaryBitmapFont; //TODO: remove me.
	
	AssetManager m_assetManager;
	
	float m_loadProgress = 0.0f;

	public LoadingScreen(){
		m_assetManager = new AssetManager();
		temporaryBitmapFont = new BitmapFont();
		
		m_assetManager.load("world_atlas.atlas",TextureAtlas.class);
		m_assetManager.load("player.atlas",TextureAtlas.class);

	}
	
	public boolean update(){
		m_loadProgress = m_assetManager.getProgress();
		
 		if(m_assetManager.update()){
			
			ResourceLookup.getInstance().setAssetManager(m_assetManager);
			return true;
		}
		
		return false;
	}
	
	public void render(){
		temporaryBitmapFont.setColor(Color.BLACK);
		temporaryBitmapFont.draw(GameRenderer.getInstance().getBatch(),"Loading..", 0, 0 );
	}
	
	public float getProgress() {
		return m_loadProgress;
	}
	

}
