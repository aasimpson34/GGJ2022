package renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameRenderer {

	SpriteBatch m_spriteBatch;
	ShapeRenderer m_renderer;
	
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
		m_renderer = new ShapeRenderer();
	}
	
	/**
	 * Get the batch
	 * @return
	 */
	public SpriteBatch getBatch()
	{
		return m_spriteBatch;
	}
	
	public ShapeRenderer getShapeRenderer()
	{
		return m_renderer;
	}
}
