import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class WindowSlidingAlg {

	public static void main(String[] args) {
		// Slide through the integer array and display numbers in 3D coordinates (x, y, z)
		
		int[] array = {2, 0, 8, -4, 9, -16, 11, 5};
		displayCoordinates(array);
		displayCoordinates(new int[] {2, 9, 7, 0});
	}
	
	public static void displayCoordinates(int[] array) {
		final int N = 3;
		
		if(array.length < 3) {return;}
		
		for(int i = N-1; i < array.length; i++) {
			System.out.print("(" + array[i - N + 1] + "," + array[i - 1] + "," + array[i] + ") ");
		}
		
		System.out.println("\nTest 2:");
		for(int i = N; i <= array.length; i++) {
			System.out.print("(" + array[i - N] + "," + array[i - 2] + "," + array[i-1] + ") ");
		}
	}

}
