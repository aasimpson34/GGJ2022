package townentity;

import inventorysystem.RESOURCE_TYPES;

public class TownResources {
	private RESOURCE_TYPES resourceType;
	private int resourceLimit;
	private int resourceTime;
	private int recourseRate;
	private int currentTime;
	private int amount;
	private int workers;
	
	public TownResources(RESOURCE_TYPES resourceType, int amount) {
		this.resourceType = resourceType;
		this.amount = amount;
	}
	
	public int getAmount() { return this.amount; }
	public void setAmount(int x) { 
		if(x > this.resourceLimit) {
			this.amount = this.resourceLimit;
			return;
		}
		if(x < 0) {
			this.amount = 0;
		}
		
		this.amount = x; 
	}
	
	public int getResourceLimit() { return this.resourceLimit; }
	public void setResourceLimit(int x) { this.resourceLimit = x; }
	
	public int getWorkers() { return this.workers; }
	public void setWorkers(int x) { this.workers = x; }
	
	public int getResourceTime() { return this.resourceTime; }
	public void setResourceTime(int x) { this.resourceTime = x; }
	
	public int getResourceRate() { return this.recourseRate; }
	public void setResourceRate(int x) { this.recourseRate = x; }
	
	public int getCurrentTime() { return this.currentTime; }
	public void setCurrentTime(int x) { this.currentTime = x; }
	
	public RESOURCE_TYPES getResourceType() { return this.resourceType; }
}
