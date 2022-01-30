package inventorysystem;

import java.util.HashMap;
import java.util.Map;

public enum RESOURCE_TYPES {
	E_WOOD(0, "Wood"),
	E_METAL(1, "Metal"),
	E_ORE(2, "Ore"),
	E_LINEN(3, "Linen"), 
	
	E_MAX_COUNT(E_LINEN.getValue() + 1, "");
	
	private static final Map<Integer, RESOURCE_TYPES> lookup = new HashMap<Integer, RESOURCE_TYPES>();
	
	static {
		for(RESOURCE_TYPES type: RESOURCE_TYPES.values())
		{
			lookup.put(type.getValue(), type);
		}
	}
	
	private final int id;
	private final String name;
	
	RESOURCE_TYPES(int id, String name) { this.id = id; this.name = name; }
    public int getValue() { return id; }
    public String getName() {return name;};
    
    public static RESOURCE_TYPES get(int type)
    {
    	return lookup.get(type);
    }
}
