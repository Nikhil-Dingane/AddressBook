
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddressBook {
	private List<AddressTemplate> addressBookList = new ArrayList<AddressTemplate>();
	private FileWriter fileWriter = null;
	private FileReader fileReader = null;
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

			System.out.println("***********************************************************************************************");
			System.out.println(record);
		}
	}

	public void sort(FieldEnums sortBy) {
		AddressTemplate.setSortBy(sortBy);
		Collections.sort(addressBookList);
	}

	public void saveRecordsToFile(String fileName) {
		try {
			fileWriter.writeFile(this.addressBookList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void readRecordsFromFile(String fileName) {
		try {
			addressBookList = fileReader.readFile();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public List<AddressTemplate> search(String searchText, FieldEnums searchChoice, CriteriaEnums criteria) {
		List<AddressTemplate> searchResultList = new ArrayList<AddressTemplate>();
		if (searchChoice == FieldEnums.BY_FIRST_NAME) {
			for (AddressTemplate record : addressBookList) {
				if (compareByCriteria(record.getFirstName(),searchText,criteria)) {
					searchResultList.add(record);
				}
			}
		} else if (searchChoice == FieldEnums.BY_LAST_NAME) {
			for (AddressTemplate record : addressBookList) {
				if (compareByCriteria(record.getLastName(),searchText,criteria)) {
					searchResultList.add(record);
				}
			}
		} else if (searchChoice == FieldEnums.BY_PHONE_NUMBER) {
			for (AddressTemplate record : addressBookList) {
				if (compareByCriteria(record.getPhoneNumber(),searchText,criteria)) {
					searchResultList.add(record);
				}
			}
		} else if (searchChoice == FieldEnums.BY_ADDRESS) {
			for (AddressTemplate record : addressBookList) {
				if (compareByCriteria(record.getAdress(),searchText,criteria)) {
					searchResultList.add(record);
				}
			}
		} else if (searchChoice == FieldEnums.BY_EMAIL) {
			for (AddressTemplate record : addressBookList) {
				if (compareByCriteria(record.getEmailAddress(),searchText,criteria)) {
					searchResultList.add(record);
				}
			}
		} else if (searchChoice == FieldEnums.BY_ID) {

			for (AddressTemplate record : addressBookList) {
				if (compareByCriteria(String.valueOf(record.getId()),searchText,criteria)) {
					searchResultList.add(record);
				}
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

	public List<AddressTemplate> getAddressBookList() {
		return addressBookList;
	}

	public void setAddressBookList(List<AddressTemplate> addressBookList) {
		this.addressBookList = addressBookList;
	}


	public FileWriter getFileWriter() {
		return fileWriter;
	}


	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}


	public FileReader getFileReader() {
		return fileReader;
	}


	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}
	protected void finalize() throws Throwable {
		fileReader.close();
		fileWriter.close();
	}
	
	public void moveEntries(List<AddressTemplate> listOfEntriesTobeMoved, String fileName, boolean addressBookExists) {
		if(addressBookExists) {
			FileReader newFileReader = new FileReader(fileName);
			List<AddressTemplate> newFileEntriesList = newFileReader.readFile();
			newFileEntriesList.addAll(listOfEntriesTobeMoved);
			(new FileWriter(fileName)).writeFile(newFileEntriesList);
		} else {
			(new FileWriter(fileName)).writeFile(listOfEntriesTobeMoved);
		}
		
		
		for(AddressTemplate entry: listOfEntriesTobeMoved) {
			this.removeRecord(entry);
		}
		
	}
	
	private boolean compareByCriteria(String str1, String str2, CriteriaEnums criteria) {
		switch (criteria) {
		case EXACT_MATCH:
			return str1.toLowerCase().equals(str2.toLowerCase());
		case STARTS_WITH:
			return str1.toLowerCase().startsWith(str2.toLowerCase());
		case ENDS_WITH:
			return str1.toLowerCase().endsWith(str2.toLowerCase());
		case CONTAINS:
			return str1.toLowerCase().contains(str2.toLowerCase());
		default:
			return false;
		}
	}
	
}
