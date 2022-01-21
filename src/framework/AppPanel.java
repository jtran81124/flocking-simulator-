package framework;

import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JPanel;

public class AppPanel extends JPanel implements Observer {
	  protected Model model;
	  protected ActionListener listener;
	 // protected ArrayList<View> views; 
	  
	  
	  public AppPanel(Model model, ActionListener listener)
	  {
		this.model = model;  
		//views = new ArrayList<View>();
		this.listener = listener; 
		model.addObserver(this);
	  }
	  
	

	  
	  public void update(Observable subject, Object msg) 
	  {
		  //no-op
	  }

}