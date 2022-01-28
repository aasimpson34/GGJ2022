package renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {

	SpriteBatch m_spriteBatch;
	
	static GameRenderer m_instance;
	
	public static GameRenderer getInstance()
	{
		if(m_instance == null)
			m_instance = new GameRenderer();
		return m_instance;
	}
	
	public GameRenderer()
	{
		m_spriteBatch = new SpriteBatch();
	}
	
	public SpriteBatch getBatch()
	{
		return m_spriteBatch;
	}
}
