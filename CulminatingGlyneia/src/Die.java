
public class Die {
	
	private String[] faces;
	private String face;

    public Die(String[]f){
    	faces = f;
        roll();
    }

    public void roll() {
        int rand = (int)(Math.random() * faces.length);
        face = faces[rand];
    }

    public String getFace() {
        return face;
    }
    
}
