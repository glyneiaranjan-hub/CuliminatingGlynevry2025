import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.text.*;


public class GUIDriver extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		VBox root = new VBox(10);
		
		/*
	    * The font for the text of the title
	    */         
	    Font titleFont = new Font("Times New Roman", 27);
	    
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
         * The Label holding the error message
         */
         Label errorMessage = new Label ("");
         errorMessage.setFont(font);
         errorMessage.setUnderline(true);
         errorMessage.setTextFill(Color.RED);
         
         HBox btnHolder = new HBox(10);
         btnHolder.setStyle("-fx-background-color: lightskyblue;");
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         Button btnNext = new Button("NEXT");
         btnNext.setFont(font);
         
         btnHolder.getChildren().addAll(btnNext);
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         		
		root.getChildren().add(lblTitle);
		

        root.setAlignment(Pos.CENTER);

        root.setStyle("-fx-background-color: lightskyblue;");
        
		bp.setCenter(root);
		bp.setBottom(btnHolder);
       
        
        Scene scene = new Scene (bp, 600, 600);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
