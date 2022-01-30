package userInterface;

import com.badlogic.gdx.graphics.Color;

import inventorysystem.RESOURCE_TYPES;

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
			
			int randomResourceB = getRandomNumber(1, 4);
			int randomAmountB = getRandomNumber(25, 75);
			int randomResourceS = getRandomNumber(1, 4);
			int randomAmountS = getRandomNumber(25, 75);
			
			int posY = (int) (getOrigin().y + 60 - (i * 60));
			
			renderIcon(randomResourceB, getOrigin().x - 350, posY, 30, 30);
			renderLabel(randomAmountB + "", getOrigin().x - 315, posY + 2, 100, Color.BLACK, 30, -1, false);
			
			renderIcon(randomResourceS, getOrigin().x + 290, posY, 30, 30);
			renderLabel(randomAmountS + "", getOrigin().x + 325, posY + 2, 100, Color.BLACK, 30, -1, false);
			
			String randomText = "-> -> Selling -> ->";
			if(i < tradeLimit / 2) {
				randomText = "<- <- Buying <- <-";
			}
			renderLabel(randomText, getOrigin().x - 375, posY + 2, 750, Color.BLACK, 30, 1, false);
		}
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}

}
