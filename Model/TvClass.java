package Model;

public class TvClass {
	
	//---------------------------------------
	//	Attributes
	//---------------------------------------
	
	private String make;
	private String screenSize;
	private String type;
	private String capable3D;
	
	//---------------------------------------
	//	Constructor
	//---------------------------------------

	public TvClass (String m, String s, String t, String c) 
	{
		this.make = m;
		this.screenSize = s;
		this.type = t;
		this.capable3D = c;
	}
	
	//---------------------------------------
	//	Get Methods
	//---------------------------------------
	
	public String getMake()
	{
		return make;
	}
	
	public String getScreenSize()
	{
		return screenSize;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getCapable3D()
	{
		return capable3D;
		
	}
	
	//---------------------------------------
	//	Set Method
	//---------------------------------------	
	
	public void setMake(String m)
	{
		this.make = m;
	}
	
	public void setScreenSize(String s)
	{
		this.screenSize = s;
	}
	
	public void setType(String t)
	{
		this.type = t;
	}
	
	public void setCapable3D(String c)
	{
		this.capable3D = c;
	}
	
}
