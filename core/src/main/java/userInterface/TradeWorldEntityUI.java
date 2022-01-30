package userInterface;

import com.badlogic.gdx.graphics.Color;

public class TradeWorldEntityUI extends WorldEntityUI {

	private int tradeLimit = 4;
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		setOriginPoints();
		
		renderLabel("Trading Post", getOrigin().x - 375, getOrigin().y + 115, 750, Color.RED, 2, -1);
		for(int i = 0; i < tradeLimit; i++) {			
			renderWindow(getOrigin().x - 375, getOrigin().y + 50 - (i * 60), 750, 50);
		}
	}

}
