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
import com.badlogic.gdx.math.Vector2;

import camera.GameCamera;
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
		
		renderWindow(getOrigin().x - 400, getOrigin().y - 300, 800, 600, 0);
		renderWindow(getOrigin().x + 400, getOrigin().y - 200, 200, 400, 0);
		renderButton("Engage Battle", getOrigin().x - 375, getOrigin().x - 275, 750, 50);
		
		TownEntity townEntity = getTownEntity();
		if(townEntity == null) {
			return;
		}
		
		String populationText = "Population: " + townEntity.getPopulation();
		renderLabel(populationText, getOrigin().x + 425, getOrigin().y + 175, 150, Color.RED, 1, -1);
		
		String TownNameText = "Temp Name";
		renderLabel(TownNameText, getOrigin().x - 375, getOrigin().y + 260, 750, Color.RED, 3, 1);
		
		renderWindow(getOrigin().x - 375, getOrigin().y + 200, 750, 10, 1);
		renderLabel("Hated", getOrigin().x - 375, getOrigin().y + 175, 100, Color.WHITE, 1, -1);
		renderLabel("Neutral", getOrigin().x - 375, getOrigin().y + 175, 750, Color.WHITE, 1, 1);
		renderLabel("Friendly", getOrigin().x + 375, getOrigin().y + 175, 100, Color.WHITE, 1, 0);
		
		float barPositionX = mapValueToRange(townEntity.getReputation(), 0, 100, getOrigin().x - 375, getOrigin().x + 375);
		renderWindow(barPositionX, getOrigin().y + 195, 5, 14, 2);
		
		TownResources[] resources = townEntity.getAllResources();
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			String resourceText = el.getResourceType().toString() + ": " + el.getAmount() + "/" + el.getResourceLimit();
			renderLabel(resourceText, getOrigin().x + 425, (getOrigin().y + 150) - (i * 25), 150, Color.RED, 1, -1);
		}
	}
	
	private float mapValueToRange(float value, float currentMin, float currentMax, float targetMin, float targetMax) {
		return targetMin + (targetMax - targetMin) * (value - currentMin) / (currentMax - currentMin);
	}

}
