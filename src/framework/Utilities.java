package framework;

import java.awt.event.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;

/**
 * Brianna: Added from Pearce's framework page
 * Brianna(11/10): fixed minor bug
 * Collaborated 11/14: removed setModel
 */

public class Utilities {

	// enables stack traces and diagnostics
	public static Boolean DEBUG = true;

	// asks user a yes/no question
	public static boolean confirm(String query) {
		int result = JOptionPane.showConfirmDialog(null,
				query, "choose one", JOptionPane.YES_NO_OPTION);
		return result == 1;
	}

	// asks user for info
	public static String ask(String query) {
		return JOptionPane.showInputDialog(null, query);
	}

	// tells user some info
	public static void inform(String info) {
		JOptionPane.showMessageDialog(null,info);
	}

	// tells user lots of info
	public static void inform(String[] items) {
		String infoString = "";
		for(int i = 0; i < items.length; i++) {
			infoString = infoString + "\n" + items[i];
		}
		inform(infoString);
	}

	// tells user about an error
	public static void error(String gripe) {
		JOptionPane.showMessageDialog(null,
				gripe,
				"OOPS!",
				JOptionPane.ERROR_MESSAGE);
	}

	// tells user about an exception
	public static void error(Exception gripe) {
		if (DEBUG) gripe.printStackTrace();
		JOptionPane.showMessageDialog(null,
				gripe.getMessage(),
				"OOPS!",
				JOptionPane.ERROR_MESSAGE);
	}

	// asks user save changes?
	public static void saveChanges(Model model) {
		if (model.hasUnsavedChanges() &&
		      Utilities.confirm("current model has unsaved changes, continue?"))
			Utilities.save(model, false);
	}

	// asks user for a file name
	public static String getFileName(String fName) {
		
		JFileChooser chooser = new JFileChooser();
		String result = null;
		if (fName != null) {
		   // open chooser in directory of fName
           chooser.setCurrentDirectory(new File(fName));
		}
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			result= chooser.getSelectedFile().getPath();
		}
		return result;
	}

	// save model
	public static void save(Model model, Boolean saveAs) {
		String fName = model.getFileName();
		if (fName == null || saveAs) {
			fName = Utilities.getFileName(fName);
			model.setFileName(fName);
		}
		try {
			ObjectOutputStream os =
			   new ObjectOutputStream(new FileOutputStream(fName));
			model.setUnsavedChanges(false);
			os.writeObject(model);
			os.close();
		} catch (Exception err) {
			model.setUnsavedChanges(true);
			Utilities.error(err);
		}
	}

	// open model
	public static Model open(Model model) {
		saveChanges(model);
		String fName = Utilities.getFileName(model.getFileName());
		Model newModel = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
			newModel = (Model)is.readObject();
			is.close();
		} catch (Exception err) {
			Utilities.error(err);
		}
		return newModel;
	}

	// a simple menu maker
	// public static void loadModel(Model model)
	// {	}
	//Jmenu's are initialized here. Because of that, new Jmenu's do not need to be initialized in AppFrame
	
	public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
		JMenu result = new JMenu(name);
		for(int i = 0; i < items.length; i++) {
			JMenuItem item = new JMenuItem(items[i]);
			item.addActionListener(handler);          //listens for button to be pressed
			result.add(item);
		}
		return result;
	}
	
	
	public static int random(int min, int max)
	{
		if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
			throw new IllegalArgumentException("Invalid range");
		}
		//inclussive
		return new Random().nextInt(max - min + 1) + min;
	}
	//exclusive
	public static int random(int mazeSize) {
		Random rand = new Random();
		return rand.nextInt(mazeSize);
	}
	
}