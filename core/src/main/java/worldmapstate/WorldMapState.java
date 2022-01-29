package worldmapstate;

import assetmanager.LoadingScreen;
import camera.GameCamera;
import entity.GameObjectEntityHandler;
import renderer.GameRenderer;
import states.GameState;
import worldmap.WorldChunk;

public class WorldMapState implements GameState {
	
	WorldChunk m_debugWorldChunk[] = new WorldChunk[4];
	
	public WorldMapState() {
		GameRenderer.getInstance();
		GameCamera.getInstance();
		m_debugWorldChunk[0] = new WorldChunk(0, 0);
		m_debugWorldChunk[1] = new WorldChunk(-1, 0);
		m_debugWorldChunk[2] = new WorldChunk(-1, -1);
		m_debugWorldChunk[3] = new WorldChunk(0, -1);
		
	}
	
	@Override
	public GameState update() {

		GameObjectEntityHandler.getInstance().update();
		for(int x = 0; x < 4 ;x++)
			m_debugWorldChunk[x].update();
		
		return this;
	}

	@Override
	public void render() {
		GameRenderer.getInstance().getBatch().setProjectionMatrix(GameCamera.getInstance().getProjection());
		GameRenderer.getInstance().getBatch().begin();
		
		for(int x = 0; x < 4 ;x++)
			m_debugWorldChunk[x].render();
		
		GameObjectEntityHandler.getInstance().render();
		
		GameRenderer.getInstance().getBatch().end();
	}

	@Override
	public void onExit() {

	}

	@Override
	public void onEntry() {

	}

}
