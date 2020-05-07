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
	
	private static MyEnums sortBy = MyEnums.BY_ID;

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
		AddressTemplate.sortBy = MyEnums.BY_ID;
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
			return (this.firstName).toString().compareTo(o.getFirstName().toString());
		case BY_LAST_NAME:
			return (this.lastName).toString().compareTo(o.getLastName().toString());
		case BY_PHONE_NUMBER:
			return (this.phoneNumber).toString().compareTo(o.getPhoneNumber().toString());
		case BY_ADDRESS:
			return (this.adress).toString().compareTo(o.getAdress().toString());
		case BY_EMAIL:
			return (this.emailAddress).toString().compareTo(o.getEmailAddress().toString());
		case BY_ID:
			return new Integer(this.id).toString().compareTo(new Integer(o.getId()).toString());
		default:
			return (this.lastName).toString().compareTo(o.getLastName().toString());
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

	public static MyEnums getSortBy() {
		return sortBy;
	}

	public static void setSortBy(MyEnums sortBy) {
		AddressTemplate.sortBy = sortBy;
	}

}
