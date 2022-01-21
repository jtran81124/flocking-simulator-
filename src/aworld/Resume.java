package aworld;


import framework.*;

public class Resume extends Command {

	private World world;
	
	public Resume(Model sim)
	{
		this.world =(World) sim;
	}
	
	public void execute()
	{
		world.resume();
	}
}
