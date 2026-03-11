
import java.util.*;

public class RegularExpressionsPractice {

	/**	Regex Syntax Elements:
	Character Classes: 		*Could also write [a-z]
			[abc] characters from a to z, [^abc] beginning with characters from a to z
			[0-9], 
			\d (digit) [0-9], \D (non-digit) [\^0-9]
			\s (whitespace), \S (non-whitespace), 
			\w (word character), \W (non-word character).						
			\b (word boundary) Find a match at the beginning of a word like this: \bWORD, or at the end of a word like this: WORD\b
			\B (non-word boundary)
	Metacharacters: 
			. (any character), 
			^ (beginning of line/string), 
			$ (end of line/string), 
			| (OR), 
			! (NOT)
			`\` (escape character).
			-	(hyphen)
	Quantifiers: 
			+ (one or more), 		n+ one or more occurrence of n
			* (zero or more), 		n* zero or more occurrence of n
			? (zero or one), 		n? zero or one occurrence of n
			{n} (occur exactly n times),
			{n,} (occur n or more times), 
			{n,m} (occur between n and m times). char s==> s{2,4} s occurs more than 2 time but no more than 4 times
	 */
	
	public static void main(String[] args) {
		String state = "In the state of Georgia, there have been 2,000 car accidents from the years 2017 to 2025";
		System.out.println(state.replaceAll("[\\d\\,]", ""));
		//int n = Integer.parseInt(state.split(state));
		String m = state.replaceAll("[\\D\\,]", "");
		long n = Long.parseLong(m);					//Recall the byte size of int (32-bit 2^31 - 1), long is 64-bit (2^63 - 1)
		System.out.println(n);
		
		//Print non-repeating characters
		HashMap<Character, Integer> chars = new HashMap<>();
		int k = 0;
		for(char c : state.toCharArray()) {
			if(!chars.containsKey(c)) {
				chars.put(c, k++);
			}
		}
		
		chars.forEach((Character c, Integer i)->{
			System.out.print(c + " ");
		});

	}

}
