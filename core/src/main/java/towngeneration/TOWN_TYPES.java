package towngeneration;

public enum TOWN_TYPES {
	T_CAMP(0),
	T_VILLAGE(1),
	T_STRONGHOLD(2),
	T_FORTRESS(3);
	
	private final int id;
	TOWN_TYPES(int id) { this.id = id; }
    public int getValue() { return id; }
}
