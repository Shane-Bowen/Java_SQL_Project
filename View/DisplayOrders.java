package View;

import java.util.ArrayList;

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

public class DisplayOrders extends Tab {
	private Controller controller;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	public DisplayOrders() {

		controller = new Controller();
		setText("Display Orders");

		BorderPane border = new BorderPane();

		Label title = new Label("Display Orders");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label customerLbl = new Label("Enter the Customer ID:");
		TextField customerTxtFld = new TextField();
		
	    HBox hb = new HBox(5);
	    hb.getChildren().addAll(customerLbl, customerTxtFld);
        hb.setAlignment(Pos.CENTER);

		//grid.getChildren().addAll(customerLbl, customerTextFld);
		border.setCenter(hb);

		Button create = new Button ("Search");
		create.setOnAction((ActionEvent event) ->{

			String customerId = customerTxtFld.getText();

			int result = controller.searchOrders(customerId);

			if ( result == 0) {    	  
				AlertBox.display("Order", "Orders Not Found!");
			}

		});

		Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			customerTxtFld.setText("");
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

	public static void display(ArrayList<String> orders) {
		Stage window = new Stage();

		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Products");
		window.setMinWidth(250);
		window.setMinHeight(250);

		VBox vb = new VBox(5);
		Label ordersLbl = null;

		for(int i = 0; i < orders.size(); i++)
		{
			ordersLbl = new Label();
			ordersLbl.setText(orders.get(i));
			vb.getChildren().add(ordersLbl);

		}

		vb.setAlignment(Pos.CENTER);

		Button closeButton = new Button("Exit");
		closeButton.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(vb, closeButton);
		layout.setAlignment(Pos.CENTER);

		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();		
	}
}