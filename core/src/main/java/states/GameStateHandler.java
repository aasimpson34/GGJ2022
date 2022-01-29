package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import worldmapstate.WorldMapState;

public class GameStateHandler {
	
	public GameStateHandler()
	{
	}
	
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		

		m_currentGameStates.peek().render();
		
	}

	public void update() {
		m_currentGameState = m_currentGameState.update();
			m_currentGameStates.pop();
		}
	}
}
