package inventorysystem;

public enum RESOURCE_TYPES {
	E_WOOD(0),
	E_METAL(1),
	E_ORE(2),
	E_LINEN(3), 
	
	E_MAX_COUNT(E_LINEN.getValue() + 1);
	
	private final int id;
	RESOURCE_TYPES(int id) { this.id = id; }
    public int getValue() { return id; }
}
