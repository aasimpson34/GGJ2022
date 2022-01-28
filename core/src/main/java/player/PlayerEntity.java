package player;

import entity.GameObjectEntity;

public class PlayerEntity extends GameObjectEntity{

	@Override
	public void render() {
		System.err.println("PlayerEntity:render(); not implemented");
		
	}

	@Override
	public boolean update() {
		System.err.println("PlayerEntity:update(); not implemented");
		return true;
	}

}
