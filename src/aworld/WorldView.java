package aworld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import framework.Model;
import framework.View;

public class WorldView extends View implements Observer{

	// remember has an observer, update method is in View
	// draws the world
	private static final long serialVersionUID = 1L;

	private World world;

	public WorldView(Model model) {
		super(model);
		this.world = (World) model;
		this.setMinimumSize(new Dimension(World.SIZE, World.SIZE));
		// do not need abbObserver here because Super class allready does that
	}
	
	//get called when notifyObservers which is in changed();
	//View's update method gets overRidden
	public void update(Observable observable, Object args)
	{
		if(model instanceof World)
		{
			this.world = (World) observable;
		}
		repaint();
	}

	public void paintComponent(Graphics g) {

		Graphics2D gc = (Graphics2D) g;
		super.paintComponent(g);
		Rectangle2D.Double rectangle;

		rectangle = new Rectangle2D.Double(0, 0, World.SIZE, World.SIZE);
		gc.setColor(Color.WHITE);
		gc.fill(rectangle);
		gc.setColor(Color.BLACK);
		gc.draw(rectangle);
		
		//draw agents
		
		for(int i = 0; i < world.getAgents().size(); i++)
		{
			Agent agent = world.getAgents().get(i);
			rectangle = new Rectangle2D.Double(agent.getX(), agent.getY(), 5, 5);
			gc.setColor(Color.RED);
			gc.fill(rectangle);
		}

	}
	

}
