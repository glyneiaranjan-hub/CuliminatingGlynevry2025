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
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

public class GUIDriver extends Application {
	static int point=0;
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
	     * Font for the "Game Over"
	     */
	    Font fontGO = new Font("Times new Roman", 40);
		
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
        i1.setVisible(false);
        
        Label i2 = new Label("1)You have 3 minutes to find as many words as possible on the grid in the given time.\n"
            	+"2)The words must be either vertically, horisontally, or diagonally connected.\n" 
            	+ "3)Longer words are worth more points.\n"
            	+ "4)If multiplayer, duplicate words are erased.");
        i2.setFont(font);
        i2.setTextFill(Color.BLACK);
        i2.setVisible(false);
        
        Label i3 = new Label("Rules : ");
	    i3.setFont(font);
	    i3.setUnderline(true);
        i3.setTextFill(Color.BLACK);
        i3.setVisible(false);
        
        Label i4 = new Label("1)Words must be at least 3 letters in length\n"
            	+ "2)A die's letter can only be used once per word\n"
            	+ "3)Words cannot be proper nouns or abbreviations\n");
	    i4.setFont(font);
        i4.setTextFill(Color.BLACK);
        i4.setVisible(false);
        
        Label i5 = new Label("IF INPUTTED WORD IS NOT DISPLAYED, IT IS INVALID");
	    i5.setFont(font);
        i5.setTextFill(Color.RED);
        i5.setVisible(false);
        
        Label points = new Label("0");
        points.setVisible(false);
        points.setFont(fontGO);

        begin.getChildren().addAll(i1,i2,i3,i4,i5, points);
        begin.setAlignment(Pos.CENTER);
        begin.setVisible(false);
        
        /*
         * Timer displaying how much time the player has left
         */
        Label timerCount = new Label("");
        timerCount.setFont(font);
        timerCount.setTextFill(Color.BLACK);
        
        VBox timeDisplay = new VBox(10);
        timeDisplay.getChildren().add(timerCount);
        timeDisplay.setAlignment(Pos.BOTTOM_LEFT);
        timeDisplay.setVisible(false);
        timeDisplay.setPadding(new Insets(30));
        bp.setBottom(timeDisplay);
        
        
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
         
         Button btnStart = new Button("Start →");
         btnStart.setFont(font);
         btnStart.setVisible(false);
         btnStart.setManaged(false);

         
         btnHolder.getChildren().addAll(btnIR,btnStart);
         btnHolder.setAlignment(Pos.BOTTOM_RIGHT);
         btnHolder.setPadding(new Insets(30));
         bp.setBottom(btnHolder);
   
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
         listView.setPrefHeight(300);
         listView.setMaxWidth(150);
         
         /*
          * File scanner to see if inputed word is in the file
          */
         ArrayList<String> words = new ArrayList<>();
         File f2 = new File("words (1).txt");
         Scanner in2 = new Scanner(f2);

         while (in2.hasNextLine()){
             words.add(in2.nextLine().trim());
         }

         answer.setOnKeyPressed( event -> {
        	 if(event.getCode() == KeyCode.ENTER ) {
        		 String word = answer.getText();
        		 if (!word.isEmpty() && !answers.contains(word)) {
        			 /*
        			  * checks if the inputed word is at least 3 letters long
        			  */
        			 CheckWord word1 = new CheckWord(word);
        			 boolean length = word1.checkLength();
        			 if (length == false) {
        				 /*
        				  * Outputs "Invalid Word" if the word is not at least 3 letters long
        				  */
        				 bp.setBottom(begin);
        				 begin.setVisible(true);
        				 i1.setText("Invalid word");
    					 i1.setVisible(true);
    				     i1.setTextFill(Color.RED);
        			 }else if (length == true) {
        				 /*
        				  * Continues the checking, Checks if the word is in the file
        				  */
        				 boolean inFile = word1.checkFile(words);
        				 bp.setBottom(timeDisplay);
        				 if (inFile == false) {
        					 /*
        					  * Outputs "Invalid Word" if the word is not found in the file
        					  */
            				 bp.setBottom(begin);
            				 begin.setVisible(true);
        					 i1.setText("Invalid word");
        					 i1.setVisible(true);
        				     i1.setTextFill(Color.RED);
        				 }else if (inFile == true) {
        					 /*
        					  * Lets the game continue and adds the points up
        					  */
        					 bp.setBottom(timeDisplay);
        					 answers.add(word);
        					 point+=word.length();
        				 }

        			 }
        			 answer.clear();
        			 }
    			 points.setText(Integer.toString(point));
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
         
         /*
          * for every column and row, creates die faces
          */
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
            	/*
            	 * Shows the instructions and rules once the button is presses
            	 */
            	begin.setVisible(true);
            	bp.setCenter(begin); 
            	i1.setVisible(true);
            	i2.setVisible(true);
            	i3.setVisible(true);
            	i4.setVisible(true);
            	i5.setVisible(true);
            	btnIR.setVisible(false);
            	btnStart.setVisible(true);
            	btnStart.setManaged(true);
            	
            } catch (Exception exp){
                System.out.println("Error: " + exp.getMessage());
                errorMessage.setText("Error: " + exp.getMessage());
            }
        });
		
		btnStart.setOnAction(e-> {
            try {
            	/*
            	 * starts the game and displays the board on the screen
            	 */
            	begin.setVisible(false);
            	i1.setVisible(false);
            	i2.setVisible(false);
            	i3.setVisible(false);
            	i4.setVisible(false);
            	i5.setVisible(false);
            	btnStart.setVisible(false);
				gameBox.setVisible(true);
            	bp.setCenter(gameBox);
            	timerCount.setVisible(true);
            	timeDisplay.setVisible(true);
            	bp.setBottom(timeDisplay);
            	
            	// Java COUNTDOWN TIMER PROGRAM - SINGLEPLAYER
                int time = 180;

                Timer timer = new Timer();
                TimerTask task = new TimerTask(){
                    int count = time;

                    @Override
                    public void run(){
                        
                    	Platform.runLater(() -> {
                    		/*
                    		 * Displays the players score and "Game Over" once the timer ends
                    		 */
                        	timerCount.setText(Integer.toString(count));
	                        count--;
	                        if(count < 0){
	                            timer.cancel();
	                            bp.setCenter(begin);
	                            gameBox.setVisible(false);
	                            timerCount.setVisible(false);
	                            timeDisplay.setVisible(false);
	                            begin.setVisible(true);
	                            i1.setText("GAME OVER");
	                            i1.setTextFill(Color.RED);
	                            i1.setVisible(true);
	                            i1.setFont(fontGO);
	                            i2.setText("SCORE :");
	                            points.setVisible(true);
	                            i2.setVisible(true);
	                            i3.setVisible(false);
	                            i4.setVisible(false);
	                            i5.setVisible(false);
	                        }
                    	});
                    	
                    }
                    
                };
                timer.scheduleAtFixedRate(task, 0, 1000); // .(task, delay, period)
            
  
            } catch (Exception exp){
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
