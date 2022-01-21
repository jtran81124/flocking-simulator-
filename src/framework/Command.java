package framework;


public abstract class Command {

	protected Model model;

    public abstract void execute();
}


//Jacky 11/10 Try as Interface and completed
//Jacky 11/14 I tried Commands as an interface. 
	//Didnt work with appFrame actionPerformed() So I went back to abstract class

// public interface Command
// {
// 	public void execute();
// }
