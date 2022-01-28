package worldmapstate;

import entity.GameObjectEntityHandler;
import states.GameState;

public class WorldMapState implements GameState {
	
	
	public WorldMapState() {
		
	}
	
	@Override
	public GameState update() {

		GameObjectEntityHandler.getInstance().update();
		
		return this;
	}

	@Override
	public void render() {
		GameObjectEntityHandler.getInstance().render();

	}

	@Override
	public void onExit() {

	}

	@Override
	public void onEntry() {

	}

}
