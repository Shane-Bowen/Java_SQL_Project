package View;

import java.util.ArrayList;

import Controller.Controller;
import Controller.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DisplayProducts extends Tab {
	private Controller controller;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	public DisplayProducts() {

		controller = new Controller();
		setText("Display Products");

		ArrayList<String> products = new ArrayList<String>();
		products = controller.displayProducts();
		BorderPane border = new BorderPane();

		Label title = new Label("Display All Products");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		VBox vb = new VBox(5);
		vb.setPadding(new Insets(0, 10, 0, 10));
		
		Label ordersLbl = null;

		for(int i = 0; i < products.size(); i++)
		{
			ordersLbl = new Label();
			ordersLbl.setText(products.get(i));
			vb.getChildren().add(ordersLbl);
		}

		vb.setAlignment(Pos.TOP_LEFT);
		border.setLeft(vb);

		Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			Main.setDisable();
			Main.setEnable();
		});

		Button quit = new Button ("Quit");
		quit.setOnAction(e -> closeProgram());

		//**********************************************************
		// Creating the new layout HBox and adding the Reset and
		// Quit button to it
		//**********************************************************

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(reset, quit);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		setContent(border);

	}
}