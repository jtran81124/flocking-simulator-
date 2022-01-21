package aworld;

import SimStation.Simulation;
import framework.*;

public class Start extends Command{

	private World world;
	
	public Start(Model sim)
	{
		this.world =(World) sim;
	}
	
	public void execute()
	{
		world.start();
	}
}
