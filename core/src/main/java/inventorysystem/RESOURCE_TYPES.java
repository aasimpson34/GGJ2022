package inventorysystem;

import java.util.HashMap;
import java.util.Map;

public enum RESOURCE_TYPES {
	E_WOOD(0),
	E_METAL(1),
	E_ORE(2),
	E_LINEN(3), 
	
	E_MAX_COUNT(E_LINEN.getValue() + 1);
	
	private static final Map<Integer, RESOURCE_TYPES> lookup = new HashMap<Integer, RESOURCE_TYPES>();
	
	static {
		for(RESOURCE_TYPES type: RESOURCE_TYPES.values())
		{
			lookup.put(type.getValue(), type);
		}
	}
	
	private final int id;
	RESOURCE_TYPES(int id) { this.id = id; }
    public int getValue() { return id; }
    
    public static RESOURCE_TYPES get(int type)
    {
    	return lookup.get(type);
    }
}
