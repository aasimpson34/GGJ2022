/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: LoadGameAssetsState.java
 * Purpose of class: 
 *
 * Written by @author
 */
package assetmanager;

import camera.GameCamera;
import entity.GameObjectEntityHandler;
import renderer.GameRenderer;
import states.GameState;
import worldmapstate.WorldMapState;

/**
 * @author aasim
 *
 */
public class LoadGameAssetsState implements GameState{

	LoadingScreen m_loadingScreenInstance;
	
	/**
	 * 
	 */
	public LoadGameAssetsState() {
		m_loadingScreenInstance = new LoadingScreen();
	}
	
	@Override
	public GameState update() {
		if(m_loadingScreenInstance.update())
		{
			return new WorldMapState();
		}
		return this;
	}

	@Override
	public void render() {
		GameRenderer.getInstance().getBatch().setProjectionMatrix(GameCamera.getInstance().getProjection());
		GameRenderer.getInstance().getBatch().begin();
		
		m_loadingScreenInstance.render();
		
		GameRenderer.getInstance().getBatch().end();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntry() {
		// TODO Auto-generated method stub
		
	}

}
