package townentity;

import inventorysystem.RESOURCE_TYPES;
import towngeneration.TOWN_TYPES;

public class TownEntity {
	private int population;
	private int populationLimit;
	private int reputation;
	private RESOURCE_TYPES mainResource;
	private TOWN_TYPES townType;
	
	private int reputationMax = 100;
	private int reputationMin = 0;
	private int populationSpeed = 1;
	private int reputationSpeed = 1;
	
	public TownEntity() {
		setPopulation(0);
		setPopulationLimit(0);
		setReputation(0);
	}
	
	public void render() {
		System.err.println("TownEntity:render(); not implemented");
		
	}

	public boolean update() {
		increasePopulation(this.populationSpeed);
		decreaseReputation(this.reputationSpeed);
		return true;
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
	
	public int getPopulation() { return this.population; }
    public void setPopulation(int x) {
    	if(this.population > this.populationLimit) {
    		this.population = this.populationLimit;
    		return;
    	}
    	if(this.population < 0) {
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
    	if(this.reputation > this.reputationMax) {
    		this.reputation = this.reputationMax;
    		return;
    	}
    	if(this.reputation < this.reputationMin) {
    		this.reputation = this.reputationMin;
    		return;
    	}
    	
        this.populationLimit = x;
    }
    
    public int getReputationSpeed() { return this.reputationSpeed; }
    public void setReputationSpeed(int x) { this.reputationSpeed = x; }
    
    public RESOURCE_TYPES getMainResource() { return this.mainResource; }
    public void setMainResource(RESOURCE_TYPES x) { this.mainResource = x; }
    
    public TOWN_TYPES getTownType() { return this.townType; }
    public void setTownType(TOWN_TYPES x) { this.townType = x; }
}
