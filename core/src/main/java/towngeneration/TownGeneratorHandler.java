package towngeneration;

import java.util.HashSet;
import java.util.Set;

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
		
		String townName = getRandomName();
		town.setTownName(townName);
		
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
			int resourceTime = getResourceTime(townLevel.getValue());			
			int resourceRate = getResourceRate(townLevel.getValue());
			boolean isMainResource = false;
			
			if(type == townMainResource) {
				isMainResource = true;
				resourceLimit += 25;
				resourceRate *= 2;
			}
			
			town.setIsMainResource(type, isMainResource);
			town.setResourceLimit(type, resourceLimit);
			town.setResourceTime(type, resourceTime);
			town.setResourceCurrentTime(type, resourceTime);
			town.setResourceRate(type, resourceRate);
		}
		
		return town;
	}
	
	private Set<String> identifiers = new HashSet<String>();
	private String getRandomName() {
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
		java.util.Random rand = new java.util.Random();
		
		StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
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
