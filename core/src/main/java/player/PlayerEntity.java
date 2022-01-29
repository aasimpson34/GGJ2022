package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import entity.GameObjectEntity;

public class PlayerEntity extends GameObjectEntity{
	
	PlayerRenderer m_playerRenderer;
	
	Vector2 m_playerPosition;
	Vector2 m_velocity;
	
	/**
	 *  
	 */
	public PlayerEntity() {
		m_playerRenderer = new PlayerRenderer();

		m_playerPosition = new Vector2();
		m_velocity = new Vector2();
		
	}
	
	@Override
	public void render() {
		m_playerRenderer.render(m_playerPosition);
	}

	@Override
	public boolean update() {
		
		if(m_velocity.len() < 500)
		{	
			handlePlayerMovement();
		}
		
		//Increment the player position by the requested velocity
		m_playerPosition.mulAdd(m_velocity, Gdx.graphics.getDeltaTime());
		
		//Scale the velocity (gives a dampening effect)
		m_velocity.scl((float) Math.pow(0.001f, Gdx.graphics.getDeltaTime()));
		if(m_velocity.epsilonEquals(0, 0, 1.0F))
		{
			m_velocity.set(0,0);
		}
		//Send the velocity to update the player renderer (for speed of animation)
		m_playerRenderer.update(m_velocity);
				
		return false;
	}

	/**
	 * 
	 */
	private void handlePlayerMovement() {
		
		if(Gdx.input.isKeyPressed(Keys.A))
			m_velocity.x -= 250 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D))
			m_velocity.x += 250 * Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Keys.W))
			m_velocity.y += 250 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.S))
			m_velocity.y -= 250 * Gdx.graphics.getDeltaTime();
	}

}
