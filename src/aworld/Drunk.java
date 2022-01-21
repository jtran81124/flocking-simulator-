package aworld;

import framework.Utilities;

public class Drunk extends Agent {
	
	int speed;
	public Drunk(World world) {
		super(world);
		speed = Utilities.random(10);
	}

	public void random() {
		speed = Utilities.random(10);
		this.turn(randomHeading());
		
	}

	public void update() {
		this.random();
		//new speed and heading are set
		this.move(speed);
	}
}