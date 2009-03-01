
public class Prog2 {

	
	public static int f1(int n){
		if(n==1) return 1;
		if(n%2==0) return n/2;
		else return 3*n+1;
	}
	
	public static int iterate_f(int a0, int n) {
		int an = a0;
		for(int i=n; i>0; i--)
			an = f1(an);
		return an;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nrOfArgs = args.length;
		int first, second;
		if(nrOfArgs == 2){
			first = Integer.parseInt(args[0]);
			second = Integer.parseInt(args[1]);
			System.out.println("(a0= "+ first + " and number of iterations = "+ second +") --> "+iterate_f(first,second));
		}
		else
			System.out.println("Prog2: Wrong nr of arguments!");

	}		
		



}
