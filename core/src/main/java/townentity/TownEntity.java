package townentity;

import inventorysystem.RESOURCE_TYPES;
import towngeneration.TOWN_TYPES;

/**
 * 
 * @author cullen
 *
 * TODO: 
 * + TEST :(
 * + create resources on start
 * + calculate time on each resource
 *  
 */

public class TownEntity {
	private int population;
	private int populationLimit;
	private int reputation;
	private RESOURCE_TYPES mainResource;
	private TOWN_TYPES townType;
	
	private int reputationMax = 100;
	private int populationSpeed = 1;
	private int reputationSpeed = 1;
	
	private int positionX;
	private int positionY;
	
	private TownResources[] resources;
	
	public TownEntity(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	public void render() {
		System.err.println("TownEntity:render(); not implemented");
		
	}

	public boolean update() {
		// Increase population
		increasePopulation(this.populationSpeed);
		// Decrease reputation
		decreaseReputation(this.reputationSpeed);
		// Increase resources based on time
		increaseResources();
		
		return true;
	}
	
	public void increaseResources() {
		for(int i = 0; i < this.resources.length; i++) {
			TownResources resource = this.resources[i];
			
			resource.setCurrentTime(1000); // Decrease time by 1 second
			
			if(resource.getCurrentTime() == 0) {
				resource.setAmount(resource.getResourceRate());
				resource.setCurrentTime(resource.getResourceTime());
				continue;
			}	
		}
	}
	
	public void increasePopulation(int x) {
		setPopulation(this.population += x);
	}
	public void decreasePopulation(int x) {
		setPopulation(this.population -= x);
	}
	
	public void increaseReputation(int x) {
		setReputation(this.reputation += x);
	}
	public void decreaseReputation(int x) {
		setReputation(this.reputation -= x);
	}
	
	public TownResources getResource(RESOURCE_TYPES search) {
		int id = search.getValue();
		for(int i = 0; i < this.resources.length; i++) {
			TownResources resource = this.resources[i];
			RESOURCE_TYPES type = resource.getResourceType();
			
			if(type.getValue() == id) {
				return resource;
			}
		}
		
		return null;
	}
	public void setResource(RESOURCE_TYPES search, int amount) {
		int id = search.getValue();
		for(int i = 0; i < this.resources.length; i++) {
			TownResources resource = this.resources[i];
			RESOURCE_TYPES type = resource.getResourceType();
			
			if(type.getValue() == id) {
				resource.setAmount(amount);
				return;
			}
		}
	}
	
	public int getPopulation() { return this.population; }
    public void setPopulation(int x) {
    	if(x > this.populationLimit) {
    		this.population = this.populationLimit;
    		return;
    	}
    	if(x < 0) {
    		this.population = 0;
    		return;
    	}
    	
        this.population = x;
    }
    
    public int getPopulationLimit() { return this.populationLimit; }
    public void setPopulationLimit(int x) { this.populationLimit = x; }
    
    public int getPopulationSpeed() { return this.populationSpeed; }
    public void setPopulationSpeed(int x) { this.populationSpeed = x; }
    
    public int getReputation() { return this.reputation; }
    public void setReputation(int x) {
    	if(x > this.reputationMax) {
    		this.reputation = this.reputationMax;
    		return;
    	}
    	if(x < 0) {
    		this.reputation = 0;
    		return;
    	}
    	
        this.reputation = x;
    }
    
    public int getReputationSpeed() { return this.reputationSpeed; }
    public void setReputationSpeed(int x) { this.reputationSpeed = x; }
    
    public RESOURCE_TYPES getMainResource() { return this.mainResource; }
    public void setMainResource(RESOURCE_TYPES x) { this.mainResource = x; }
    
    public TOWN_TYPES getTownType() { return this.townType; }
    public void setTownType(TOWN_TYPES x) { this.townType = x; }
    
    public int getPositionX() { return this.positionX; }
    public int getPositionY() { return this.positionY; }
}
