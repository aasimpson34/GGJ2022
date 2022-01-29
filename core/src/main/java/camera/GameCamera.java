/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: GameCamera.java
 * Purpose of class: 
 *
 * Written by @author Aaron Simpson
 */
package camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author aasim
 *
 */
public class GameCamera {
	static GameCamera m_instance;
	
	/**
	 * Get the static instance of this class
	 * @return
	 */
	public static GameCamera getInstance()
	{
		if(m_instance == null)
			m_instance = new GameCamera();
				
		return m_instance;
	}
	
	//================== Member Variables ======================
	Vector2 m_targetPosition;
	float m_speedToMoveToPosition;
	
	OrthographicCamera m_gameCameraMatrix;
	
	boolean translateCalled;
	
	/**
	 * Constructor 
	 * Game camera matrix will be initialised here.
	 */
	public GameCamera()
	{
		m_gameCameraMatrix = new OrthographicCamera(1920,1080);
		m_targetPosition = new Vector2(0, 0);
	}
	
	/**
	 * Update method called via the state handlers
	 */
	public void update()
	{
		//Interpolation formula 
		if(!translateCalled)
		{
		float interpolate_amount = (float) (1.0 - Math.pow(1 - m_speedToMoveToPosition * 3.0f, Gdx.graphics.getDeltaTime()* 60));
		m_gameCameraMatrix.position.x  = Interpolation.linear.apply(m_gameCameraMatrix.position.x, m_targetPosition.x, interpolate_amount);
		m_gameCameraMatrix.position.y  = Interpolation.linear.apply(m_gameCameraMatrix.position.y, m_targetPosition.y, interpolate_amount);
		}
		else
		{
			m_gameCameraMatrix.position.x  += m_targetPosition.x;
			m_gameCameraMatrix.position.y  += m_targetPosition.y;
			translateCalled = false;
		}
		m_gameCameraMatrix.update();
	}
	
	/**
	 * This operation is a handy function that will allow the user to 
	 * move to a certain area over a specified time period
	 * 
	 * if <time> == 0 then it will move instantly.
	 * @param x
	 * @param y
	 * @param time
	 */
	public void moveTo(float x, float y, float speed)
	{
		m_targetPosition = new Vector2(x, y);
		m_speedToMoveToPosition = speed;
		
	}
	
	public Vector3 getMouseCoords(Vector2 mousePosition)
	{
		return m_gameCameraMatrix.unproject(new Vector3(mousePosition, 0));
	}

	/**
	 * @return the matrix projection to be used
	 */
	public Matrix4 getProjection() {
		return m_gameCameraMatrix.combined;
	}

	/**
	 * @return
	 */
	public Vector2 getPosition() {
		return new Vector2(m_gameCameraMatrix.position.x, m_gameCameraMatrix.position.y);
	}

	/**
	 * @param i
	 * @param j
	 */
	public void translate(int i, int j) {
		translateCalled = true;
		m_targetPosition = new Vector2(i,j);
	}
}
