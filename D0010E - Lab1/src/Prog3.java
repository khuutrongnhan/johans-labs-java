
public class Prog3 {

	static int lifeLength=0;
	
	public static int f1(int n){
		if(n==1) return 1;
		if(n%2==0) return n/2;
		else return 3*n+1;
	}
	
	public static int iter_life_length(int a0) {
		int an = a0;
		int life_length = 0;
		while(an !=1){
			an = f1(an);
			life_length++;
		}
		return life_length;
	}
	
	public static int rec_life_length(int a0){
		if (a0 == 1)
			return lifeLength;
		else
			lifeLength++;
			return rec_life_length(f1(a0));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nrOfArgs = args.length;
		int first;
		if(nrOfArgs == 1){
			first = Integer.parseInt(args[0]);
			System.out.println("The life length of "+ first + " is: "+ iter_life_length(first));
			System.out.println("The life length of "+ first + " is (recursive calc):"+ rec_life_length(first));
		}
		else
			System.out.println("Prog3: Wrong nr of arguments!");

	}		
		



}
