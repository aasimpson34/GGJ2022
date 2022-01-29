/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: PlayerRenderer.java
 * Purpose of class: 
 *
 * Written by @author
 */
package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.math.Vector2;

import assetmanager.ResourceLookup;
import renderer.GameRenderer;

/**
 * @author aasim
 *
 */
public class PlayerRenderer {
	
	final int NUMBER_OF_ANIMATION_FRAMES = 6;
	
	AtlasSprite IDLE[] = new AtlasSprite[NUMBER_OF_ANIMATION_FRAMES];
	AtlasSprite RUN[] = new AtlasSprite[NUMBER_OF_ANIMATION_FRAMES];
	
	AtlasSprite m_activeRegion;
	
	float m_currentFrame;
	
	boolean m_imageShouldBeFlipped;
	
	public PlayerRenderer()
	{
		TextureAtlas atlas = ResourceLookup.getInstance().getTextureAtlas("player.atlas");
		
		for(int x = 0; x < NUMBER_OF_ANIMATION_FRAMES; x++)
		{
			IDLE[x] = new AtlasSprite(atlas.findRegion("idle", x + 1));
			RUN[x] 	= new AtlasSprite(atlas.findRegion("run", x + 1));
		}
		
		m_currentFrame = 0;
		
		m_activeRegion = new AtlasSprite(IDLE[0]);
	}
	
	public void render(Vector2 position)
	{
		m_activeRegion.setPosition(position.x, position.y);
		m_activeRegion.draw(GameRenderer.getInstance().getBatch());
	}
	
	public void update(Vector2 playerVelocity)
	{
		
		if(playerVelocity.len() > 0)
		{
			m_currentFrame += Gdx.graphics.getDeltaTime() * (playerVelocity.len() * 0.25F);
			if((int)m_currentFrame >= NUMBER_OF_ANIMATION_FRAMES)
				m_currentFrame = 0;
			m_activeRegion =RUN[(int) m_currentFrame];
			
			if(playerVelocity.x < 0)
				m_imageShouldBeFlipped = true;
			else if (playerVelocity.x > 0)
				m_imageShouldBeFlipped = false;
	
		}else
		{
			m_currentFrame += Gdx.graphics.getDeltaTime() * 15;
			if((int)m_currentFrame >= NUMBER_OF_ANIMATION_FRAMES)
				m_currentFrame = 0;
			m_activeRegion = IDLE[(int) m_currentFrame];

		}
		
		m_activeRegion.setFlip(m_imageShouldBeFlipped, false);
	}
}
