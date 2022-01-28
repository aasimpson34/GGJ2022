package renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {

	SpriteBatch m_spriteBatch;
	
	static GameRenderer m_instance;
	
	/**
	 * get the statically defined instance.
	 * @return
	 */
	public static GameRenderer getInstance()
	{
		if(m_instance == null)
			m_instance = new GameRenderer();
		return m_instance;
	}
	
	/**
	 * Constructor
	 */
	public GameRenderer()
	{
		m_spriteBatch = new SpriteBatch();
	}
	
	/**
	 * Get the batch
	 * @return
	 */
	public SpriteBatch getBatch()
	{
		return m_spriteBatch;
	}
}
