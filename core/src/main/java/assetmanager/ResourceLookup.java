/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: ResoucreLookup.java
 * Purpose of class: 
 *
 * Written by @author
 */
package assetmanager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * @author aasim
 *
 */
public class ResourceLookup {
	static ResourceLookup m_instance;
	
	/**
	 * Get the instance of the resource look up table.
	 * @return
	 */
	public static ResourceLookup getInstance()
	{
		if(m_instance == null)
			m_instance = new ResourceLookup();
		
		return m_instance;
	}
	
	AssetManager m_assetManager;
	
	public void setAssetManager(AssetManager assetMan)
	{
		m_assetManager = assetMan;
	}
	
	public TextureAtlas getTextureAtlas(String string) {
		return m_assetManager.get(string, TextureAtlas.class);
	}
	
	public BitmapFont getFont(String string) {
		return m_assetManager.get(string, BitmapFont.class);
	}
}
