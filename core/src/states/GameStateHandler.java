package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameStateHandler {
	GameState m_currentGameState;
	
	public GameStateHandler()
	{
		m_currentGameState = new GameplayStateHandler();
	}
	
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		m_currentGameState.render();
		
	}

	public void update() {
		m_currentGameState = m_currentGameState.update();
	}

}
