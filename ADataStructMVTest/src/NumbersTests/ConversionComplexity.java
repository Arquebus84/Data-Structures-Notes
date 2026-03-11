package NumbersTests;

public class ConversionComplexity {

	public static void main(String[] args) {
		// TODO Convert Between strings and ints, if string is non-numeric, change it to its hexadecimal value
		
		String str = "Hello World";
		//Method 1: Directly convert each character into int
		/*int num = 0;
		for(int i = 0; i < str.length(); i++) {
			num = (int)(str.charAt(i));
			System.out.print(num + " ");
		}
		System.out.println();*/
		
		//Method 2: Create two arrays
		char[] c_str = str.toCharArray();
		int[] i_str = new int[c_str.length];
		for(int i = 0; i < c_str.length; i++) {
			i_str[i] = (int)(c_str[i]);
		}
		
		for(int i : i_str) {
			System.out.print(i + " ");
		}

	}

}
