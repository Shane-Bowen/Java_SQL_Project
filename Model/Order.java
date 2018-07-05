package Model;

public class Order {
	
	//---------------------------------------
	//	Attributes
	//---------------------------------------
	
	private String orderId;
	private String customerId;
	private String productId;
	private int quantity;
	
	//---------------------------------------
	//	Constructor
	//---------------------------------------

	public Order (String o, String c, String p, int q) 
	{
		this.orderId = o;
		this.customerId = c;
		this.productId = p;
		this.quantity = q;
	}
	
	//---------------------------------------
	//	Get Methods
	//---------------------------------------
	
	public String getOrderId()
	{
		return orderId;
	}
	
	public String getCustomerId()
	{
		return customerId;
	}
	
	public String getProductId()
	{
		return productId;
	}
	
	public int getQuantity()
	{
		return quantity;
		
	}
	
	//---------------------------------------
	//	Set Method
	//---------------------------------------	
	
	public void setOrderId(String o)
	{
		this.orderId = o;
	}
	
	public void setCustomerId(String c)
	{
		this.customerId = c;
	}
	
	public void setProductId(String p)
	{
		this.productId = p;
	}
	
	public void setQuantity(int q)
	{
		this.quantity = q;
	}
	
}
