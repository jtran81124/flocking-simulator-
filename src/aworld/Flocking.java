package aworld;

import framework.AppFrame;
import framework.Command;
import framework.Model;

public class Flocking extends Command {

	private World world;

	public Flocking(Model sim)
		{
			this.world =(World) sim;
		}

	public void execute() {
		
		//do what you want when command gets called 
	
		world.setWorldState(WorldState.FLOCKING);
		
	}
}
