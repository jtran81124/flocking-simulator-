package SimStation;

import aworld.World;
import framework.Model;

public abstract class Simulation extends Model implements Runnable {

	protected String name;
	protected Long clock;
	protected SimState state; // READY, SUSPENDED, STOPPED, RUNNING
	
	transient protected Thread thread;
	//thread is not serializable, so when serializeing, it skips the thread.

	// add constructor?

	public Simulation(String name) {
		this.name = name;
		clock = 0L;
		this.thread = new Thread(this);
		state = SimState.READY;

	}

	public Long getClock() { return clock; }
	public String getName() {return name;}
	public SimState getSimState() { return state; }
	
	public void run() {
		// state is running
		// Thread catch thread currently running
		thread = Thread.currentThread(); // thread is changed, so call changed()
		this.changed();
		while (state != SimState.STOPPED) 
		{
			//
			this.update();

			// to have threads cooperate with each other,
			// threads need to sleep for others to run
			try {
					Thread.sleep(30);
					//lets 1 simulation go into while loop at a time
					//allows more than 1 simulation window to run at a time
					
					
					synchronized (this) //lock thing in professor
						{
							while (state == SimState.SUSPENDED) {wait();}
						}
				} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void start() {

		
		if (state == SimState.READY) {
			state = SimState.RUNNING;
			thread.start(); // starts and overrides run()
			this.changed(); // updates all observers,
			
		}
		else if(state == SimState.STOPPED)
		{
			this.reset();
			state = SimState.RUNNING;
			thread.start();
			this.changed();
		}
	}

	public void suspended() {
		if (state == SimState.RUNNING) {
			state = SimState.SUSPENDED;
			this.changed();
		}
	}

	public synchronized void resume() {
		if (state == SimState.SUSPENDED) {
			state = SimState.RUNNING;
			this.changed();
			//call notify to get out of wait loop in suspended
			notify();
		}

	}

	//kill the thread
	public synchronized void stop() {
		
		
		if (state == SimState.RUNNING || state == SimState.SUSPENDED) {
			state = SimState.STOPPED;
			this.changed();
			//call notify to get out of wait loop
			notify();
			
		}
		

	}
	
	public void reset()
	{
		clock = 0L;
		state = SimState.READY;
		thread = new Thread(this);
		//would do new to restart
	}

	
	public void update() { // this method gets called in run method.
		clock++;
		this.changed(); // updates all the observers looking at Observable (the model)
	}
	
	public void copy(Model m)
	{
		super.copy(m);
		Simulation otherWorld = (Simulation) m;
		clock = otherWorld.getClock();
		name = otherWorld.getName();
		state = otherWorld.getSimState();
		
		this.thread = new Thread(this, this.name);
		synchronized (this)
		{
			thread.start();
		}
		
	}
}
