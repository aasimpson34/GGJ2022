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
		
		renderLabel("Trading Post", getOrigin().x - 375, getOrigin().y + 100, 750, Color.BLACK, 30, -1, true);
		for(int i = 0; i < tradeLimit; i++) {			
			renderTradeWindow(getOrigin().x - 375, getOrigin().y + 50 - (i * 60), 750, 50);
		}
	}

}
