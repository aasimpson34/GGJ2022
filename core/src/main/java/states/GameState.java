package states;

public interface GameState {
	GameState update();
	public void render();
	public void onExit();
	public void onEntry();

}
