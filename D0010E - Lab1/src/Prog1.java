
public class Prog1 {

	public static int f1(int n){
		if(n==1) return 1;
		if(n%2==0) return n/2;
		else return 3*n+1;
	}
	
	public static int f2(int n){
		if(n==1) return 1;
		else return f1(f1(n));
	}
	
	public static int f4(int n){
		if(n==1) return 1;
		else return f2(f2(n));
	}
	
	public static int f8(int n){
		if(n==1) return 1;
		else return f4(f4(n));
	}
	
	public static int f16(int n){
		if(n==1) return 1;
		else return f8(f8(n));
	}
	
	public static int f32(int n){
		if(n==1) return 1;
		else return f16(f16(n));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[0]);
		int nrOfArgs = args.length; 
		if(nrOfArgs == 1)
			
			System.out.println("f1="+f1(n)+" f2="+f2(n)+" f4="+f4(n)+" f8="+f8(n)+" f16="+f16(n)+" f32="+f32(n));
		else
			System.out.println("Wrong nr of arguments!");

		
		
	}

}
