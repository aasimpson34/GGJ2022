package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

import assetmanager.LoadGameAssetsState;
import worldmapstate.WorldMapState;

public class GameStateHandler {
	Array<GameState> m_currentGameStates;
	
	public GameStateHandler()
	{
		m_currentGameStates = new Array<GameState>();
		m_currentGameStates.add(new LoadGameAssetsState());
	}
	
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		

		m_currentGameStates.peek().render();
		
	}

	public void update() {
		GameState gameStateToAdd = m_currentGameStates.peek().update();
		if(gameStateToAdd == null)
		{
			m_currentGameStates.pop();
		}
		else if(gameStateToAdd != m_currentGameStates.peek())
		{
			m_currentGameStates.add(gameStateToAdd);

		}
	}
}
