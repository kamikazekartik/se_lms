package Controller;

import java.io.*;

/**
 * This class is responsible for logging all actions of the application
 * @author KamikaZe
 *
 */
public class LogWriter {

	/**
	 * This function is static so that logWriter need not be instantiated each time
	 * Just call the LogWrite Method from the class.
	 * @param log
	 */
	 public static void logWrite(String log){
	        
	        try{
	        BufferedWriter output;
	        output = new BufferedWriter(new FileWriter("Log.dat", true));
	        output.append(log);
	        output.newLine();
	        	        
	        output.close();
	    }
	        catch(Exception e){
	            
	        }
	    }

}
