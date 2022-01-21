package aworld;

import java.util.ArrayList;
import java.util.List;

import SimStation.Simulation;
import framework.Model;

public class World extends Simulation { // Simulation implements runnable

	/*
	 * notes in 11/26 class Two main methods, one world that runs flocks, and one
	 * world that runks drunks
	 */

	public static int SIZE = 300;
	public List<Agent> agents;
	public WorldState state;

	public World(String name) {
		super(name);
		reset();
		//calls this classes reset
		
		//issue now, im just adding 50 birds, my buttons dont make new worlds
		//how can a world 		
		
	}
	
	public WorldState getState()
	{
		return state;
	}
	public void reset()
	{
		
		super.reset();
		agents = new ArrayList<>();
		if ( state == WorldState.FLOCKING || state == null)
		{
			for(int i = 0; i <= 50; i++)
			{
				agents.add(new Bird(this));
			}
		}
		else if (state == WorldState.RANDOMWALK)
		{
			for (int i = 0; i<= 50; i++)
			{
				agents.add(new Drunk(this));
			}
		}
		this.changed();
	}

	public void setWorldState(WorldState state) {this.state = state;}
	public List<Agent> getAgents(){	return agents;	}
	
	public void add(Agent agent) {
		agents.add(agent);
	}

	public void update() {
		// update subclasses and super class
		for (Agent a : agents) {
			a.update();
			
		}
		super.update(); // updates clock in simulation and does this.changed();
		
	}

	//so two agents dont call this method at this same time
	public synchronized Agent findNeighbor(Agent seeker, int radius) {
		Agent neighbor = null;
		int x;
		int y;

		for (Agent o : agents) {
			// get distance from seeker and otherAgent. Distance formula.
			x = seeker.getX() - o.getX();
			x = x * x;
			y = seeker.getY() - o.getY();
			y = y * y;
			double function = Math.sqrt(x + y);
			double distance = Math.round(function); // decimal is .0

			// Vanilla case, two agents are close inside the border
			if (distance <= radius) {
				neighbor = o;
			}

			// base case, two agents are close across the border

		}

		return neighbor;
	}
	@Override
	public void copy(Model m)
	{
		super.copy(m);
		World otherWorld = (World) m;
		agents = otherWorld.getAgents();
		state = otherWorld.getState();
	}
}
