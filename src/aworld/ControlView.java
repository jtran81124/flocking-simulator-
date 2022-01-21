package aworld;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SimStation.Simulation;

public class ControlView extends JPanel implements Observer {
	// Control view is an observer because the movesLeft and exitLocation labels
	// looks for changes in the maze.
	// maze.addObserver(this) because observers look for changes in the maze.

	private World world;
	private ActionListener listener;

	// Labels are not dynamic, exitsLeft and movesLeft are dynamic
	private JLabel clockLabel, clock;
	private JButton start, suspend, resume, stop, flocking, randomWalk;

	ControlView(World world, ActionListener listener) {
		this.world = world;
		// simtester observes simulation for changes
		world.addObserver(this);

		this.listener = listener;
		setLayout(new GridLayout(6, 1));
		// Initialize all the variables
		clockLabel = new JLabel("Clock: ");

		clock = new JLabel("" + world.getClock()); // just changes the int to a string

		start = new JButton("Start");
		suspend = new JButton("Suspend");
		resume = new JButton("Resume");
		stop = new JButton("Stop");
		flocking = new JButton("New Flocking");
		randomWalk = new JButton("New Random Walk");

		// add listners to all the buttons, actionPerformed() in "" will handle them.
		start.addActionListener(listener);
		suspend.addActionListener(listener);
		resume.addActionListener(listener);
		stop.addActionListener(listener);
		flocking.addActionListener(listener);
		randomWalk.addActionListener(listener);

		// clock panel
		JPanel p = new JPanel();
		p.add(clockLabel);
		p.add(clock);
		this.add(p);

		// add commands in their own panels
		p = new JPanel();
		p.add(flocking);
		p.add(randomWalk);
		this.add(p);
		
		p = new JPanel();
		p.add(start);
		this.add(p);

		p = new JPanel();
		p.add(suspend);
		this.add(p);

		p = new JPanel();
		p.add(resume);
		this.add(p);

		p = new JPanel();
		p.add(stop);
		this.add(p);


	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Simulation) {

			//need to get the clock somehow.
			clock.setText("" + world.getClock()); // just changes the int to a string

		}

	}
}
