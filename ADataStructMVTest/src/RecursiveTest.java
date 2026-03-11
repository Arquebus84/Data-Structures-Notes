/**
 * Description: Create a recursive method that prints out "Hello" with specified n times
 */
public class RecursiveTest {

	public static void main(String[] args) {
		//long start = System.currentTimeMillis();
		/*recursiveHello(5);
		int num = 5;
		System.out.println("5! = " + recursiveFactorial(num));
		
		
		int n = 6;
		System.out.printf("Recursive fibonacci(%d) = %d%n", n, recursiveFibonacci(n));*/
		//System.out.printf("Looped fibonacci(%d) = %d%n", n, LoopFib(n));
		
		//long end = System.currentTimeMillis();
		//long totalTime = (end - start);
		
		//System.out.println("Total Time: " + totalTime + " millis");
		//loopFib(6);
		HowRecursionWorks(5);
	}
	
	public static void HowRecursionWorks(int n) {
		System.out.println("Recursively Searches: " + n);
		if(n == 0) {
			System.out.println("Starts to Unravel: " + n);
			return;
		}
		//Recursive Function
		HowRecursionWorks(n-1);
		
		System.out.println("Unravels: " + n);
	}
	
	public static void recursiveHello(int n) {
		if(n == 0) {
			return;
		}else {
			System.out.println("Hello");
			recursiveHello(n - 1);
		}
	}
	
	public static int recursiveFactorial(int n) {
		if(n == 0) {
			return 1;
		}else {
			return n * recursiveFactorial(n - 1);
		}
	}
	
	//Create a Fibonacci sequence where the next number is the sum of the two previous numbers
	//	ex: if n = 7, then it will be 0, 1, 1, 2, 3, 5, 8, 13 (1 + 2 = 3, 2 + 3 = 5, etc.)
	//						indices:  0, 1, 2, 3, 4, 5, 6, 7
	public static int recursiveFibonacci(int n) {
		if(n == 0) {
			return 0;
		}else if(n == 1) {
			return 1;
		}else {
			return recursiveFibonacci(n - 2) + recursiveFibonacci(n - 1);
		}
	}
	
	//Creating fibonacci sequence with loop instead of recursion
	public static void loopFib(int n) {
		/*
		 * 0, 1, 2, 3, 4, 5, 6, 7
		 * 0, 1, 2, 3, 5, 8, 13, 21
		 * 
		 * (1 + 2) = 3												==	(1 + 2)
		 * (2 + 3) = 5 = (2 + (1 + 2))								==	(1 + 2 + 2)
		 * (3 + 5) = 8 = (2 + (1 + 2) + (1 + 2)) 					==	(1 + 1 + 2 + 2 + 2)
		 * (5 + 8) = 13 = (2 + (1 + 2) + (1 + 2) + 2 + (1 + 2))		==	(1 + 1 + 1 + 2 + 2 + 2 + 2 + 2)
		 */		
		int prev = 0;
		int next = 1;
		for(int i = 0; i < n; i++) {
			//Switch both numbers, but add the previous to the next to create sum of previous numbers for new results
			int temp = prev;
			prev = next;
			next += temp;	//Add 1 to 2 to create 3, then 2 to 3 to create 5, ...
			
			System.out.println(prev + " : " + next);
		}
		System.out.printf("Fibonacci number of size %d is %d", n, next);
	}

}
