package towngeneration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import inventorysystem.RESOURCE_TYPES;
import townentity.TownEntity;

public class TownGeneratorHandler { 
	
	private JsonValue config = null;
	
	public TownEntity generateNewTown(int x, int y)
	{
		if(this.config == null) {
			this.config = loadConfig();
		}
		
		TownEntity town = new TownEntity(x, y);
		
		TOWN_TYPES townLevel = getTownLevel();
		town.setTownType(townLevel);
		
		RESOURCE_TYPES townMainResource = getTownResource();
		town.setMainResource(townMainResource);
		
		int townPopulationLimit = getTownPopulationLimit(townLevel.getValue());
		town.setPopulationLimit(townPopulationLimit);
		
		int townPopulation = getTownPopulation(townLevel.getValue());
		town.setPopulation(townPopulation);
		
		int townReputation = getTownReputation();
		town.setReputation(townReputation);
		
		int maxResources = RESOURCE_TYPES.E_MAX_COUNT.getValue();
		for(int i = 0; i < maxResources; i++) {
			RESOURCE_TYPES type = RESOURCE_TYPES.get(i);
			town.createResources(type, 0);
			
			int resourceLimit = getResourceLimit(townLevel.getValue());
			town.setResourceLimit(type, resourceLimit);
			
			int resourceTime = getResourceTime(townLevel.getValue());
			town.setResourceTime(type, resourceTime);
			town.setResourceCurrentTime(type, resourceTime);
			
			int workers = townPopulation / 3;
			town.setWorkers(type, workers);
			
			int resourceRate = getResourceRate(townLevel.getValue());
			town.setResourceRate(type, resourceRate);
		}
		
		return town;
	}
	
	private JsonValue loadConfig() {
		JsonReader json = new JsonReader();
		JsonValue base = json.parse(Gdx.files.internal("town_config.json"));
		JsonValue core = base.get("town_config");
		return core;
	}
	
	private TOWN_TYPES getTownLevel() {
		int id = getRandomNumber(0, 2);
		return TOWN_TYPES.get(id);
	}
	
	private RESOURCE_TYPES getTownResource() {
		int id = getRandomNumber(0, 4);
		return RESOURCE_TYPES.get(id);
	}
	
	private int getTownPopulationLimit(int type) {
		return this.config.get(type).getInt("populationLimit");
	}
	private int getTownPopulation(int type) {
		return this.config.get(type).getInt("startingPopulation");
	}
	private int getResourceLimit(int type) {
		return this.config.get(type).getInt("resourceLimit");
	}
	private int getResourceRate(int type) {
		return this.config.get(type).getInt("ratePerWorker");
	}
	private int getResourceTime(int type) {
		switch(type) {
			case 1:
				return 2000;
			case 2: 
				return 1000;
			default:
				return 3000;
		}
	}
	
	private int getTownReputation() {
		int chance = getRandomNumber(0, 3);
		switch(chance) {
			case 0:
				return (int) 25;
			case 3:
				return (int) 75;
			default:
				return (int) 50;
		}
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}
}
