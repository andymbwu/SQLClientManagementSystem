package Model;

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

/**
 * Class that contains user info for a single user
 */
public class UserModel implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String userType;
	private static final long serialVersionUID = 1L;

	/**
	 * default constructor
	 */
	public UserModel(){}

	/**
	 * Constructor that assigns user info to member variables, no ID
	 * @param firstName: User first name
	 * @param lastName: User last name
	 * @param address: User address
	 * @param postalCode: User postal code
	 * @param phoneNumber: User phone number
	 * @param userType: Type of users (residential/commercial)
	 */
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

	/**
	 * UserModel constructor that initializes user that has an ID
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param postalCode
	 * @param phoneNumber
	 * @param clientType
	 */
	public UserModel(int id, String firstName, String lastName, String address, String postalCode, String phoneNumber, String clientType){
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setPostalCode(postalCode);
		setPhoneNumber(phoneNumber);
		setUserType(clientType);
	}


	/**
	 * Setter for user id
	 * @param id: user id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * setter for user first name
	 * @param firstName: user first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Setter for user last name
	 * @param lastName: user last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Setter for user address
	 * @param address: user address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Setter for user postal code
	 * @param postalCode: user postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * setter for user phone number
	 * @param phoneNumber: user contact phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * setter for type of user (commercial/residential)
	 * @param clientType: type of user
	 */
	public void setUserType(String clientType) {
		this.userType = clientType;
	}

	/**
	 * getter for user id
	 * @return: user id
	 */
	public int getID(){
		return this.id;
	}

	/**
	 * getter for user first name
	 * @return: user first name
	 */
	public String getFirstName(){
		return this.firstName;
	}

	/**
	 * getter for user last name
	 * @return: user last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * getter for user address
	 * @return: user address
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 * getter for user postal code
	 * @return: user postal code
	 */
	public String getPostalCode(){
		return this.postalCode;
	}

	/**
	 * getter for user phone number
	 * @return: user phone number
	 */
	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	/**
	 * getter for user type
	 * @return: user type (commercial/residential)
	 */
	public String getUserType(){
		return this.userType;
	}

	/**
	 * User toString method
	 * @return
	 */
	public String toString()
	{
		String user = this.id + " " + this.firstName + " " + this.lastName + ", " + this.address +
				", " + this.postalCode + ", " + this.phoneNumber + ", " + this.userType + "\n";
		return user;
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
