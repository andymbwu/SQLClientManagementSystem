import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserModel implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String userType;

	public UserModel(){}

	public UserModel(String firstName, String lastName, String address, String postalCode, 
			String phoneNumber, String userType)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
	}
	
	public UserModel(int id, String firstName, String lastName, String address, String postalCode, String phoneNumber, String clientType){
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setPostalCode(postalCode);
		setPhoneNumber(phoneNumber);
		setUserType(clientType);
	}

	private static final long serialVersionUID = 1L;

	public void setId(int id){
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUserType(String clientType) {
		this.userType = clientType;
	}

	public int getID(){
		return this.id;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getAddress(){
		return this.address;
	}
	public String getPostalCode(){
		return this.postalCode;
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	public String getUserType(){
		return this.userType;
	}
	public String toString(){
		return this.firstName + " " + this.lastName + " " + this.id;
	}
	
	/**
	 * This is a validation method that checks that the text entered into the 'first
	 * name' text field is less than 20 characters as per the project requirements.
	 * 
	 * @param firstName - String retrieved from the 'first name' text field in the
	 *                  GUI.
	 * @return - boolean return type. True if the firstName parameter is valid,
	 *         false otherwise.
	 */
	public boolean checkFirstName(String firstName) {
		if (firstName.length() > 20 || firstName.isEmpty())
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'last
	 * name' text field is less than 20 characters as per the project requirements.
	 * 
	 * @param lastName - String retrieved from the 'last name' text field in the
	 *                 GUI.
	 * @return - boolean return type. True if the lastName parameter is valid, false
	 *         otherwise.
	 */
	public boolean checkLastName(String lastName) {
		if (lastName.length() > 20 || lastName.isEmpty())
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'last
	 * name' text field is less than 50 characters as per the project requirements.
	 * 
	 * @param address - String retrieved from the 'address' text field in the GUI.
	 * @return - boolean return type. True if the address parameter is valid, false
	 *         otherwise.
	 */
	public boolean checkAddress(String address) {
		if (address.length() > 50 || address.isEmpty())
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the
	 * 'postal code' text field is in the format of A#A #A#, where A represents an
	 * upper case letter and # represents a number as per the project requirements.
	 * 
	 * @param postalCode - String retrieved from the 'postal code' text field in the
	 *                   GUI.
	 * @return - boolean return type. True if the postalCode parameter is valid,
	 *         false otherwise.
	 */
	public boolean checkPostalCode(String postalCode) {
		if (!postalCode.matches("[A-Za-z][0-9][A-Za-z]\\s[0-9][A-Za-z][0-9]"))
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'phone
	 * number' text field is in the format of ###-###-####, where # represents a
	 * number as per the project requirements.
	 * 
	 * @param phoneNumber - String retrieved from the 'phone number' text field in
	 *                    the GUI.
	 * @return - boolean return type. True if the phoneNumber parameter is valid,
	 *         false otherwise.
	 */
	public boolean checkPhoneNumber(String phoneNumber) {
		if (!phoneNumber.matches("[0-9][0-9][0-9][-][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9]"))
			return false;

		return true;
	}

}
