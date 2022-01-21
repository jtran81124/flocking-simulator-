package aworld;

import framework.AppFrame;
import framework.Command;
import framework.Model;

public class RandomWalk extends Command {

	private World world;

	public RandomWalk(Model sim)
		{
			this.world =(World) sim;
		}

	public void execute() {
		
		//do what you want when command gets called 
	
		world.setWorldState(WorldState.RANDOMWALK);
		
	}
}
