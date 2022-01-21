package framework;


public class CommandProcessor {

	public static CommandProcessor theCommandProcessor =  new CommandProcessor();
    
	public CommandProcessor() 
	{

    }
	public static void execute(Command cmmd) 
	{
        cmmd.execute();
    }

}

