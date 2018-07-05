package View;

import Controller.Controller;
import Model.CustomerClass;
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

public class Customer extends Tab {
	private Controller controller;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(CustomerClass customer) 
	{
		int result = controller.createCustomer(customer);

		if ( result == 1 ){  
			AlertBox.display("Customer", "Customer Added!");
		}

		else {    	  
			AlertBox.display("Customer", "Customer Not Added!");
		}
	}

	public Customer() {

		controller = new Controller();
		setText("Customer");

		BorderPane border = new BorderPane();

		Label title = new Label("Customer Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label idLbl = new Label("Enter the Customer ID:");
		GridPane.setConstraints(idLbl, 1, 0); 

		TextField idTxtFld = new TextField();
		GridPane.setConstraints(idTxtFld, 2, 0);

		Label nameLbl = new Label("Enter the Customer Name:");
		GridPane.setConstraints(nameLbl, 1, 1);

		TextField nameTxtFld = new TextField();
		GridPane.setConstraints(nameTxtFld, 2, 1);

		Label addressLbl = new Label("Enter the Customer Address:");
		GridPane.setConstraints(addressLbl, 1, 2);

		TextField addressTxtFld = new TextField();
		GridPane.setConstraints(addressTxtFld, 2, 2);

		grid.getChildren().addAll(idLbl, idTxtFld, nameLbl, nameTxtFld, addressLbl, addressTxtFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{
			
			if(idTxtFld.getText().equals("") || nameTxtFld.getText().equals("") || addressTxtFld.getText().equals(""))
			{
				AlertBox.display("Error", "Empty Field");
			}

			else{
			String id = idTxtFld.getText();
			String name = nameTxtFld.getText();
			String address = addressTxtFld.getText();
			
			CustomerClass customer = new CustomerClass(id, name, address);
			createButtonActionPerformed(customer);
			}
		});

		Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			idTxtFld.setText("");
			nameTxtFld.setText("");
			addressTxtFld.setText("");
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