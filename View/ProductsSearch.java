package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductsSearch extends Tab {
	   private Controller controller;
	
	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}
	
	public ProductsSearch() {
		
		controller = new Controller();
		setText("Product Search");
		
		BorderPane border = new BorderPane();

		Label title = new Label("Search for Products");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));
	    
	    Label productLbl = new Label("Enter the Product ID:");
	    TextField productTxtFld = new TextField();
	    
	    HBox hb = new HBox(5);
	    hb.getChildren().addAll(productLbl, productTxtFld);
        hb.setAlignment(Pos.CENTER);
		
		//grid.getChildren().addAll(productLbl, productTextFld);
	    border.setCenter(hb);
	    
	    Button create = new Button ("Search");
	    create.setOnAction((ActionEvent event) ->{
	    	
			String productId= productTxtFld.getText();
			
			int result = controller.searchProduct(productId);
		        
		      if ( result == 0) {    	  
		    	  AlertBox.display("Product", "Product Not Found!");
		      }
		      
	    });
	    
	    Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			productTxtFld.setText("");
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
	
	public static void display(String make, String model, String storage, String productId) {
        
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Products");
        window.setMinWidth(250);
        window.setMinHeight(250);

        Label makeLbl = new Label();
        makeLbl.setText(make);
        
        Label modelLbl = new Label();
        modelLbl.setText(model);

        Label storageLbl = new Label();
        storageLbl.setText(storage);
        
        Label productLbl = new Label();
        productLbl.setText(productId);
        
        HBox hb = new HBox(5);
        hb.getChildren().addAll(makeLbl, modelLbl, storageLbl, productLbl);
        hb.setAlignment(Pos.CENTER);
        
        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> window.close());

        VBox vb = new VBox(10);
        vb.getChildren().addAll(hb, closeButton);
        vb.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(vb);
        window.setScene(scene);
        window.showAndWait();
    }
}