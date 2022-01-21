package aworld;


import java.awt.event.ActionListener;

import SimStation.Simulation;
import framework.AppFactory;
import framework.AppPanel;
import framework.Command;
import framework.Model;

public class WorldFactory implements AppFactory {

	public WorldFactory() {
		// done
	}

	@Override // makes maze
	public Model makeModel() {
		Model sim = new World("test");
		return sim;
	}

	@Override // makes the panel under the FileMenu //NULL POINTER where?
	public AppPanel makePanel(Model model, ActionListener listener) {
		AppPanel appP = new WorldPanel((World) model, listener);
		return appP;
	}

	@Override
	public String[] getEditCommands() {

		return new String[] { "Start", "Suspend", "Resume", "Stop", "New Flocking", "New Random Walk"};
		// Under the Edit button, these strings appear.
	}

	@Override // convert commands to string
	public Command makeEditCommand(Model model, String type) {
		// needs to return type command
		World world = (World) model;

		switch (type) {
		case "Start":
			return new Start(world);

		case "Suspend":
			return new Suspend(world);

		case "Resume":
			return new Resume(world);

		case "Stop":
			return new Stop(world);
		
		case "New Flocking":
			return new Flocking(world);
			
		case "New Random Walk":
			return new RandomWalk(world);
		}
		return null;
	}

	// Not sure if needed in Maze Challenge assignment.
	@Override
	public String getTitle() {

		return new String("World Simmulation");
	}

	@Override
	public String[] getHelp() {

		return new String[] { "About", "Contents" };
		// After clicking help, these two string appear in the drop down menu.
	}

	@Override
	public String about() {
		return "Simmulation version 1.0 by Jacky Tran " + Character.toString((char) 169) + " 2019";
		// The About info under Help.
	}

	@Override
	public String[] contents() {
		return new String[] { "Watch the neat simulation" };
		// The contents infor under Help.
	}
}