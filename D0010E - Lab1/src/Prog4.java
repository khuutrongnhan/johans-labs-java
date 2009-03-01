
public class Prog4 {


	
	public static double rec_raise(double x, int k){

		if(k==0)
			return 1.0;
		else
			return (x * rec_raise(x,k-1));
	}
	
	public static double rec_raise_eff(double x, int k){
		double temp;

		if (k==0)
			return 1.0;
		if ((k%2) == 0){
			temp = rec_raise_eff(x,k/2); 
			return  temp * temp;
		} else {
			temp = rec_raise_eff(x,(k-1)/2);
			return x * temp * temp;
		}
			
	}
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nrOfArgs = args.length;
		double x;
		int k;
		if(nrOfArgs == 2){
			x = Double.parseDouble(args[0]);
			k = Integer.parseInt(args[1]);
			System.out.println("(x= "+ x + " and k = "+ k +") --> x^k= "+ rec_raise(x,k));
			System.out.println("(x= "+ x + " and k = "+ k +") --> (Effective calc) x^k= "+ rec_raise_eff(x,k));
		}
		else
			System.out.println("Prog4: Wrong nr of arguments!");
		
	}

}
