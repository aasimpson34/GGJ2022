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
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

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
		
		FileHandleResolver resolver = new InternalFileHandleResolver();
		m_assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		m_assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
	    FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	    params.fontFileName = "font.ttf";
	    params.fontParameters.size = 30;
	    m_assetManager.load("font30.ttf", BitmapFont.class, params);
	    
	    FreetypeFontLoader.FreeTypeFontLoaderParameter params2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	    params2.fontFileName = "font.ttf";
	    params2.fontParameters.size = 20;
	    m_assetManager.load("font20.ttf", BitmapFont.class, params2);

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
