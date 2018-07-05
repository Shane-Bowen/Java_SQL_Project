package View;

import Controller.Controller;
import Model.Order;
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

public class OrderProducts extends Tab {
	private Controller controller;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(Order order) 
	{
		int result = controller.orderProduct(order);

		if ( result == 1 ){  
			AlertBox.display("Order", "Order Added!");
		}

		else {    	  
			AlertBox.display("Order", "Order Not Added!");
		}
	}

	public OrderProducts() {

		controller = new Controller();
		setText("Order");

		BorderPane border = new BorderPane();

		Label title = new Label("Order Products");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label orderLbl = new Label("Enter the Order ID:");
		GridPane.setConstraints(orderLbl, 1, 0); 

		TextField orderTxtFld = new TextField();
		GridPane.setConstraints(orderTxtFld, 2, 0);

		Label customerLbl = new Label("Enter the Customer ID:");
		GridPane.setConstraints(customerLbl, 1, 1);

		TextField customerTxtFld = new TextField();
		GridPane.setConstraints(customerTxtFld, 2, 1);

		Label productLbl = new Label("Enter the Product ID:");
		GridPane.setConstraints(productLbl, 1, 2);

		TextField productTxtFld = new TextField();
		GridPane.setConstraints(productTxtFld, 2, 2);

		Label qtyLbl = new Label("Enter the Quantity:");
		GridPane.setConstraints(qtyLbl, 1, 3);

		TextField qtyTxtFld = new TextField();
		GridPane.setConstraints(qtyTxtFld, 2, 3);

		grid.getChildren().addAll(orderLbl, orderTxtFld, customerLbl, customerTxtFld, productLbl, productTxtFld, qtyLbl, qtyTxtFld);
		border.setCenter(grid);

		Button create = new Button ("Order");
		create.setOnAction((ActionEvent event) ->{
			
			if(orderTxtFld.getText().equals("") || customerTxtFld.getText().equals("") || productTxtFld.getText().equals("")
					|| qtyTxtFld.getText().equals(""))
			{
				AlertBox.display("Error", "Empty Field");
			}
			
			else{
			String orderId= orderTxtFld.getText();
			String customerId = customerTxtFld.getText();
			String productId = productTxtFld.getText();
			Integer quantity = Integer.valueOf(qtyTxtFld.getText());
			
			Order order = new Order(orderId, customerId, productId, quantity);
			createButtonActionPerformed(order);
			}
		});

		Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			orderTxtFld.setText("");
			customerTxtFld.setText("");
			productTxtFld.setText("");
			qtyTxtFld.setText("");
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