import java.io.Serializable;
import java.util.Comparator;

public class AddressTemplate implements Comparable<AddressTemplate>, Serializable, Comparator<AddressTemplate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String adress;
	private String emailAddress;
	
	private static FieldEnums sortBy = FieldEnums.BY_ID;

	public AddressTemplate() {

	}

	public AddressTemplate(Integer id, String firstName, String lastName, String phoneNumber, String adress,
			String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public int compareTo(AddressTemplate o) {
		// TODO Auto-generated method stub
		System.out.println("Inside compareTo");
		//return (this.lastName).toString().compareTo(o.getLastName().toString());
		switch (sortBy) {
		case BY_FIRST_NAME:
			return (this.firstName).compareTo(o.getFirstName());
		case BY_LAST_NAME:
			return (this.lastName).compareTo(o.getLastName());
		case BY_PHONE_NUMBER:
			return (this.phoneNumber).compareTo(o.getPhoneNumber());
		case BY_ADDRESS:
			return (this.adress).compareTo(o.getAdress());
		case BY_EMAIL:
			return (this.emailAddress).compareTo(o.getEmailAddress());
		case BY_ID:
			return String.valueOf(this.id).compareTo(String.valueOf(o.getId()));
		default:
			return String.valueOf(this.id).compareTo(String.valueOf(o.getId()));
		}
	}

	@Override
	public String toString() {
		return new StringBuffer("ID: ").append(this.id).append("\nFirst name: ").append(this.firstName)
				.append("\nLast name: ").append(this.lastName).append("\nPhone number: ").append(this.phoneNumber)
				.append("\nAdress: ").append(this.adress).append("\nEmail ID: ").append(this.emailAddress).toString();
	}

	@Override
	public int compare(AddressTemplate arg0, AddressTemplate arg1) {
		// TODO Auto-generated method stub
		System.out.println("Inside compare");
		return 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		AddressTemplate addressBookEntry = (AddressTemplate) obj;

		if ((this.lastName.equals(addressBookEntry.lastName)) && (this.firstName.equals(addressBookEntry.firstName))
				&& (this.phoneNumber.equals(addressBookEntry.phoneNumber))) {
			return true;
		} else {
			return false;
		}
	}

	public static FieldEnums getSortBy() {
		return sortBy;
	}

	public static void setSortBy(FieldEnums sortBy) {
		AddressTemplate.sortBy = sortBy;
	}

}
