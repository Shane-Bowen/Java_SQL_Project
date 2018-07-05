/*
 * Name: Shane Bowen
 * ID: R00149085
 * Class: SD2-A 
 */

package Controller;

import View.Customer;
import View.DisplayOrders;
import View.DisplayProducts;
import View.OrderProducts;
import View.Phone;
import View.ProductsSearch;
import View.TV;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	static DisplayProducts products = new DisplayProducts();

	//*************************************************
	//	Declaring TabPane as a global variable
	//*************************************************	

	static TabPane tabPane = new TabPane();

	public static void main(String[] args) {
		launch(args);
	}

	//*****************************************************************
	//	Creating the main BorderPane and adding tabs to it.
	//****************************************************************

	public void start(Stage primaryStage) throws Exception {

		try {
			BorderPane mainPane = new BorderPane();
			Group root = new Group();
			Scene scene = new Scene(root,700,500);
			scene.setFill(Color.LIGHTGREY);

			Phone phone = new Phone();
			TV tv = new TV();			
			OrderProducts order = new OrderProducts();
			Customer customer = new Customer();
			ProductsSearch productSearch = new ProductsSearch();
			DisplayOrders display = new DisplayOrders();
			products = new DisplayProducts();
			
			tabPane.getTabs().addAll(phone, tv, order, customer, productSearch, display, products);
		
			mainPane.setCenter(tabPane);

			mainPane.prefHeightProperty().bind(scene.heightProperty());
			mainPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setEnable(){
		
		products = new DisplayProducts();			
		tabPane.getTabs().add(products);
	}
	
	public static void setDisable(){
		
		tabPane.getTabs().remove(products);
	}
}	