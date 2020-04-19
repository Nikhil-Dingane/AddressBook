import java.io.Serializable;
import java.util.Comparator;

public class AddressTemplate implements Comparable<AddressTemplate>, Serializable,Comparator<AddressTemplate>
{
	
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
	
	
	public AddressTemplate()
	{
		
	}
	
	public AddressTemplate(Integer id,String firstName, String lastName, String phoneNumber, String adress, String emailAddress) {
		this.id=id;
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
		return (this.lastName).toString().compareTo(o.getLastName().toString());
	}
	
	@Override
	public String toString()
	{
		return new StringBuffer("ID:").append(this.id)
				.append("First name:").append(this.firstName)
				.append("Last name:").append(this.lastName)
				.append("Phone number:").append(this.phoneNumber)
				.append("Adress:").append(this.adress)
				.append("Email ID:").append(this.emailAddress).toString();
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
	    AddressTemplate record=(AddressTemplate)obj;
	    
	    if((this.lastName.equals(record.lastName))&&(this.firstName.equals(record.firstName))&&(this.phoneNumber.equals(record.phoneNumber)))
	    {
	    	return true;
	    }
	    else {
			return false;
		}
	}
	
	
}
