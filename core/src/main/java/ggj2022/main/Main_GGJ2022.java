/**
 * Main class that will call the game itself
 * @author Aaron Simpson
 */
package ggj2022.main;

import com.badlogic.gdx.ApplicationAdapter;

import states.GameStateHandler;


public class Main_GGJ2022 extends ApplicationAdapter {

	GameStateHandler m_gameStateHandler;
	@Override
	public void create () {
		m_gameStateHandler = new GameStateHandler();
	}

	@Override
	public void render () {
		m_gameStateHandler.render();
		m_gameStateHandler.update();

	}
	
	@Override
	public void dispose () {
	
	}
}
