package aworld;

import framework.*;

public class Stop extends Command{

	private World world;
	
	public Stop(Model sim)
	{
		this.world =(World) sim;
	}
	
	public void execute()
	{
		world.stop();
	}
}

