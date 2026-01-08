import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
		bp.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
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
        lblTitle.setAlignment(Pos.CENTER);
        bp.setTop(lblTitle);
		BorderPane.setAlignment(lblTitle, Pos.CENTER);
        
        
        /*
         * The Labels holding the rules + instructions
         */
        
        VBox begin = new VBox(10);
        Label i1 = new Label("Instructions : ");
	    i1.setFont(font);
	    i1.setUnderline(true);
        i1.setTextFill(Color.BLACK);
        
        Label i2 = new Label("1) Find as many words as possible on the grid in the given time.\n "
            	+"2)The words must be either vertically, horisontally, or diagonally connected. \n" 
            	+ "3)Longer words are worth more points.\n"
            	+ "4)If multiplayer, duplicate words are erased.");
        i2.setFont(font);
        i2.setTextFill(Color.BLACK);
        
        Label i3 = new Label("Rules : ");
	    i3.setFont(font);
	    i3.setUnderline(true);
        i3.setTextFill(Color.BLACK);
        
        Label i4 = new Label("1)Words must be at least 3 letters in length\n"
            	+ "2)A die's letter can only be used once per word\n"
            	+ "3)Words cannot be proper nouns or abbreviations\n");
	    i4.setFont(font);
        i4.setTextFill(Color.BLACK);
        
        Label i5 = new Label("IF INPUTTED WORD IS NOT DISPLAYED, IT IS INVALID");
	    i5.setFont(font);
        i5.setTextFill(Color.RED);
        
        begin.getChildren().addAll(i1,i2,i3,i4,i5);
        begin.setAlignment(Pos.CENTER);
        begin.setVisible(false);
        
        /*
         * The Label holding the error message for incorrect input
         */
         Label errorMessage = new Label ("");
         errorMessage.setFont(font);
         errorMessage.setUnderline(true);
         errorMessage.setTextFill(Color.RED);
         
         /*
          * Starting buttons with Instructions and Settings
          */
         
         HBox btnHolder = new HBox(10);
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         
         Button btnIR = new Button("Instructions/Rules →");
         btnIR.setFont(font);
         btnIR.setVisible(true);
         
         Button btnS = new Button("Settings →");
         btnS.setFont(font);
         btnS.setVisible(false);
         btnS.setManaged(false);

         
         btnHolder.getChildren().addAll(btnIR,btnS);
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         btnHolder.setPadding(new Insets(30));
         bp.setBottom(btnHolder);
         
         
         /*
          * buttons for player modes
          */
         
         HBox btnPlayer = new HBox(10);
         btnPlayer.setAlignment(Pos.CENTER);
         
         Button btnSingle = new Button("Single player");
         btnSingle.setFont(font);
         
         Button btnMulti = new Button("Multiplayer");
         btnMulti.setFont(font);
         
         btnPlayer.getChildren().addAll(btnSingle,btnMulti);
         btnPlayer.setAlignment(Pos.TOP_CENTER);
         btnPlayer.setVisible(false);
         
         VBox playerContainer = new VBox(btnPlayer);
         playerContainer.setAlignment(Pos.CENTER);
         bp.setCenter(playerContainer);
         BorderPane.setAlignment(playerContainer, Pos.CENTER);
         
         /*
          * timer input 
          */
         VBox timerV = new VBox(10);
         timerV.setAlignment(Pos.CENTER);
         
         TextField timeM = new TextField();
         timeM.setMaxWidth(50);
   
         timerV.getChildren().add(timeM);
         timerV.setVisible(false);
         
         /*
          * game board
          */
         
         HBox gameBox = new HBox (30);
         gameBox.setAlignment(Pos.CENTER);
         
         /*
          * answer input with scrolling list of typed answers 
          */
         
         VBox type = new VBox(10);
         type.setAlignment(Pos.TOP_RIGHT);
         
         TextField answer = new TextField();
         answer.setMaxWidth(150);
         
         Label todo = new Label("Type answers here:   ");
         todo.setFont(font);
         todo.setTextFill(Color.BLACK);
         
         ObservableList<String> answers = FXCollections.observableArrayList();
         ListView<String> listView = new ListView<>(answers);
         listView.setPrefHeight(400);
         listView.setMaxWidth(150);
 
         answer.setOnKeyPressed( event -> {
        	 if(event.getCode() == KeyCode.ENTER ) {
        		 String word = answer.getText();
        		 if (!word.isEmpty() && !answers.contains(word)) {
        			 answers.add(word);
        			 answer.clear();
        			 }
        		 }
        	 });
         
         answers.addListener((ListChangeListener<String>) c -> {
        	    listView.scrollTo(answers.size()-1);
        	});
         
         type.setPadding(new Insets(30));
         type.getChildren().addAll(todo,answer,listView);
         /*type.setManaged(false);
         type.setVisible(false);*/
         
         /*
          * Board with dice
          */
         Board board = new Board();
         Label[][]tiles = new Label [4][4];
         
         VBox boardVBox = new VBox(10);
         boardVBox.setAlignment(Pos.TOP_LEFT);
         
         for (int r = 0; r < 4; r++) {
        	 HBox row = new HBox(10);
        	 row.setAlignment(Pos.CENTER);
        	 
        	 for (int c = 0; c <4; c++) {
        		 Label tile = new Label(board.getDie(r,c).getFace());
        		 tile.setMinSize(70, 70);
        		 tile.setAlignment(Pos.CENTER);
        		 tile.setStyle("-fx-border-color: black;" +
        				 		"-fx-font-size: 24;" +
        				 		"-fx-background-color: white;");
        		 
        		 tiles[r][c] = tile;
        		 row.getChildren().add(tile);
        	 }
        	 boardVBox.getChildren().add(row);
         }
         
         /*boardVBox.setManaged(false);
         boardVBox.setVisible(false);*/
         
         gameBox.getChildren().addAll(boardVBox, type);
         gameBox.setVisible(true);
         
         /*boardVBox.setVisible(false);
         boardVBox.setManaged(false);
         type.setVisible(false);
         type.setManaged(false);*/
         
        
		 
		btnIR.setOnAction(e-> {
            try {
            	begin.setVisible(true);
            	bp.setCenter(begin); 
            	btnIR.setVisible(false);
            	btnS.setVisible(true);
            	btnS.setManaged(true);

  
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnS.setOnAction(e-> {
            try {
            	begin.setVisible(false);
            	btnS.setVisible(false);
            	btnPlayer.setVisible(true);
            	bp.setCenter(playerContainer);
  
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnSingle.setOnAction(e-> {
			try {
				btnPlayer.setVisible(false);
				gameBox.setVisible(true);
            	bp.setCenter(gameBox);
            	

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

        Scene scene = new Scene (bp, 700, 600);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
