public class Prog5 {

	static int rec_raise_nr_of_rec_calls = 0;
	static int rec_raise_eff_nr_of_rec_calls = 0;
	
	public static double rec_raise(double x, int k){
		rec_raise_nr_of_rec_calls++;
		if(k==0)
			return 1.0;
		else
			return (x * rec_raise(x,k-1));
	}
	
	public static double rec_raise_eff(double x, int k){
		double temp;
		rec_raise_eff_nr_of_rec_calls++;
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
	 public static double log2(double d){
		 return Math.log(d)/Math.log(2.0);
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
			for(int i=1; i<=k; i++){
				rec_raise_nr_of_rec_calls = 0;
				rec_raise_eff_nr_of_rec_calls = 0;
				// System.out.println("(x= "+ x + " and k = "+ i +") --> x^k= "+ rec_raise(x,i) + " (nr of rec calls = " + rec_raise_nr_of_rec_calls + ")");
				System.out.println("(x= "+ x + " and k = "+ i +") --> (Effective calc) x^k= "+ rec_raise_eff(x,i) + " (nr of rec calls = " + rec_raise_eff_nr_of_rec_calls + ")");
				System.out.println("N2 = " + (2 + Math.floor(log2(i))));
			}
			
		}
		else
			System.out.println("Prog4: Wrong nr of arguments!");
		
	}

}
