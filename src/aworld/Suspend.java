package aworld;

import framework.*;

public class Suspend extends Command {

	private World world;
	
	public Suspend(Model sim)
	{
		this.world =(World) sim;
	}
	
	public void execute()
	{
		world.suspended();
	}
}
