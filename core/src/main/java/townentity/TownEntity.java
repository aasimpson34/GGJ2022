package townentity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import assetmanager.ResourceLookup;
import inventorysystem.RESOURCE_TYPES;
import renderer.GameRenderer;
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
		
		int maxResources = RESOURCE_TYPES.E_MAX_COUNT.getValue();
		resources = new TownResources[maxResources];
	}
	
	public void render() {
		renderTown();
	}
	public void renderTown() {
		int townId = this.townType.getValue() + 1;
		SpriteBatch batch = GameRenderer.getInstance().getBatch();
		AtlasRegion town = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("town", townId);
		batch.draw(town, this.positionX, this.positionY);
	}

	public boolean update() {
		increasePopulation(this.populationSpeed);
		decreaseReputation(this.reputationSpeed);
		//increaseResources();
		
		return true;
	}
	
	public void increaseResources() {
		for(int i = 0; i < this.resources.length; i++) {
			TownResources resource = this.resources[i];
			
			int currentTime = resource.getCurrentTime();
			resource.setCurrentTime(currentTime - 100);
			
			if(resource.getCurrentTime() <= 0) {
				resource.setAmount(resource.getResourceRate());
				resource.setCurrentTime(resource.getResourceTime());
			}
			
			this.resources[i] = resource;
		}
	}
	
	public void increasePopulation(int x) {
		setPopulation(this.population + x);
	}
	public void decreasePopulation(int x) {
		setPopulation(this.population - x);
	}
	
	public void increaseReputation(int x) {
		setReputation(this.reputation + x);
	}
	public void decreaseReputation(int x) {
		setReputation(this.reputation - x);
	}
	
	// * INI - Resource Management
	public void createResources(RESOURCE_TYPES type, int x) {
		TownResources resource = new TownResources(type, x);
		this.resources[type.getValue()] = resource;
	}
	
	public void setWorkers(RESOURCE_TYPES search, int x) {
		int id = search.getValue();
		this.resources[id].setWorkers(x);
	}
	public int getWorkers(RESOURCE_TYPES search) {
		int id = search.getValue();
		return this.resources[id].getWorkers();
	}
	
	public void setResourceLimit(RESOURCE_TYPES search, int x) {
		int id = search.getValue();
		this.resources[id].setResourceLimit(x);
	}
	public int getResourceLimit(RESOURCE_TYPES search) {
		int id = search.getValue();
		return this.resources[id].getResourceLimit();
	}
	
	public void setResourceTime(RESOURCE_TYPES search, int x) {
		int id = search.getValue();
		this.resources[id].setResourceTime(x);
	}
	public int getResourceTime(RESOURCE_TYPES search) {
		int id = search.getValue();
		return this.resources[id].getResourceTime();
	}
	
	public void setResourceCurrentTime(RESOURCE_TYPES search, int x) {
		int id = search.getValue();
		this.resources[id].setCurrentTime(x);
	}
	public int getResourceCurrentTime(RESOURCE_TYPES search) {
		int id = search.getValue();
		return this.resources[id].getCurrentTime();
	}
	
	public void setResourceRate(RESOURCE_TYPES search, int x) {
		int id = search.getValue();
		this.resources[id].setResourceRate(x);
	}
	public int getResourceRate(RESOURCE_TYPES search) {
		int id = search.getValue();
		return this.resources[id].getResourceRate();
	}
	
	public void setResourceAmount(RESOURCE_TYPES search, int amount) {
		int id = search.getValue();
		this.resources[id].setAmount(amount);
	}
	public TownResources getResource(RESOURCE_TYPES search) {
		int id = search.getValue();
		return this.resources[id];
	}
	public TownResources[] getAllResources() { return this.resources; }
	// * END - Resource Management
	
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
    
    public void debugOverview() {
    	System.out.println("\n#INI# Town Entity - Overview #INI#");
    	System.out.println("positionX: " + this.positionX);
    	System.out.println("positionY: " + this.positionY);
    	System.out.println("population: " + this.population + "/" + this.populationLimit);
    	System.out.println("reputation: " + this.reputation);
    	System.out.println("resourseType: " + this.mainResource.toString());
    	System.out.println("townType: " + this.townType.toString());
    	System.out.println("resourceStock - " + this.resources[0].getResourceType().toString() + ": " + this.resources[0].getAmount() + "/" + this.resources[0].getResourceLimit());
    	System.out.println("resourceStock - " + this.resources[1].getResourceType().toString() + ": " + this.resources[1].getAmount() + "/" + this.resources[1].getResourceLimit());
    	System.out.println("resourceStock - " + this.resources[2].getResourceType().toString() + ": " + this.resources[2].getAmount() + "/" + this.resources[2].getResourceLimit());
    	System.out.println("resourceStock - " + this.resources[3].getResourceType().toString() + ": " + this.resources[3].getAmount() + "/" + this.resources[3].getResourceLimit());
    	System.out.println("resourceRate - " + this.resources[0].getResourceType().toString() + ": " + this.resources[0].getResourceRate());
    	System.out.println("resourceRate - " + this.resources[1].getResourceType().toString() + ": " + this.resources[1].getResourceRate());
    	System.out.println("resourceRate - " + this.resources[2].getResourceType().toString() + ": " + this.resources[2].getResourceRate());
    	System.out.println("resourceRate - " + this.resources[3].getResourceType().toString() + ": " + this.resources[3].getResourceRate());
    	System.out.println("#END# Town Entity - Overview #END#\n");
    }
}
