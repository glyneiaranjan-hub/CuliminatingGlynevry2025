import java.util.ArrayList;

/*
 * Purpose : Checks if the words inputed by the player meet the requirements 
 */

public class CheckWord {
	private String word;
	private int length;
	
	/*
	 * Turns the word into an object to be manipulated by the methods in
	 * the CheckWord Class
	 */
	CheckWord (String w){
		word = w;
		length = w.length();
	}

	/*
	 * checks if the word is at least 3 letters long
	 */
	public boolean checkLength () {
		if (length >= 3) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * checks if the word is in the words.txt file
	 */
	public boolean checkFile (ArrayList<String> a) {
		boolean isWordPresent = a.contains(word);
		return isWordPresent;
	}
}
