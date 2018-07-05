package Model;

public class PhoneClass {
	
	//---------------------------------------
	//	Attributes
	//---------------------------------------

	private String make;
	private String model;
	private String storageSpace;
	
	//---------------------------------------
	//	Constructor
	//---------------------------------------
	
	public PhoneClass(String ma, String mo, String s) 
	{	
		this.make = ma;
		this.model = mo;
		this.storageSpace = s;
	}
	
	//---------------------------------------
	//	Get Methods
	//---------------------------------------	
	
	public String getMake()
	{
		return make;
	}
	
	public String getModel()
	{
		return model;
	}
	
	public String getStorageSpace()
	{
		return storageSpace;
	}
	
	//---------------------------------------
	//	Set Method
	//---------------------------------------	
	
	public void setMake(String ma)
	{
		this.make = ma;
	}
	
	public void setModel(String mo)
	{
		this.model = mo;
	}
	
	public void setStorageSpace(String s)
	{
		this.storageSpace = s;
	}
	
}