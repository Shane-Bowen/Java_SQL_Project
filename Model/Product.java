package Model;

public class Product {
	
	//---------------------------------------
	//	Attributes
	//---------------------------------------
	
	private String name;
	private String description;
	private double price;
	private String productID;
	
	//---------------------------------------
	//	Constructor
	//---------------------------------------
	
	public Product(String n, String d, double p, String id)
	{
		this.name = n;
		this.description = d;
		this.price = p;
		this.productID = id;
	}
	
	//---------------------------------------
	//	Get Methods
	//---------------------------------------	

	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getProductID()
	{
		return productID;
	}
	
	//---------------------------------------
	//	Set Method
	//---------------------------------------	
	
	public void setName(String n)
	{
		this.name = n;
	}
	
	public void setDescription(String d)
	{
		this.description = d;
	}
	
	public void setPrice(double p)
	{
		this.price = p;
	}
	
	public void setProductID(String id)
	{
		this.productID = id;
	}
	
}