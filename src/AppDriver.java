import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class AppDriver {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		AddressBook addressBook = new AddressBook();

		AddressTemplate addressBookEntry = new AddressTemplate();
		
		String fileName = "";

		while (true) {

			clearScreen();

			System.out.println("\n\n1) Load from file");
			System.out.println("2) Save to file");
			System.out.println("3) Add an entry");
			System.out.println("4) Remove an entry");
			System.out.println("5) Edit an existing entry");
			System.out.println("6) Sort the address book");
			System.out.println("7) Search for a specific entry");
			System.out.println("8) Display address book");
			System.out.println("9) Move entry/entries to other address book:");
			System.out.println("0) Quit");

			System.out.println("\nPlease choose what you'd like to do with the database:");

			int choice = 0;
			
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter number as a input.");
				br.readLine();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			switch (choice) {
			case 1:
				if(fileName == "" && addressBook.getFileReader() == null){

					System.out.println("\nEnter a file name of a address book to be load:");
					fileName = br.readLine();
					if(validateFile(fileName)) {
						addressBook.setFileReader(new FileReader(fileName));
						addressBook.readRecordsFromFile(fileName);	
						System.out.println("\nAddress book successfully loaded into memory.");

					} else {
						System.out.println("\nError: File does not exist");
						fileName = "";
					}
				} else {

					String option = "";
					while( true ) {
						System.out.println("\nDo you want to read the data from this file(y/n) : "+fileName);
						option = br.readLine();
						if(option.toUpperCase().equals("Y")) {
							if(addressBook.getFileReader()==null) {
								addressBook.setFileReader(new FileReader(fileName));
							}
							addressBook.readRecordsFromFile(fileName);
							System.out.println("\nAddress book successfully loaded into memory.");
							break;
						}
						else if(option.toUpperCase().equals("N")) {
							System.out.println("\nEnter a file name of a address book to be load:");
							fileName = br.readLine();
							if(validateFile(fileName)) {
								addressBook.setFileReader(new FileReader(fileName));
								addressBook.readRecordsFromFile(fileName);	
								System.out.println("\nAddress book successfully loaded into memory.");
							}else {
								System.out.println("\nError: File does not exist");
								fileName = "";
							}
							break;
						}
						else {
							System.out.print("\nPlease enter valid option");
						}
					}
				}					

				break;

			case 2:

				if(fileName == "" && addressBook.getFileWriter() == null){

					System.out.println("Enter a file name of a address book to be save:");
					fileName = br.readLine();
					addressBook.setFileWriter(new FileWriter(fileName));
					addressBook.saveRecordsToFile(fileName);	
					System.out.println("Address book successfully saved to file.");


				} else {

					String option = "";
					while( true ) {
						System.out.println("\nDo you want to save the data to this file(y/n) : "+fileName);
						option = br.readLine();
						if(option.toUpperCase().equals("Y")) {
							if(addressBook.getFileWriter() == null) {
								addressBook.setFileWriter(new FileWriter(fileName));
							}
							addressBook.saveRecordsToFile(fileName);
							System.out.println("Address book successfully saved to file.");
							break;
						}
						else if(option.toUpperCase().equals("N")) {
							System.out.println("\nEnter a file name of a address book to be load:");
							fileName = br.readLine();
							addressBook.setFileWriter(new FileWriter(fileName));
							addressBook.saveRecordsToFile(fileName);	
							System.out.println("Address book successfully saved to file.");
							break;
						}
						else {
							System.out.print("\nPlease enter valid option");
						}
					}
				}
				break;

			case 3:

				System.out.println("******************************* New Entry *******************************");

				addressBookEntry = getUserInfo();

				if (addressBook.addRecord(addressBookEntry)) {
					System.out.println("Record successfully inserted");
				} else {
					System.out.println("Unable to insert record");
				}

				break;

			case 4:
				clearScreen();
				System.out.println("******************************* Remove Entry *******************************");

				System.out.println("1) Remove by ID");
				System.out.print("2) Remove by information");
				System.out.print("\nEnter you choice to remove records:");
				int removeChoice = Integer.parseInt(br.readLine());
				if (removeChoice == 1) {
					System.out.println("Enter ID:");
					if (addressBook.removeRecord(Integer.parseInt(br.readLine()))) {
						System.out.println("Record successfully deleted...");
					} else {
						System.out.println("No such a reocrd to delete or Unable to remove record");
					}
				} else if (removeChoice == 2) {
					addressBookEntry = getUserInfo();

					System.out.println(addressBookEntry.getFirstName() + addressBookEntry.getLastName()
					+ addressBookEntry.getPhoneNumber());
					if (addressBook.removeRecord(addressBookEntry)) {
						System.out.println("Record successfully deleted...");
					} else {
						System.out.println("No such a reocrd to delete or Unable to remove record");
					}
				} else {

				}

				break;

			case 5:
				clearScreen();
				System.out.println("******************************* Update Entry *******************************");
				System.out.println("\nUpdate id of the entry to be updated:");
				int updateId = Integer.parseInt(br.readLine());
				addressBookEntry = null;
				addressBookEntry = addressBook.searchById(updateId);
				if (addressBookEntry != null) {

					System.out.println(
							"***********************************************************************************************");
					System.out.println(addressBookEntry);
					System.out.println(
							"***********************************************************************************************");
				} else {
					System.out.println("Record not found!!");
					break;
				}
				System.out.println("Select field to be updated");

				System.out.println("1) By first name:");
				System.out.println("2) By last name:");
				System.out.println("3) By phone number :");
				System.out.println("4) By address:");
				System.out.println("5) By email id:");
				System.out.println("Enter your choice:");
				int updateChoice = Integer.parseInt(br.readLine());

				System.out.println("Enter the text:");
				String updateTextString = br.readLine();

				if (updateChoice > 0 && updateChoice < 6) {
					addressBook.updateRecord(addressBookEntry, updateTextString, updateChoice);
					System.out.println("Record updated successfully");
				} else {
					System.out.println("Invalid choice!!");
				}
				break;

			case 6:
				clearScreen();
				System.out.println("******************************* Sort Entry *******************************");
				printOptions();
				int sortChoice = Integer.parseInt(br.readLine());
				switch (sortChoice) {
				case 1:
					addressBook.sort(FieldEnums.BY_FIRST_NAME);
					break;
				case 2:
					addressBook.sort(FieldEnums.BY_LAST_NAME);
					break;
				case 3:
					addressBook.sort(FieldEnums.BY_PHONE_NUMBER);
					break;
				case 4:
					addressBook.sort(FieldEnums.BY_ADDRESS);	
					break;
				case 5:
					addressBook.sort(FieldEnums.BY_EMAIL);		
					break;
				case 6:
					addressBook.sort(FieldEnums.BY_ID);	
					break;	
				default:
					break;
				}
				break;

			case 7:
				try {
					getSearchedEntries(addressBook, br);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;

			case 8:
				addressBook.display();
				break;

			case 9:
				List<AddressTemplate> searchResultEntries = null;
				try {
					searchResultEntries = getSearchedEntries(addressBook, br);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if(searchResultEntries.size() > 0) {
					while(true) {
						System.out.println("\nDo you want to move these entries to another address book (y/n):");
						String moveChoice= br. readLine();
						if(moveChoice.equalsIgnoreCase("y")){
							System.out.println("\nEnter the name of address book to which contatcts to be moved:");
							String newFileName = br.readLine();
							addressBook.moveEntries(searchResultEntries, newFileName,validateFile(newFileName));
							System.out.println("\nThe entry/entries has been successfully moved to " + newFileName);
							break;
						}
						else if(moveChoice.equalsIgnoreCase("n")) {
							break;
						} else {
							System.out.println("\nInvalid choice!!");
							continue;
						}
					}
				}

				break;
			case 0:
				System.out.println("\nExiting from the program...\n\nPress any key to get exit....");
				br.readLine();
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
			System.out.println("\nPress any key to continue.....");
			br.readLine();
		}
	}

	public static void clearScreen() {
		try{
			System.out.print("\033[H\033[2J");
			System.out.flush();  
		} catch (final Exception e){
			//  Handle any exceptions.
			e.printStackTrace();
		}
	}

	public static AddressTemplate getUserInfo() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AddressTemplate addressBookEntry = new AddressTemplate();
		System.out.println("Enter first name:");
		addressBookEntry.setFirstName(br.readLine());
		System.out.println("Enter last name:");
		addressBookEntry.setLastName(br.readLine());
		System.out.println("Enter phone number:");
		addressBookEntry.setPhoneNumber(br.readLine());
		System.out.println("Enter address:");
		addressBookEntry.setAdress(br.readLine());
		System.out.println("Enter email address:");
		addressBookEntry.setEmailAddress(br.readLine());
		return addressBookEntry;
	}

	public static void printOptions() {
		System.out.println("1) By first name:");
		System.out.println("2) By last name:");
		System.out.println("3) By phone number :");
		System.out.println("4) By address:");
		System.out.println("5) By email id:");
		System.out.println("6) By ID:");
		System.out.println("Enter your choice:");
	}

	public static boolean validateFile(String fileName) {

		File file = new File(fileName);
		return file.exists();
	}

	public static List<AddressTemplate> getSearchedEntries(AddressBook addressBook,BufferedReader br)throws Exception{
		clearScreen();
		System.out.println("******************************* Search Entry *******************************");


		int searchChoice = 0;

		while(true) {
			System.out.println("\nSearch the entry by follwoing:");
			printOptions();
			try {
				searchChoice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("\nError: Input should be integer number.");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(searchChoice > 0 && searchChoice < 7)
			{
				break;
			} else {
				continue;
			}
		}

		int criteriaChoice = 0;

		while(true) {
			System.out.println("\nSearch the entry by follwoing:");
			System.out.println(" \n1. Exact match" +
					"\n2. Starts with"+
					"\n3. Ends with" +
					"\n4. Contains");
			try {
				criteriaChoice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("\nError: Input should be integer number.");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if( criteriaChoice > 0 && criteriaChoice < 5) {
				break;
			}
			else {
				continue;
			}
		}

		CriteriaEnums criteria = CriteriaEnums.CONTAINS;

		switch (criteriaChoice) {
		case 1:
			criteria = CriteriaEnums.EXACT_MATCH;
			break;
		case 2:
			criteria = CriteriaEnums.STARTS_WITH;
			break;
		case 3:
			criteria = CriteriaEnums.ENDS_WITH;
			break;
		case 4:
			criteria = CriteriaEnums.CONTAINS;
			break;
		default:
			criteria = CriteriaEnums.CONTAINS;
			break;
		}

		System.out.println("Please enter text to be searched:");
		String searchText = br.readLine();
		List<AddressTemplate> searchResultList = null;

		switch (searchChoice) {
		case 1:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_FIRST_NAME, criteria);
			break;
		case 2:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_LAST_NAME, criteria);
			break;
		case 3:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_PHONE_NUMBER, criteria);
			break;
		case 4:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_ADDRESS, criteria);
			break;
		case 5:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_EMAIL, criteria);
			break;
		case 6:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_ID, criteria);
			break;	
		default:
			searchResultList = addressBook.search(searchText, FieldEnums.BY_ID, criteria);
			break;
		}

		if (searchResultList.size() == 0) {
			System.out.println("No match found!!");
		} else {
			System.out.println(
					"*********************************** Match found *****************************************");
			for (AddressTemplate entry : searchResultList) {


				System.out.println(entry);
				System.out.println(
						"***********************************************************************************************");
			}
		}
		return searchResultList;
	}
}