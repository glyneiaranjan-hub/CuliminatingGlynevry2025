import java.util.ArrayList;

/*
 * Creates a Board as a 4 by 4 grid 2D array.
 * Provides methods to shuffle the into random position on the grid and retrieve the die at a 
 * specific position through row and column coordinate
 */
public class Board {
	private Die[][] board = new Die[4][4];
	
	private String[][] diceFaces = {
			{"A","A","E","E","G","N"},
			{"E","L","R","T","T","Y"},
			{"A","O","O","T","T","W"},
			{"A","B","B","J","O","O"},
			{"E","H","R","T","V","W"},
			{"C","I","M","O","T","U"},
			{"D","I","S","T","T","Y"},
			{"E","I","O","S","S","T"},
			{"D","E","L","R","V","Y"},
			{"A","C","H","O","P","S"},
			{"H","I","M","N","Qu","U"},
			{"E","E","I","N","S","U"},
			{"E","E","G","H","N","W"},
			{"A","F","F","K","P","S"},
			{"H","L","N","N","R","Z"},
			{"D","E","I","L","R","X"}
	};
	
	/*
	 * Method to shuffle the dice randomly on the 4 by 4 board
	 */
	private void shuffleDice() {
		//creates an array list of integers from 0 - 15, keeps track of available dice
		ArrayList<Integer> bag= new ArrayList<>();
		for (int i = 0; i <16; i++) {
			bag.add(i);
		}
		
		//a 2D array holding shuffled dice
		String[][] shuffled = new String[16][6];
		
		// Randomly select and remove a die index for each position
		for(int pos = 0; pos < 16; pos++) {
			int j = (int)(Math.random() * bag.size()); //random index between 0 - 15  in bag
			int dieIndex = bag.get(j); // get die index
			bag.remove(j); // remove it from bag
			shuffled[pos]= diceFaces[dieIndex]; // place die
		}
		
		//puts shuffled dice positions in original diceFaces 2D array 
		diceFaces = shuffled;
	}
	
	/*
	 * Constructs a board with 4 rows and 4 columns of Die objects 
	 */
	
	public Board() {
		shuffleDice();
		
		int index = 0;
		for(int r = 0; r <4; r++) {
			for (int c =0; c<4; c++) {
				board [r][c] = new Die (diceFaces[index]);
				index++;
			}
		}
	}
	
	/*
    * Provides the die at a specific column and row (coordinate on the board) for easy facilitation 
    * with check class
    * @param int r representing the row 
    * @param int c representing the column s
    * @return Die placed at that coordinate on the board
    */
	
	public Die getDie(int r, int c) {
		return board [r][c];
	}
}
