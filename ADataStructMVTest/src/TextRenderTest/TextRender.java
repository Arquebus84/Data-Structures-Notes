package TextRenderTest;

public class TextRender {

	public static void main(String[] args) {
		// set the string text, then cut it into an array of char, display one at a time (gives the appearance that the text is slowly appearing)
		String text = "Hello there, welcome to the Java program!";
		char[] charText = text.toCharArray();
		
		for(int i = 0; i < charText.length; i++) {
			System.out.print(charText[i]);
			PauseForMilliSec(100);
		}
		
	}
	
	public static void PauseForMilliSec(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		}catch(Exception ex) {
			System.out.println();
		}
	}

}
