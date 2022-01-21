package framework;


import java.util.*;
import java.io.*;

// Collaborated 11/14: removed setModel()
public abstract class Model extends Observable implements Serializable {

	protected String fileName;
	protected boolean unsavedChanges;
	private static final long serialVersionUID = 1L;
	
	public Model(String fileName) {
		this.fileName = fileName;
		unsavedChanges = false;
	}
	
	public Model() { this(null); }
	
	public void copy(Model m) {
		fileName = m.getFileName();
		unsavedChanges = m.hasUnsavedChanges();
		changed();
	}
	
	public void changed() {
		this.setUnsavedChanges(true);
		this.setChanged();
		this.notifyObservers();
		this.clearChanged(); //Added 
	}
	public boolean hasUnsavedChanges() {
		return unsavedChanges;
	}
	public void setUnsavedChanges(boolean flag) {
		this.unsavedChanges = flag;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
