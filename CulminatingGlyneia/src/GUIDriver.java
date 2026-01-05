import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.text.*;

/*
 * Notes :
 * maybe merge the instructions and the rules onto one page
 */
public class GUIDriver extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		VBox root = new VBox(10);
		
		/*
	    * The font for the text of the title
	    */         
	    Font titleFont = new Font("Impact", 60);
	    
	    /*
	    * The font for the text of the buttons and Error Message
	    */ 
	    Font font = new Font("Times New Roman", 17);
		
		/*
	    * The Label holding the title
	    */         
		Label lblTitle = new Label("BOGGLE");
	    lblTitle.setFont(titleFont);
	    lblTitle.setUnderline(true);
        lblTitle.setTextFill(Color.BLUE);
        
        /*
         * The Labels holding the rules + instructions
         */
        
        Label i1 = new Label("");
	    i1.setFont(font);
        i1.setTextFill(Color.BLACK);
        
        Label i2 = new Label("");
        i2.setFont(font);
        i2.setTextFill(Color.BLACK);
        
        Label i3 = new Label("");
	    i3.setFont(font);
        i3.setTextFill(Color.BLACK);
        
        Label i4 = new Label("");
	    i4.setFont(font);
        i4.setTextFill(Color.BLACK);

        
        /*
         * The Label holding the error message
         */
         Label errorMessage = new Label ("");
         errorMessage.setFont(font);
         errorMessage.setUnderline(true);
         errorMessage.setTextFill(Color.RED);
         
         HBox btnHolder = new HBox(10);
         btnHolder.setStyle("-fx-background-color: lightskyblue;");
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         Button btnIR = new Button("Instructions/Rules →");
         btnIR.setFont(font);
         
         Button btnS = new Button("Settings →");
         btnS.setFont(font);
         btnS.setVisible(false);
         
         HBox btnPlayer = new HBox(10);
         btnPlayer.setStyle("-fx-background-color: lightskyblue;");
         btnPlayer.setAlignment(Pos.CENTER);
         
         Button btnSingle = new Button("Single player");
         btnSingle.setFont(font);
         btnSingle.setVisible(false);
         
         Button btnMulti = new Button("Multiplayer");
         btnMulti.setFont(font);
         btnMulti.setVisible(false);
         
         btnHolder.getChildren().addAll(btnIR,btnS);
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         
         btnPlayer.getChildren().addAll(btnSingle,btnMulti);
         btnPlayer.setAlignment(Pos.TOP_CENTER);
         
         VBox timerV = new VBox(10);
         timerV.setAlignment(Pos.CENTER);
         
         TextField timeM = new TextField();
         timeM.setMaxWidth(50);
         timeM.setVisible(false);
   
         timerV.getChildren().add(timeM);
        
		 
		btnIR.setOnAction(e-> {
            try {
            	i1.setText("Instructions : ");
            	i1.setUnderline(true);
            	i2.setText("1) Find as many words as possible on the grid in the given time.\n "
            	+"2)The words must be either vertically, horisontally, or diagonally connected. \n" 
            	+ "3)Longer words are worth more points.");
            	i3.setText("Rules : ");
            	i3.setUnderline(true);
            	i4.setText("1)Words must be at least 3 letters in length\n"
            	+ "2)A die's letter can only be used once per word\n"
            	+ "3)Words cannot be proper nouns or abbreviations");
            	btnIR.setVisible(false);
            	btnS.setVisible(true);
  
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnS.setOnAction(e-> {
            try {
            	i1.setVisible(false);
            	i2.setVisible(false);
            	i3.setVisible(false);
            	i4.setVisible(false);
            	btnS.setVisible(false);
            	btnMulti.setVisible(true);
            	btnSingle.setVisible(true);
  
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnSingle.setOnAction(e-> {
			try {
				

				
			} catch (Exception exp) {
				 System.out.println("Error: " + exp.getMessage());
	             errorMessage.setText("Error: " + exp.getMessage());
			}
		});
		
		btnMulti.setOnAction(e-> {
			try {
				i1.setText("How long do you want to play for?");
				timeM.setVisible(true);
				int timeS = Integer.valueOf(timeM.getText());
				
				
			} catch (Exception exp) {
				 System.out.println("Error: " + exp.getMessage());
	             errorMessage.setText("Error: " + exp.getMessage());
			}
		});

		
		root.getChildren().addAll(lblTitle,i1,i2,i3,i4, timerV);

        root.setAlignment(Pos.CENTER);

        root.setStyle("-fx-background-color: lightskyblue;");
        
		bp.setTop(root);
		bp.setCenter(btnPlayer);
		bp.setBottom(btnHolder);
		
       
        
        Scene scene = new Scene (bp, 600, 600);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
