/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: TownWorldEntityUI.java
 * Purpose of class: 
 *
 * Written by @author
 */
package userInterface;

import com.badlogic.gdx.graphics.Color;

import townentity.TownEntity;
import townentity.TownResources;

/**
 * @author Cullen Willis
 *
 */
public class TownWorldEntityUI extends WorldEntityUI {
	
	@Override
	public void update() {
	}

	@Override
	public void render() {
		setOriginPoints();
		
		renderWindow(getOrigin().x - 400, getOrigin().y - 300, 800, 600);
		renderWindow(getOrigin().x + 400, getOrigin().y - 200, 200, 400);
		renderButton("Engage Battle", getOrigin().x - 375, getOrigin().y - 275, 750, 50);
		
		TownEntity townEntity = getTownEntity();
		if(townEntity == null) {
			return;
		}
		
		String TownNameText = townEntity.getTownName();
		renderLabel(TownNameText, getOrigin().x - 375, getOrigin().y + 250, 750, Color.BLACK, 30, 1, true);
		
		renderWindow(getOrigin().x - 375, getOrigin().y + 200, 750, 10);
		renderLabel("Hated", getOrigin().x - 375, getOrigin().y + 175, 100, Color.WHITE, 20, -1, false);
		renderLabel("Neutral", getOrigin().x - 375, getOrigin().y + 175, 750, Color.WHITE, 20, 1, false);
		renderLabel("Friendly", getOrigin().x + 275, getOrigin().y + 175, 100, Color.WHITE, 20, 0, false);
		
		float barPositionX = mapValueToRange(townEntity.getReputation(), 0, 100, getOrigin().x - 375, getOrigin().x + 375);
		renderWindow(barPositionX, getOrigin().y + 197, 5, 15);
		
		float xCol = getOrigin().x + 415;
		String populationText = "Population: " + townEntity.getPopulation();
		renderLabel(populationText, xCol, getOrigin().y + 170, 150, Color.BLACK, 20, -1, false);
		renderLabel("RESOURCES", xCol, getOrigin().y + 135, 150, Color.BLACK, 20, -1, true);
		
		TownResources[] resources = townEntity.getAllResources();
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			float gapSize = 30;
			float posY = getOrigin().y + 110 - (i * gapSize);
			
			String resourceText = el.getAmount() + "/" + el.getResourceLimit();
			renderLabel(resourceText, xCol + 30, posY, 150, Color.BLACK, 20, -1, false);
			renderIcon(el.getResourceType().getValue() + 1, xCol, posY, 25, 25);
		}
		
		renderLabel("WORKERS", xCol, getOrigin().y - 25, 150, Color.BLACK, 20, -1, true);
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			float gapSize = 30;
			float posY = getOrigin().y - 50 - (i * gapSize);
			
			String resourceText = el.getWorkers() + "";
			renderLabel(resourceText, xCol + 30, posY, 150, Color.BLACK, 20, -1, false);
			renderIcon(el.getResourceType().getValue() + 1, xCol, posY, 25, 25);
		}
	}
	
	private float mapValueToRange(float value, float currentMin, float currentMax, float targetMin, float targetMax) {
		return targetMin + (targetMax - targetMin) * (value - currentMin) / (currentMax - currentMin);
	}

}
