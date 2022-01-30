package worldmapstate;

import camera.GameCamera;
import entity.GameObjectEntityHandler;
import inventorysystem.PlayerInventoryUI;
import player.PlayerEntity;
import renderer.GameRenderer;
import states.GameState;
import worldmap.WorldMap;

public class WorldMapState implements GameState {
	
	WorldMap m_worldMap;
	PlayerEntity m_playerEntity;
	
	//TownEntity debugTown;
	//WorldEntityUI townUI;
	
	public WorldMapState() {
		GameRenderer.getInstance();
		GameCamera.getInstance();
		m_worldMap = new WorldMap();
		
		//Add the player entity to the object pool.
		m_playerEntity = new PlayerEntity();
		GameObjectEntityHandler.getInstance().addGameObject(m_playerEntity);
		
		//TownGeneratorHandler townGenerator = new TownGeneratorHandler();
		//debugTown = townGenerator.generateNewTown(0, 0);
		//townUI = new TownWorldEntityUI();
	}
	
	@Override
	public GameState update() {
		GameObjectEntityHandler.getInstance().update();
		GameCamera.getInstance().update();
		
		//debugTown.update();
		//townUI.setTownEntity(debugTown);
		//townUI.update();
		
		GameCamera.getInstance().moveTo(m_playerEntity.getPosition().x, m_playerEntity.getPosition().y, 0.01F);
		m_worldMap.update(m_playerEntity);
		m_playerEntity.updateMovement(m_worldMap);
		return this;
	}

	@Override
	public void render() {
		GameRenderer.getInstance().getBatch().setProjectionMatrix(GameCamera.getInstance().getProjection());
		GameRenderer.getInstance().getBatch().begin();
		m_worldMap.render();
		GameObjectEntityHandler.getInstance().render();

		//debugTown.render(0, 0);
		//townUI.render();
		m_worldMap.renderUI();
		m_playerEntity.renderUI();

		GameRenderer.getInstance().getBatch().end();
	}

	@Override
	public void onExit() {

	}

	@Override
	public void onEntry() {

	}

}
