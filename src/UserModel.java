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
	private String clientType;

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

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public int getId(){
		return this.id;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastname() {
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
	public String getClientType(){
		return this.clientType;
	}
	public String toString(){
		return this.firstName + " " + this.lastName + " " + this.id;
	}
}
