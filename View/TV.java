package View;

import Controller.Controller;
import Model.Product;
import Model.TvClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TV extends Tab {
	   private Controller controller;
	
	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}
	
	private void createButtonActionPerformed(TvClass tv, Product product) 
	   {
	      int result = controller.createTV(tv, product);
	      
	      if ( result == 1 ){  
	    	  AlertBox.display("TV", "TV Added!");
	      }
	        
	      else {    	  
	    	  AlertBox.display("TV", "TV Not Added!");
	      }
	   }
	
	public TV() {
		
		controller = new Controller();
		setText("TV");
		
		BorderPane border = new BorderPane();

		Label title = new Label("TV Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));
	    
	    Label idLbl = new Label("Enter the Product ID:");
		GridPane.setConstraints(idLbl, 1, 0);
		
		TextField idTxtFld = new TextField();
		GridPane.setConstraints(idTxtFld, 2, 0);
	    
	    Label makeLbl = new Label("Enter the Make:");
	    GridPane.setConstraints(makeLbl, 1, 1); 
	    
	    TextField makeTxtFld = new TextField();
		GridPane.setConstraints(makeTxtFld, 2, 1);
		
		Label screenLbl = new Label("Enter the Screen Size:");
		GridPane.setConstraints(screenLbl, 1, 2);
		
	    TextField screenTxtFld = new TextField();
		GridPane.setConstraints(screenTxtFld, 2, 2);
		
		Label typeLbl = new Label("Enter the Type:");
		GridPane.setConstraints(typeLbl, 1, 3);
		
		TextField typeTxtFld = new TextField();
		GridPane.setConstraints(typeTxtFld, 2, 3);
		
		Label capable3DLbl = new Label("Is it Capable of 3D:");
		GridPane.setConstraints(capable3DLbl, 1, 4);
		
		TextField capable3DTxtFld = new TextField();
		GridPane.setConstraints(capable3DTxtFld, 2, 4);
		
		Label commentLbl = new Label("Make a Comment:");
		GridPane.setConstraints(commentLbl, 1, 5);
		
		TextField commentTxtFld = new TextField();
		GridPane.setConstraints(commentTxtFld, 2, 5);

		Label priceLbl = new Label("Enter the Price:");
		GridPane.setConstraints(priceLbl, 1, 6);
		
		TextField priceTxtFld = new TextField();
		GridPane.setConstraints(priceTxtFld, 2, 6);
		
		grid.getChildren().addAll(makeLbl, makeTxtFld, screenLbl, screenTxtFld, typeLbl, typeTxtFld, capable3DLbl, capable3DTxtFld, 
				commentLbl, commentTxtFld, priceLbl, priceTxtFld, idLbl, idTxtFld);
	    border.setCenter(grid);
	    
	    Button create = new Button ("Create");
	    create.setOnAction((ActionEvent event) ->{
	    	
	    	if(makeTxtFld.getText().equals("") || screenTxtFld.getText().equals("") || typeTxtFld.getText().equals("") || 
	    			capable3DTxtFld.getText().equals("") || commentTxtFld.getText().equals("") || priceTxtFld.getText().equals("")
	    			|| idTxtFld.getText().equals(""))
	    	{
	    		AlertBox.display("Error", "Empty Field!");
	    	}
	    	
	    	else{
			String make = makeTxtFld.getText();
			String screen = screenTxtFld.getText();
			String type = typeTxtFld.getText();
			String capable3D = capable3DTxtFld.getText();
			String comment = commentTxtFld.getText();
			Double price = Double.valueOf(priceTxtFld.getText());
			String id = idTxtFld.getText();
			
			TvClass tv = new TvClass(make, screen, type, capable3D);
			Product product = new Product(make, comment, price, id);
				    
	    createButtonActionPerformed(tv, product);
	    	}
	    });
	    
		Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			makeTxtFld.setText("");
			screenTxtFld.setText("");
			typeTxtFld.setText("");
			capable3DTxtFld.setText("");
			commentTxtFld.setText("");
			priceTxtFld.setText("");
			idTxtFld.setText("");
		});

		Button quit = new Button ("Quit");
		quit.setOnAction(e -> closeProgram());
		
		//**********************************************************
		// Creating the new layout HBox and adding the Reset and
		// Quit button to it
		//**********************************************************

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(create, reset, quit);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		setContent(border);
		
	}
}