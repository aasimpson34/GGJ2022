package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import entity.GameObjectEntity;
import worldmap.WorldMap;

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
	
	public void updateMovement(WorldMap map)
	{
		
		Vector2 m_previousPlayerPosition = m_playerPosition.cpy();
		//Increment the player position by the requested velocity
		m_playerPosition.mulAdd(m_velocity, Gdx.graphics.getDeltaTime());
		
		//Scale the velocity (gives a dampening effect)
		m_velocity.scl((float) Math.pow(0.001f, Gdx.graphics.getDeltaTime()));
		if(m_velocity.epsilonEquals(0, 0, 1.0F))
		{
			m_velocity.set(0,0);
		}
		
		if(m_velocity.len() < 500)
		{	
			handlePlayerMovement();
		}
		
		boolean doesPlayerCollide = doesPlayerCollide(map);
		if(doesPlayerCollide)
		{
			m_playerPosition = m_previousPlayerPosition.cpy();
			m_velocity.scl(-0.5F);
		}
	}
	/**
	 * @param map
	 * @return
	 */
	private boolean doesPlayerCollide(WorldMap map) {
		boolean doesCollide = false;
		Vector2 global = m_playerPosition.cpy();
		global.y+= 5;
		
		Vector2 playerLeft = global.cpy();
		playerLeft.x +=16;
		
		Vector2 playerRight = global.cpy();
		playerRight.x += 32;
		
		for(Polygon col : map.getCollisionList())
		{
	
			if(col.contains(playerLeft) || col.contains(playerRight))
			{
				doesCollide = true;
				break;
			}
		}
		return doesCollide;
	}

	@Override
	public boolean update() {
	
		//Send the velocity to update the player renderer (for speed of animation)
		m_playerRenderer.update(m_velocity);
				
		return false;
	}

	/**
	 * 
	 */
	private void handlePlayerMovement() {
		
		if(Gdx.input.isKeyPressed(Keys.A))
			m_velocity.x -= 1250 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D))
			m_velocity.x += 1250 * Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Keys.W))
			m_velocity.y += 1250 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.S))
			m_velocity.y -= 1250 * Gdx.graphics.getDeltaTime();
	}

	/**
	 * @return
	 */
	public Vector2 getPosition() {
		return m_playerPosition;
	}

}
