import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook {
	private List<AddressTemplate> AddressBookList;

	public AddressBook() {
		this.AddressBookList=new ArrayList<AddressTemplate>();
	}
	
	public boolean addRecord(AddressTemplate record) {
		record.setId(AddressBookList.size()+1);
		return AddressBookList.add(record);	
	}
	public boolean removeRecord(int id)
	{
		AddressTemplate objAddressTemplate=AddressBookList.remove(id);
		return (objAddressTemplate!=null);
	}
	public boolean removeRecord(AddressTemplate record) {
		return AddressBookList.remove(record);
	}
	
	public void display() {
		for(AddressTemplate record: AddressBookList)
		{
			System.out.println("***********************************************************************************************");
			System.out.println("ID : "+record.getId());
			System.out.println("First name : "+record.getFirstName());
			System.out.println("last name : "+record.getLastName());
			System.out.println("Phone number : "+record.getPhoneNumber());
			System.out.println("Address : "+record.getAdress());
			System.out.println("Email ID : "+record.getEmailAddress());
		}
	}
	
	public void sort()
	{
		Collections.sort(AddressBookList);
	}
	
	public void saveRecordsToFile()
	{
		try 
		{
			ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream("AddressBook.ser"));
			objectOutputStream.writeObject(AddressBookList);
			objectOutputStream.close();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	public void readRecordsFromFile()
	{
		try 
		{
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("AddressBook.ser"));
			this.AddressBookList=(List<AddressTemplate>)objectInputStream.readObject();
			objectInputStream.close();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List search(String searchText, Integer searchChoice) 
	{
		List<AddressTemplate> searchResultList=new ArrayList<AddressTemplate>();
		if(searchChoice==1)
		{
			for(AddressTemplate record: AddressBookList)
			{
				if(record.getFirstName().equals(searchText))
				{
					searchResultList.add(record);
				}
			}
			return searchResultList;
		}
		else if(searchChoice==2) {
			for(AddressTemplate record: AddressBookList)
			{
				if(record.getLastName().equals(searchText))
				{
					searchResultList.add(record);
				}
			}
			return searchResultList;
		}
		else if(searchChoice==3)
		{
			for(AddressTemplate record: AddressBookList)
			{
				if(record.getPhoneNumber().equals(searchText))
				{
					searchResultList.add(record);
				}
			}
			return searchResultList;
		}
		else if(searchChoice==4) 
		{
			for(AddressTemplate record: AddressBookList)
			{
				if(record.getAdress().equals(searchText))
				{
					searchResultList.add(record);
				}
			}
			return searchResultList;
		}
		else if(searchChoice==5)
		{
			for(AddressTemplate record: AddressBookList)
			{
				if(record.getEmailAddress().equals(searchText))
				{
					searchResultList.add(record);
				}
			}
			return searchResultList;
		}
		else if(searchChoice==6)
		{
			try {
				searchResultList.add(AddressBookList.get(Integer.parseInt(searchText)-1));
				return searchResultList;
			} catch (Exception e) {
				// TODO: handle exception
				return searchResultList;
			}
			
		}
		else
		{
			return searchResultList;
		}
	}
	
	public void updateRecord(AddressTemplate record, String updateText, int choice)
	{
		if(choice==1) {record.setFirstName(updateText);}
		else if(choice==2) {record.setLastName(updateText);}
		else if(choice==3) {record.setPhoneNumber(updateText);}
		else if(choice==4) {record.setAdress(updateText);}
		else if(choice==5) {record.setEmailAddress(updateText);}
		AddressBookList.set(record.getId()-1, record);
	}
}
