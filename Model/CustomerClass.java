package Model;

public class CustomerClass {

	//---------------------------------------
	//	Attributes
	//---------------------------------------

	private String customerId;
	private String name;
	private String address;

	//---------------------------------------
	//	Constructor
	//---------------------------------------

	public CustomerClass(String id, String n, String a)
	{
		this.customerId = id;
		this.name = n;
		this.address = a;
	}

	//---------------------------------------
	//	Get Methods
	//---------------------------------------

	public String getcustomerId()
	{
		return customerId;
	}
	
	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

	//---------------------------------------
	//	Set Method
	//---------------------------------------	

	public void setCustomerId(String id)
	{
		this.customerId = id;
	}
	
	public void setName(String n)
	{
		this.name = n;
	}

	public void setAddress(String a)
	{
		this.address = a;
	}
	
}