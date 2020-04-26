import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook {
	private List<AddressTemplate> addressBookList = new ArrayList<AddressTemplate>();

	public AddressBook() {

	}

	public boolean addRecord(AddressTemplate record) {
		record.setId(addressBookList.size() + 1);
		return addressBookList.add(record);
	}

	public boolean removeRecord(int id) {

		boolean returnValue = false;
		for (AddressTemplate entry : addressBookList) {
			if (entry.getId() == id) {
				returnValue = addressBookList.remove(entry);
			}
		}
		return returnValue;
	}

	public boolean removeRecord(AddressTemplate record) {
		return addressBookList.remove(record);
	}

	public void display() {
		for (AddressTemplate record : addressBookList) {

			System.out.println(
					"***********************************************************************************************");
			System.out.println(record);
		}
	}

	public void sort() {
		Collections.sort(addressBookList);
	}

	public void saveRecordsToFile() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("AddressBook.ser"));
			objectOutputStream.writeObject(addressBookList);
			objectOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void readRecordsFromFile() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("AddressBook.ser"));
			this.addressBookList = (List<AddressTemplate>) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List search(String searchText, Integer searchChoice) {
		List<AddressTemplate> searchResultList = new ArrayList<AddressTemplate>();
		if (searchChoice == 1) {
			for (AddressTemplate record : addressBookList) {
				if (record.getFirstName().equals(searchText)) {
					searchResultList.add(record);
				}
			}
			return searchResultList;
		} else if (searchChoice == 2) {
			for (AddressTemplate record : addressBookList) {
				if (record.getLastName().equals(searchText)) {
					searchResultList.add(record);
				}
			}
			return searchResultList;
		} else if (searchChoice == 3) {
			for (AddressTemplate record : addressBookList) {
				if (record.getPhoneNumber().equals(searchText)) {
					searchResultList.add(record);
				}
			}
			return searchResultList;
		} else if (searchChoice == 4) {
			for (AddressTemplate record : addressBookList) {
				if (record.getAdress().equals(searchText)) {
					searchResultList.add(record);
				}
			}
			return searchResultList;
		} else if (searchChoice == 5) {
			for (AddressTemplate record : addressBookList) {
				if (record.getEmailAddress().equals(searchText)) {
					searchResultList.add(record);
				}
			}
			return searchResultList;
		} else if (searchChoice == 6) {

			AddressTemplate addressBookEntry = searchById(Integer.parseInt(searchText));

			if (addressBookEntry != null) {
				searchResultList.add(addressBookEntry);
			}
		}

		return searchResultList;
	}

	public void updateRecord(AddressTemplate record, String updateText, int choice) {
		if (choice == 1) {
			record.setFirstName(updateText);
		} else if (choice == 2) {
			record.setLastName(updateText);
		} else if (choice == 3) {
			record.setPhoneNumber(updateText);
		} else if (choice == 4) {
			record.setAdress(updateText);
		} else if (choice == 5) {
			record.setEmailAddress(updateText);
		}
		addressBookList.set(record.getId() - 1, record);
	}

	public AddressTemplate searchById(int id) {
		AddressTemplate addressBookEntry = null;
		for (AddressTemplate entry : addressBookList) {
			if (entry.getId() == id) {
				addressBookEntry = entry;
			}
		}
		return addressBookEntry;
	}
}
