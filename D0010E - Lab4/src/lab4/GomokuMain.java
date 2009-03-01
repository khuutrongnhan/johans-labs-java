package lab4;

import lab4.data.*;
import lab4.gui.*;
import lab4.client.*;

/** Test class for Gomoku game - starts 2 clients
 * @author Johan Stršm
 * @author Afshin Aresh
 */
public class GomokuMain {

	/** Main program
	 * @param args Port number the first client listens to (+1 for the other client)
	 */
	public static void main(String[] args) {
		int port1, port2;
	    port1 = Integer.parseInt(args[0]);
	    port2 = port1+1;
		
	    // Player1
	    GomokuClient client1 = new GomokuClient(port1);
	    GomokuGameState gs1 = new GomokuGameState(client1);
	    new GomokuGUI(gs1,client1);
	    
	    // Player2
	    GomokuClient client2 = new GomokuClient(port2);
	    GomokuGameState gs2 = new GomokuGameState(client2);
	    new GomokuGUI(gs2,client2);

		  
	}

}
