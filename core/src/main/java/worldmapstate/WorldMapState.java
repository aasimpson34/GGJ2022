package worldmapstate;

import assetmanager.LoadingScreen;
import camera.GameCamera;
import entity.GameObjectEntityHandler;
import renderer.GameRenderer;
import states.GameState;

public class WorldMapState implements GameState {
	
	LoadingScreen loadingScreen;
	
	public WorldMapState() {
		GameRenderer.getInstance();
		GameCamera.getInstance();
		loadingScreen = new LoadingScreen();
	}
	
	@Override
	public GameState update() {

		GameObjectEntityHandler.getInstance().update();
		loadingScreen.update();
		return this;
	}

	@Override
	public void render() {
		GameRenderer.getInstance().getBatch().setProjectionMatrix(GameCamera.getInstance().getProjection());
		GameRenderer.getInstance().getBatch().begin();
		
		GameObjectEntityHandler.getInstance().render();
		loadingScreen.render();
		
		GameRenderer.getInstance().getBatch().end();
	}

	@Override
	public void onExit() {

	}

	@Override
	public void onEntry() {

	}

}
