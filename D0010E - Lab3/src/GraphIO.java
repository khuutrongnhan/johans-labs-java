import java.io.IOException;
import java.io.File;  
import java.util.Scanner;


/** IO handling for the Graph class
 * @author Johan Ström
 */
public class GraphIO {

	
	/** Adds nodes and edges according to the description in file filename to Graph g.
	 * @param g Graph object
	 * @param filename the name of a file containing a description of a graph
	 * @throws IOException 
	 */
	static public void readFile(Graph g, String filename)
			throws IOException{
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			if(sc.hasNext()){
				int nrNodes = sc.nextInt();
				for(int i=0; i<nrNodes; i++){
					int id = sc.nextInt();
					int x = sc.nextInt();
					int y = sc.nextInt();
					g.addNode(id, x, y);
				}
				while(sc.hasNext()){
					int id1 = sc.nextInt();
					int id2 = sc.nextInt();
					int weight = sc.nextInt();
					g.addEdge(id1, id2, weight);
				}
			}
			sc.close();
		}
		catch(IOException IOex){
			throw new IOException(IOex);
		}

	}
	
	
}

	
	

