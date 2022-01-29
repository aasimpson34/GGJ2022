package towngeneration;

import java.util.HashMap;
import java.util.Map;

import inventorysystem.RESOURCE_TYPES;

public enum TOWN_TYPES {
	T_CAMP(0),
	T_VILLAGE(1),
	T_STRONGHOLD(2),
	
	E_MAX_COUNT(T_STRONGHOLD.getValue() + 1);
	
	private static final Map<Integer, TOWN_TYPES> lookup = new HashMap<Integer, TOWN_TYPES>();
	static {
		for(TOWN_TYPES type: TOWN_TYPES.values())
		{
			lookup.put(type.getValue(), type);
		}
	}
	
	private final int id;
	TOWN_TYPES(int id) { this.id = id; }
    public int getValue() { return id; }
    public static TOWN_TYPES get(int type){ return lookup.get(type); }
}
