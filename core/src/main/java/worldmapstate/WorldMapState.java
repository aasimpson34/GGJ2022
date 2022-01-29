package worldmapstate;

import camera.GameCamera;
import entity.GameObjectEntityHandler;
import player.PlayerEntity;
import renderer.GameRenderer;
import states.GameState;
import townentity.TownEntity;
import towngeneration.TownGeneratorHandler;
import worldgeneration.WorldGenerator;
import worldmap.WorldChunk;

public class WorldMapState implements GameState {
	
	WorldChunk m_debugWorldChunk;
	TownEntity debugTown;
	PlayerEntity m_playerEntity;
	
	public WorldMapState() {
		GameRenderer.getInstance();
		GameCamera.getInstance();
		m_debugWorldChunk = new WorldGenerator().generateChunk(0, 0, System.currentTimeMillis());
		
		//Add the player entity to the object pool.
		m_playerEntity = new PlayerEntity();
		GameObjectEntityHandler.getInstance().addGameObject(m_playerEntity);
		
		TownGeneratorHandler townGenerator = new TownGeneratorHandler();
		debugTown = townGenerator.generateNewTown(0, 0);
		//debugTown.debugOverview();
		
		debugTown.update();
		//debugTown.debugOverview();
		
		debugTown.update();
		//debugTown.debugOverview();
		
		debugTown.update();
		//debugTown.debugOverview();
	}
	
	@Override
	public GameState update() {
		GameObjectEntityHandler.getInstance().update();
		GameCamera.getInstance().update();
		
		m_debugWorldChunk.update();
		
		GameCamera.getInstance().moveTo(m_playerEntity.getPosition().x, m_playerEntity.getPosition().y, 0.01F);
		
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
