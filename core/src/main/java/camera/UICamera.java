/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: GameCamera.java
 * Purpose of class: 
 *
 * Written by @author Aaron Simpson
 */
package camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

/**
 * Would probably be better the two camera systems worked together.
 * @author aasim
 *
 */
public class UICamera {
	static UICamera m_instance;
	
	/**
	 * Get the static instance of this class
	 * @return
	 */
	public static UICamera getInstance()
	{
		if(m_instance == null)
			m_instance = new UICamera();
				
		return m_instance;
	}
	
	//================== Member Variables ======================

	OrthographicCamera m_gameCameraMatrix;
	
	
	/**
	 * Constructor 
	 * Game camera matrix will be initialised here.
	 */
	public UICamera()
	{
		m_gameCameraMatrix = new OrthographicCamera(1920,1080);
		m_gameCameraMatrix.position.x += 1920/2;
		m_gameCameraMatrix.position.y += 1080/2;
		m_gameCameraMatrix.update();
	}
	

	/**
	 * @return the matrix projection to be used
	 */
	public Matrix4 getProjection() {
		return m_gameCameraMatrix.combined;
	}
}
