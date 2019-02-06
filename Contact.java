public class Contact implements java.io.Serializable {
	private String name;
	private String email;
	private String homeNumber;
	private String workNumber;
	private String mobileNumber;
	private String address;
	private String description;

	public Contact(String name) 
	{
		this.name = name;
	}

	/*
	 * A constructor that sets all fields. 
	 */
	public Contact(String name,
					String email,
					String homeNumber,
					String workNumber,
					String mobileNumber,
					String address,
					String description) {
				setName(name);
				setEmail(email);
				setHomeNumber(homeNumber);
				setWorkNumber(workNumber);
				setMobileNumber(mobileNumber);
				setAddress(address);
				setDescription(description);
						
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getHomeNumber() 
	{
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) 
	{
		this.homeNumber = homeNumber;
	}

	public String getWorkNumber() 
	{
		return workNumber;
	}

	public void setWorkNumber(String workNumber) 
	{
		this.workNumber = workNumber;
	}

	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) 
	{
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}