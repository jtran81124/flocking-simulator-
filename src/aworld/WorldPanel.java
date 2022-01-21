package aworld;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import SimStation.Simulation;
import framework.AppPanel;
import framework.Model;


public class WorldPanel extends AppPanel{

	private static final long serialVersionUID = 1L;

	private World world;
	private ControlView ctrlView;
		
	public WorldPanel(Model model, ActionListener listener) {
		super(model, listener);
		this.world = (World) model;
		setLayout(new GridLayout (1,2)); // 1x2 grid layout
		
		ctrlView = new ControlView(world, listener);
		this.add(ctrlView);
		WorldView worldView = new WorldView(model);
		this.add(worldView);
	}
}
