/**
 * Project Name: GGJ2022-core 
 * Created On: 29 Jan 2022
 * file: TownWorldEntityUI.java
 * Purpose of class: 
 *
 * Written by @author
 */
package userInterface;

import townentity.TownEntity;

/**
 * @author Cullen Willis
 *
 */
public class TownWorldEntityUI extends WorldEntityUI {
	
	private TownEntity townEntity;
	@Override
	public void update() {
		townEntity = getTownEntity();
		
	}

	@Override
	public void render() {
		renderWindow(0, 0, 200, 200);
	}

}
