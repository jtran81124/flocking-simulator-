package aworld;

import framework.Utilities;

public class Bird extends Agent {

	private int speed;

	public Bird(World world) {
		super(world);
		speed = Utilities.random(1, 10); // inclusive
	}

	public int getSpeed() {
		return speed;
	}

	// this bird is copied to a nearby bird
	public void similarCopy() {

		// need to copy another agent
		// for now set radius 10
		// other agent gets copied to this
		// findNeighbor returns a nearby 'other' Agent
		Agent other = world.findNeighbor(this, 10);

		// THIS, copies the heading of other bird
		Heading otherHeading = other.getHeading();
		this.turn(otherHeading);
		Bird otherB = (Bird) other;

		// THIS, is set to a speed similar of OTHER bird
		if (otherB.getSpeed() > this.getSpeed()) {
			speed = Utilities.random(this.getSpeed(), otherB.getSpeed());
		} else {
			speed = Utilities.random(otherB.getSpeed(), this.getSpeed());
		}

	}

	public void update() {
		
		this.similarCopy();
		//new heading and new speed is set similarly to a nearby bird
		
		this.move(speed);
		
	}

}

