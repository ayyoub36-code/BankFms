package fr.fms.entities;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {

	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String address;
	private String phoneNumber;
	private LocalDateTime birthDate;
	private ArrayList<Account> listAccounts;
	private String role = "USER";

	public User(int id, String lastName, String firstName, String email, String address, String phoneNumber,
			LocalDateTime birthDate) throws Exception {

		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		setEmail(email);
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.listAccounts = new ArrayList<Account>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		// Regex pour valider l email
		String regExp = "^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z][a-z]+$";
		if (email.matches(regExp))
			this.email = email;
		else
			throw new Exception("Votre adresse mail est invalide ! ");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws Exception {
		// regex pour le numéro de tel
		String phoneRegex = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
		if (phoneNumber.matches(phoneRegex)) {
			this.phoneNumber = phoneNumber;
		} else
			throw new Exception("Votre numéro de téléphone est invalide ! ");

	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<Account> getListAccounts() {
		return listAccounts;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", birthDate=" + birthDate + ", role=" + role + "]";// ", address=" + address + ", phoneNumber=" +
																		// phoneNumber +
	}
}