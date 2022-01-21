package aworld;

import java.io.Serializable;
import java.util.Random;

import framework.*;

//abstract class is an idea, Agent cannot be initialized
//Bird and drunk have Agent's methods, and extend Agent
public abstract class Agent implements Serializable {
	// serializable: used when wanting to save an object to a file.

	protected int xc;
	protected int yc;
	protected Heading heading;
	protected World world;

	public Agent(World world) {
		this.world = world;
		xc = Utilities.random(World.SIZE);
		yc = Utilities.random(World.SIZE);
		heading = randomHeading();
	}

	public int getX() {
		return xc;
	}

	public int getY() {
		return yc;
	}

	public Heading getHeading() {
		return heading;
	}

	public Heading randomHeading() {
		Heading rand;
		int turn = Utilities.random(3 + 1); // exclusive so add 1
		if (turn == 0) {
			rand = Heading.NORTH;
		} else if (turn == 1) {
			rand = Heading.EAST;
		} else if (turn == 2) {
			rand = Heading.SOUTH;
		} else // turn ==3
		{
			rand = Heading.WEST;
		}
		return rand;
	}

	public void move(int steps) {
		if (this.heading == Heading.NORTH) {
			if (yc - steps < 0) {
				yc = World.SIZE - (steps - yc); // if yc = 2 , steps = 4, World.Size - (4-2)
			} else {
				yc = yc - steps;
			}
		}

		else if (this.heading == Heading.SOUTH) {
			if (yc + steps > World.SIZE) {
				yc = (yc + steps) - World.SIZE; // if yc = 298 steps = 4,
			} else {
				yc = yc + steps;
			}
		} else if (this.heading == Heading.EAST) {
			if (xc + steps > World.SIZE) {
				xc = (xc + steps) - World.SIZE; // if yc = 298 steps = 4,
			} else {
				xc = xc + steps;
			}
		} else if (this.heading == Heading.WEST) {
			if (xc - steps < 0) {
				xc = World.SIZE - (steps - xc); // if yc = 2 , steps = 4, World.Size - (4-2)
			} else {
				xc = xc - steps;
			}
		}

	}

	public abstract void update(); // bird and drunk need to have update method

	public void turn(Heading heading) // sets direction to head to.
	{
		this.heading = heading;
	}

}
