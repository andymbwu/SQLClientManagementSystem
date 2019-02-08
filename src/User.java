public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String userType; //char or String???

	
	public User(String firstName, String lastName, String address, String postalCode, 
			String phoneNumber, String userType)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
	}

	public User(int id, String firstName, String lastName, String address, String postalCode, 
			String phoneNumber, String userType) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
	}

	public int getID() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getAddress() {
		return this.address;
	}
	
	public String getPostalCode() {
		return this.postalCode;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public String toString()
	{
		String user = this.id + " " + this.firstName + " " + this.lastName + ", " + this.address + 
				", " + this.postalCode + ", " + this.phoneNumber + ", " + this.userType + "\n";
		return user;
	}
	
}