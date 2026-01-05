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
	    Font font = new Font("Times New Roman", 15);
		
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
         Button btnI = new Button("Instructions →");
         btnI.setFont(font);

         Button btnR = new Button("Rules →");
         btnR.setFont(font);
         btnR.setVisible(false);
         
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
         
         btnHolder.getChildren().addAll(btnI,btnS, btnR);
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         
         btnPlayer.getChildren().addAll(btnSingle,btnMulti);
         btnPlayer.setAlignment(Pos.CENTER);
         
         VBox timer = new VBox(10);
         TextField timeM = new TextField();
         timeM.setPrefWidth(50);
         timeM.setVisible(false);
         
         timer.getChildren().add(timeM);
        
		 
		btnI.setOnAction(e-> {
            try {
            	i1.setText("Find as many words as possible in the given time.");
            	i2.setText("Longer words are worth more points.");
            	btnI.setVisible(false);
            	btnR.setVisible(true);
  
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnR.setOnAction(e-> {
            try {
            	i1.setText("1. Words must be at least 3 letters in length");
            	i2.setText("2. A die's letter can only be used once per word");
            	i3.setText("3. Words cannot be proper nouns or abbreviations");
            	btnR.setVisible(false);
            	btnS.setVisible(true);
  
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnS.setOnAction(e-> {
            try {
            	i1.setText("");
            	i2.setText("");
            	i3.setText("");
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
				i1.setText("How long do you want to play for?");
				timeM.setVisible(true);
				int timeS = Integer.valueOf(timeM.getText());

				
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

		
		root.getChildren().addAll(lblTitle,i1,i2,i3,timer);

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
