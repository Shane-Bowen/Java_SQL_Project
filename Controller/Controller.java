package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.CustomerClass;
import Model.Order;
import Model.PhoneClass;
import Model.Product;
import Model.TvClass;
import View.DisplayOrders;
import View.ProductsSearch;

public class Controller {

	private PreparedStatement statement = null; 
	private Connection connection = DB_Connection.My_Connection();

	// add an Phone entry
	public int createPhone(PhoneClass phone, Product product)
	{
		int result = 0;

		try {
			statement = connection.prepareStatement( 
					"INSERT INTO PRODUCT " + 
							"( productId, description, price ) " + 
					"VALUES ( ?, ?, ? )" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// set parameters, then execute insert new Product
		try 
		{
			statement.setString( 1, product.getProductID() );
			statement.setString( 2, product.getDescription() );
			statement.setDouble( 3, product.getPrice() );

			// insert the new entry; returns # of rows updated
			result = statement.executeUpdate(); 
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			close();
		} // end catch

		try {
			statement = connection.prepareStatement( 
					"INSERT INTO PHONE " + 
							"( make, model, storageSpace, productId ) " + 
					"VALUES ( ?, ?, ?, ? )" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// set parameters, then execute insert new Phone
		try 
		{
			statement.setString( 1, phone.getMake());
			statement.setString( 2, phone.getModel() );
			statement.setString( 3, phone.getStorageSpace() );
			statement.setString( 4, product.getProductID() );

			// insert the new entry; returns # of rows updated
			result = statement.executeUpdate(); 
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method createPhone

	// add an TV entry
	public int createTV(TvClass tv, Product product)
	{
		int result = 0;

		try {
			statement = connection.prepareStatement( 
					"INSERT INTO PRODUCT " + 
							"( productId, description, price ) " + 
					"VALUES ( ?, ?, ? )" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// set parameters, then execute insert new Product
		try 
		{
			statement.setString( 1, product.getProductID());
			statement.setString( 2, product.getDescription());
			statement.setDouble( 3, product.getPrice());

			// insert the new entry; returns # of rows updated
			result = statement.executeUpdate(); 
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			close();
		} // end catch

		try {
			statement = connection.prepareStatement( 
					"INSERT INTO TV " + 
							"( make, screenSize, type, capable3D, productId ) " + 
					"VALUES ( ?, ?, ?, ?, ? )" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// set parameters, then execute insert new TV
		try 
		{
			statement.setString( 1, tv.getMake());
			statement.setString( 2, tv.getScreenSize());
			statement.setString( 3, tv.getType());
			statement.setString( 4, tv.getCapable3D());
			statement.setString( 5, product.getProductID() );

			// insert the new entry; returns # of rows updated
			result = statement.executeUpdate(); 
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method createTV

	public int orderProduct(Order order)
	{
		try {
			statement = connection.prepareStatement( 
					"INSERT INTO ORDERS " + 
							"( orderId, quantity, productId, customerId ) " + 
					"VALUES ( ?, ?, ?, ? )" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int result = 0;

		// set parameters, then execute insert new staff
		try 
		{
			statement.setString( 1, order.getOrderId());
			statement.setInt( 2, order.getQuantity());
			statement.setString( 3, order.getProductId());
			statement.setString( 4, order.getCustomerId());

			// insert the new entry; returns # of rows updated
			result = statement.executeUpdate(); 
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method orderProduct

	public int createCustomer( CustomerClass customer )
	{
		try {
			statement = connection.prepareStatement( 
					"INSERT INTO CUSTOMER " + 
							"( customerId, name, address ) " + 
					"VALUES ( ?, ?, ? )" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int result = 0;

		// set parameters, then execute insert new Customer
		try 
		{
			statement.setString( 1, customer.getcustomerId() );
			statement.setString( 2, customer.getName() );
			statement.setString( 3, customer.getAddress() );

			// insert the new entry; returns # of rows updated
			result = statement.executeUpdate(); 
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method createCustomer

	public int searchProduct( String productId )
	{
		int result = 0;

		try {
			String query =
					"(SELECT make, model, storageSpace, productId FROM phone Where productId = '"+productId+"') UNION" +
					"(SELECT make, screenSize, type, productId FROM tv Where productId = '"+productId+"');";

			//Statement st = connection.createStatement();
			Statement statement = connection.createStatement();

			result = statement.executeUpdate(query);
			ResultSet rs = statement.executeQuery(query);

			while (rs.next())
			{
				String make = rs.getString("make");
				String model = rs.getString("model");
				String storageSpace = rs.getString("storageSpace");
				String productID = rs.getString("productId");

				// print the results
		    	ProductsSearch.display(make, model, storageSpace, productID);

			}
			statement.close();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	} // end method orderProduct
	
	public int searchOrders( String customerId )
	{
		int result = 0;

		try {
			String query =
					"(Select p.make, p.model, p.storageSpace, o.quantity From orders o, phone p, product pd WHERE o.customerId = '"+customerId+"'"
					+ "AND o.productId = pd.productId AND pd.productId = p.productId ) UNION "
					+ "(Select tv.make, tv.screenSize, tv.type, o.quantity From orders o, tv, product pd Where o.customerId = '"+customerId+"' "
					+ "AND o.productId = pd.productId AND pd.productId = tv.productId )";

			//Statement st = connection.createStatement();
			Statement statement = connection.createStatement();

			result = statement.executeUpdate(query);
			
			if(result != 0)
			{
			ResultSet rs = statement.executeQuery(query);
			
			ArrayList<String> Orders = new ArrayList<String>();

			while (rs.next())
			{
				String make = rs.getString("make");
				String model = rs.getString("model");
				String storageSpace = rs.getString("storageSpace");
				String quantity = rs.getString("quantity");

				// print the results
				Orders.add(make + ", " + model + ", " + storageSpace + ", " + "Quantity: " + quantity);		    	
			}		
			
	    	DisplayOrders.display(Orders);
			}

			statement.close();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	} // end method orderProduct
	
	public ArrayList<String> displayProducts()
	{
		ArrayList<String> products = new ArrayList<String>();

		try {
			String query =
					"(SELECT make, model, storageSpace, productId FROM phone) UNION" +
							"(SELECT make, screenSize, type, productId FROM tv)" +
							 "order by productId ASC";

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next())
			{
				String make = rs.getString("make");
				String model = rs.getString("model");
				String storageSpace = rs.getString("storageSpace");
				String productId = rs.getString("productId");
				
				products.add(make + ", " + model + ", " + storageSpace + ", " + productId);		    	
			}		
			
			statement.close();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	} // end method orderProduct

	public void close()
	{
		try 
		{
			connection.close();
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
		} // end catch
	} // end method close 
	
}