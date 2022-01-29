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
	
	private TownEntity townEntity;
	@Override
	public void update() {
		townEntity = getTownEntity();
	}

	@Override
	public void render() {
		renderWindow(0, 0, 800, 600, 0);
		renderWindow(800, 100, 200, 400, 0);
		renderButton("Engage Battle", 25, 25, 750, 50);
		
		if(this.townEntity == null) {
			return;
		}
		
		String populationText = "Population: " + this.townEntity.getPopulation();
		renderLabel(populationText, 825, 475, 150, Color.RED, 1, -1);
		
		String TownNameText = "Temp Name";
		renderLabel(TownNameText, 25, 560, 750, Color.RED, 3, 1);
		
		renderWindow(25, 500, 750, 10, 1);
		renderLabel("Hated", 25, 475, 100, Color.WHITE, 1, -1);
		renderLabel("Neutral", 25, 475, 750, Color.WHITE, 1, 1);
		renderLabel("Friendly", 675, 475, 100, Color.WHITE, 1, 0);
		
		int barPositionX = mapValueToRange(this.townEntity.getReputation(), 0, 100, 25, 775);
		renderWindow(barPositionX, 498, 5, 14, 2);
		
		TownResources[] resources = this.townEntity.getAllResources();
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			String resourceText = el.getResourceType().toString() + ": " + el.getAmount() + "/" + el.getResourceLimit();
			renderLabel(resourceText, 825, 450 - (i * 25), 150, Color.RED, 1, -1);
		}
		
		
	}
	
	private int mapValueToRange(int value, int currentMin, int currentMax, int targetMin, int targetMax) {
		return targetMin + (targetMax - targetMin) * (value - currentMin) / (currentMax - currentMin);
	}

}
