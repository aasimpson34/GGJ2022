package townentity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import assetmanager.ResourceLookup;
import inventorysystem.RESOURCE_TYPES;
import renderer.TileRenderer;
import towngeneration.TOWN_TYPES;
import userInterface.TownWorldEntityUI;
import userInterface.TradeWorldEntityUI;
import userInterface.WorldEntityUI;

/**
 * 
 * @author cullen
 *
 */

public class TownEntity {
	private String name;
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
	
	WorldEntityUI m_worldEntityUI;
	private float lastUpdate;
	
	private WorldEntityUI townInterface;
	private WorldEntityUI tradeInterface;
	
	public TownEntity(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		
		int maxResources = RESOURCE_TYPES.E_MAX_COUNT.getValue();
		resources = new TownResources[maxResources];
		
		m_worldEntityUI = new TownWorldEntityUI();
		this.townInterface = new TownWorldEntityUI();
		this.tradeInterface = new TradeWorldEntityUI();
	}
	
	public void render(int xOffset, int yOffset) {
		int townId = this.townType.getValue() + 1;
		AtlasRegion town = ResourceLookup.getInstance().getTextureAtlas("world_atlas.atlas").findRegion("town", townId);
		TileRenderer.renderTile(town, this.positionX, this.positionY, xOffset, yOffset);
	}

	public void renderUI() {
		if(this.townInterface != null && this.townInterface.getIsShowing()) {
			this.townInterface.render();
			this.tradeInterface.render();
		}
	}
	public void updateUI() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
			this.townInterface.setIsShowing(true);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			this.townInterface.setIsShowing(false);
		}
	}
	public void forceCloseUI() {
		this.townInterface.setIsShowing(false);
	}
	public void updateEntityUI(TownEntity entity) {
		this.townInterface.setTownEntity(entity);
		this.tradeInterface.update();
	}
	
	public boolean update() {
		float updateTime = Gdx.graphics.getDeltaTime();
		lastUpdate += updateTime;
		
		if(lastUpdate >= 1) {
			increasePopulation(this.populationSpeed);
			decreaseReputation(this.reputationSpeed);
			increaseResources();
			lastUpdate = 0;
		}
		
		
		return true;
	}
	
	public void increaseResources() {
		for(int i = 0; i < this.resources.length; i++) {
			TownResources resource = this.resources[i];
			
			int currentTime = resource.getCurrentTime();
			resource.setCurrentTime(currentTime - 1000);
			
			if(resource.getCurrentTime() <= 0) {
				int currentAmount = resource.getAmount();
				resource.setAmount(currentAmount + resource.getResourceRate());
				resource.setCurrentTime(resource.getResourceTime());
			}
		}
	}
	
	public void increasePopulation(int x) {
		setPopulation(this.population + x);
		distributeWorkers();
	}
	public void decreasePopulation(int x) {
		setPopulation(this.population - x);
		distributeWorkers();
	}
	
	public void increaseReputation(int x) {
		setReputation(this.reputation + x);
	}
	public void decreaseReputation(int x) {
		setReputation(this.reputation - x);
	}
	
	private void distributeWorkers() {
		for(int i = 0; i < resources.length; i++) {
			TownResources el = resources[i];
			boolean isMainResouce = el.getIsMainType();
			int population = this.population;
			int workForce = calculateWorkers(population, isMainResouce);
			
			resources[i].setWorkers(workForce);
		}
	}
	private int calculateWorkers(int population, boolean isMainResouce) {
		int labourForce = population / 2;
		
		if(labourForce < 4) {
			return 0;
		}
		if(labourForce == 4) {
			return 1;
		}
		if(isMainResouce) {
			return (int) Math.ceil((double) labourForce / 4);
		}
		
		return (int) Math.floor((double) labourForce / 4);
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
	
	public void setIsMainResource(RESOURCE_TYPES search, boolean x) {
		int id = search.getValue();
		this.resources[id].setIsMainType(x);
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
    
    public void setTownName(String x) { this.name = x; }
    public String getTownName() { return this.name; }
    
    public void debugOverview() {
    	System.out.println("\n#INI# Town Entity - Overview #INI#");
    	System.out.println("positionX: " + this.positionX);
    	System.out.println("positionY: " + this.positionY);
    	System.out.println("population: " + this.population + "/" + this.populationLimit);
    	System.out.println("reputation: " + this.reputation);
    	System.out.println("resourseType: " + this.mainResource.toString());
    	System.out.println("townType: " + this.townType.toString());
    	for(int i = 0; i < this.resources.length; i++) {
    		System.out.println("resource - " + this.resources[i].getResourceType().toString() + ": ");
        	System.out.println("  amount: " + this.resources[i].getAmount() + "/" + this.resources[i].getResourceLimit());
        	System.out.println("  workers: " + this.resources[i].getWorkers());
        	System.out.println("  rate: " + this.resources[i].getResourceRate());
        	System.out.println("  time: " + this.resources[i].getResourceTime());
    	}    	
    	System.out.println("#END# Town Entity - Overview #END#\n");
    }
}
