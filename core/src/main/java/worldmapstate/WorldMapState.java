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
import worldmap.WorldMap;

public class WorldMapState implements GameState {
	
	WorldMap m_worldMap;
	TownEntity debugTown;
	PlayerEntity m_playerEntity;
	
	public WorldMapState() {
		GameRenderer.getInstance();
		GameCamera.getInstance();
		m_worldMap = new WorldMap();
		
		//Add the player entity to the object pool.
		m_playerEntity = new PlayerEntity();
		GameObjectEntityHandler.getInstance().addGameObject(m_playerEntity);
		
		TownGeneratorHandler townGenerator = new TownGeneratorHandler();
	}
	
	@Override
	public GameState update() {
		GameObjectEntityHandler.getInstance().update();
		GameCamera.getInstance().update();
		
	
		
		GameCamera.getInstance().moveTo(m_playerEntity.getPosition().x, m_playerEntity.getPosition().y, 0.01F);
		m_worldMap.update(m_playerEntity);
		return this;
	}

	@Override
	public void render() {
		GameRenderer.getInstance().getBatch().setProjectionMatrix(GameCamera.getInstance().getProjection());
		GameRenderer.getInstance().getBatch().begin();
		m_worldMap.render();
	
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
