package inventorysystem;

import java.util.Random;

/**
 * This inventory system will contain everything the user can keep
 * 
 * Should it also be responsible for managing the passive resources.
 * 
 * TODO: 	potentially if we're going to expand the things the player can have in their inventory 
 * 			then we may need to change this systsem where the resource is handled in a different
 * 			class.
 * 
 * @author Aaron Simpson
 *
 */
public class InventorySystem {
	
	int m_currentInventoryNumber[];
	
	/**
	 * Inventory System Constructor
	 */
	public InventorySystem()
	{
		//Create integer for every type of resource available
		m_currentInventoryNumber = new int[RESOURCE_TYPES.E_MAX_COUNT.getValue()];
		
		//Set Initial resources to 0.
		for(int x = 0; x < RESOURCE_TYPES.E_MAX_COUNT.getValue(); x++)
		{
			m_currentInventoryNumber[x] = new Random().nextInt(100);
		}
	}
	
	/**
	 * Increment the resource
	 * @param type
	 * @param amount
	 */
	public void incrementResource(RESOURCE_TYPES type, int amount)
	{
		m_currentInventoryNumber[type.getValue()] += amount;
	}
	
	/**
	 * Decrement the specified resource
	 * @param type
	 * @param amount
	 */
	public void decrementResource(RESOURCE_TYPES type, int amount)
	{
		m_currentInventoryNumber[type.getValue()] -= amount;
	}
	
	/**
	 * Returns the resource.
	 * @param type
	 * @return
	 */
	public int getResourceCount(RESOURCE_TYPES type)
	{
		return m_currentInventoryNumber[type.getValue()];
	}
	
	

}
