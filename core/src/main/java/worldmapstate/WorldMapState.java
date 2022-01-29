package worldmapstate;

import assetmanager.LoadingScreen;
import camera.GameCamera;
import entity.GameObjectEntityHandler;
import inventorysystem.RESOURCE_TYPES;
import renderer.GameRenderer;
import states.GameState;
import townentity.TownEntity;
import towngeneration.TownGeneratorHandler;
import worldgeneration.WorldGenerator;
import worldmap.WorldChunk;

public class WorldMapState implements GameState {
	
	WorldChunk m_debugWorldChunk;
	TownEntity debugTown;
	
	public WorldMapState() {
		GameRenderer.getInstance();
		GameCamera.getInstance();
		m_debugWorldChunk = new WorldGenerator().generateChunk(0, 0, System.currentTimeMillis());
		
		TownGeneratorHandler townGenerator = new TownGeneratorHandler();
		debugTown = townGenerator.generateNewTown(0, 0);
		debugTown.debugOverview();
	}
	
	@Override
	public GameState update() {
		GameObjectEntityHandler.getInstance().update();
		
		m_debugWorldChunk.update();
		
		return this;
	}

	@Override
	public void render() {
		GameRenderer.getInstance().getBatch().setProjectionMatrix(GameCamera.getInstance().getProjection());
		GameRenderer.getInstance().getBatch().begin();
		
		m_debugWorldChunk.render();
		
		debugTown.render();
		
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
