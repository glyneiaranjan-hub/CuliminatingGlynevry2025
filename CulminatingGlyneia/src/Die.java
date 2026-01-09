
/*
 * Creates Dice with 6 faces providing methods to roll and retrieve the rolled letter
 */
public class Die {
	
	private String[] faces;
	private String face;
	
	/*
	 * Constructs a Die object with an array
	 * @param String array f consisting of the die's 6 faces
	 */

    public Die(String[]f){
    	faces = f;
        roll();
    }
    
    /*
     * Roll method generating a random index between 0 - 5 of the array with the faces
     * to assign to the rolled and result face
     */

    public void roll() {
        int rand = (int)(Math.random() * faces.length);
        face = faces[rand];
    }
    
    /*
     * Provides the rolled face
     * @return String representing the resulting letter on the face
     */

    public String getFace() {
        return face;
    }
    
}
