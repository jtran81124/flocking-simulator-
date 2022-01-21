package aworld;

import framework.AppFrame;


public class WorldRunner {

	public static void main (String[] args)
	{
		AppFrame app = new AppFrame( new WorldFactory());
		app.pack();
		app.setVisible(true);
	}
	
}
