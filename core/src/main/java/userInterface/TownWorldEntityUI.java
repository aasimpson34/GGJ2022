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
		
		
		
		String TownNameText = "Temp Name";
		renderLabel(TownNameText, getOrigin().x - 375, getOrigin().y + 260, 750, Color.RED, 3, 1);
		
		renderWindow(getOrigin().x - 375, getOrigin().y + 200, 750, 10);
		renderLabel("Hated", getOrigin().x - 375, getOrigin().y + 175, 100, Color.WHITE, 1, -1);
		renderLabel("Neutral", getOrigin().x - 375, getOrigin().y + 175, 750, Color.WHITE, 1, 1);
		renderLabel("Friendly", getOrigin().x + 275, getOrigin().y + 175, 100, Color.WHITE, 1, 0);
		
		float barPositionX = mapValueToRange(townEntity.getReputation(), 0, 100, getOrigin().x - 375, getOrigin().x + 375);
		renderWindow(barPositionX, getOrigin().y + 197, 5, 15);
		
		float xCol = getOrigin().x + 415;
		String populationText = "Population: " + townEntity.getPopulation();
		renderLabel(populationText, xCol, getOrigin().y + 170, 150, Color.BLACK, 1.5f, -1);
		renderLabel("RESOURCES", xCol, getOrigin().y + 135, 150, Color.BLACK, 1.5f, -1);
		
		TownResources[] resources = townEntity.getAllResources();
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			String resourceText = el.getResourceType().toString() + ": " + el.getAmount() + "/" + el.getResourceLimit();
			renderLabel(resourceText, xCol, (getOrigin().y + 110) - (i * 25), 150, Color.BLACK, 1, -1);
		}
		
		renderLabel("WORKERS", xCol, getOrigin().y - 5, 150, Color.BLACK, 1.5f, -1);
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			String resourceText = el.getResourceType().toString() + ": " + el.getWorkers();
			renderLabel(resourceText, xCol, (getOrigin().y - 30) - (i * 25), 150, Color.BLACK, 1, -1);
		}
	}
	
	private float mapValueToRange(float value, float currentMin, float currentMax, float targetMin, float targetMax) {
		return targetMin + (targetMax - targetMin) * (value - currentMin) / (currentMax - currentMin);
	}

}
