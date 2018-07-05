package View;

import Controller.Controller;
import Model.PhoneClass;
import Model.Product;
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

public class Phone extends Tab {
	private Controller controller;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(PhoneClass phone, Product product) 
	{
		int result = controller.createPhone(phone, product);

		if ( result == 1 ){  
			AlertBox.display("Phone", "Phone Added!");
		}

		else {    	  
			AlertBox.display("Phone", "Phone Not Added!");
		}
	}

	public Phone() {

		controller = new Controller();
		setText("Phone");

		BorderPane border = new BorderPane();

		Label title = new Label("Phone Details");
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

		Label modelLbl = new Label("Enter the Model:");
		GridPane.setConstraints(modelLbl, 1, 2);

		TextField modelTxtFld = new TextField();
		GridPane.setConstraints(modelTxtFld, 2, 2);

		Label sizeLbl = new Label("Enter the GB:");
		GridPane.setConstraints(sizeLbl, 1, 3);

		TextField sizeTxtFld = new TextField();
		GridPane.setConstraints(sizeTxtFld, 2, 3);

		Label commentLbl = new Label("Make a Comment:");
		GridPane.setConstraints(commentLbl, 1, 4);

		TextField commentTxtFld = new TextField();
		GridPane.setConstraints(commentTxtFld, 2, 4);

		Label priceLbl = new Label("Enter the Price:");
		GridPane.setConstraints(priceLbl, 1, 5);

		TextField priceTxtFld = new TextField();
		GridPane.setConstraints(priceTxtFld, 2, 5);

		grid.getChildren().addAll(makeLbl, makeTxtFld, modelLbl, modelTxtFld, sizeLbl, sizeTxtFld, commentLbl, commentTxtFld, 
				priceLbl, priceTxtFld, idLbl, idTxtFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{

			if(makeTxtFld.getText().equals("") || modelTxtFld.getText().equals("") || sizeTxtFld.getText().equals("") || 
					commentTxtFld.getText().equals("") || priceTxtFld.getText().equals("") || idTxtFld.getText().equals(""))
			{
				AlertBox.display("Error", "Empty Field!");
			}

			else{
				String make = makeTxtFld.getText();
				String model = modelTxtFld.getText();
				String size = sizeTxtFld.getText();
				String comment = commentTxtFld.getText();
				Double price = Double.valueOf(priceTxtFld.getText());
				String id = idTxtFld.getText();

				PhoneClass phone = new PhoneClass(make, model, size);
				Product product = new Product(make, comment, price, id);
				createButtonActionPerformed(phone, product);
			}

		});

		Button reset = new Button ("Reset");
		reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have Reset!");
			makeTxtFld.setText("");
			modelTxtFld.setText("");
			sizeTxtFld.setText("");
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