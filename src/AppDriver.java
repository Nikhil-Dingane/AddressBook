import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AppDriver {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        AddressBook addressBook=new AddressBook();
        
        while(true)
        {
            clearScreen();
            System.out.println("\n\n1) Load from file");
            System.out.println("2) Save to file");
            System.out.println("3) Add an entry");
            System.out.println("4) Remove an entry");
            System.out.println("5) Edit an existing entry");
            System.out.println("6) Sort the address book");
            System.out.println("7) Search for a specific entry");
            System.out.println("8) Display address book");
            System.out.println("9) Quit");

            System.out.println("\nPlease choose what you'd like to do with the database:");

            
            int choice=Integer.parseInt(br.readLine());

            switch(choice)
            {
                case 1:
                	addressBook.readRecordsFromFile();
                    break;

                case 2:
                	addressBook.saveRecordsToFile();
                	System.out.println("Records Successfully saved to files.");
                    break;

                case 3:
                	
                	System.out.println("******************************* New Entry *******************************");
                	
                	AddressTemplate record=new AddressTemplate();
                	
                	System.out.println("Enter first name:");
                	record.setFirstName(br.readLine());
                	System.out.println("Enter last name:");
                	record.setLastName(br.readLine());
                	System.out.println("Enter phone number:");
                	record.setPhoneNumber(br.readLine());
                	System.out.println("Enter address:");
                	record.setAdress(br.readLine());
                	System.out.println("Enter email address:");
                	record.setEmailAddress(br.readLine());
                	
                	if(addressBook.addRecord(record))
                	{
                		System.out.println("Record successfully inserted");
                	}
                	else {
                		System.out.println("Unable to insert record");
					}
                	
                	break;

                case 4:
                	clearScreen();
                	System.out.println("******************************* Remove Entry *******************************");
                	
                	System.out.println("1) Remove by ID");
                	System.out.print("2) Remove by information"); 
                	System.out.print("\nEnter you choice to remove records:");
                	int removeChoice=Integer.parseInt(br.readLine());
                	if(removeChoice==1)
                	{
                		System.out.println("Enter ID:");
                		if(addressBook.removeRecord(Integer.parseInt(br.readLine())))
                    	{
                    		System.out.println("Record successfully deleted...");
                    	}
                    	else {
							System.out.println("No such a reocrd to delete or Unable to remove record");
						}
                	}
                	else if(removeChoice==2)
                	{
                		AddressTemplate removablerecord=new AddressTemplate();
                    	
                    	System.out.println("Enter first name:");
                    	removablerecord.setFirstName(br.readLine());
                    	System.out.println("Enter last name:");
                    	removablerecord.setLastName(br.readLine());
                    	System.out.println("Enter phone number:");
                    	removablerecord.setPhoneNumber(br.readLine());
                    	
                    	System.out.println(removablerecord.getFirstName()+removablerecord.getLastName()+removablerecord.getPhoneNumber());
                    	if(addressBook.removeRecord(removablerecord))
                    	{
                    		System.out.println("Record successfully deleted...");
                    	}
                    	else {
							System.out.println("No such a reocrd to delete or Unable to remove record");
						}
                	}
                	else {
						
					}
                	
                    break; 

                case 5:
                	clearScreen();
                	System.out.println("******************************* Update Entry *******************************");
                	System.out.println("\nUpdate id of the entry to be updated:");
                	int updateId=Integer.parseInt(br.readLine());
                	AddressTemplate recordToBeUpdated=null;
                	List<AddressTemplate> recordToBeUpdatedList=addressBook.search(""+updateId,6);
                	if(recordToBeUpdatedList.size()==1)
                	{
                	    recordToBeUpdated=recordToBeUpdatedList.get(0);
                		System.out.println("***********************************************************************************************");
        				System.out.println("ID : "+recordToBeUpdated.getId());
        				System.out.println("First name : "+recordToBeUpdated.getFirstName());
        				System.out.println("last name : "+recordToBeUpdated.getLastName());
        				System.out.println("Phone number : "+recordToBeUpdated.getPhoneNumber());
        				System.out.println("Address : "+recordToBeUpdated.getAdress());
        				System.out.println("Email ID : "+recordToBeUpdated.getEmailAddress());
        				System.out.println("***********************************************************************************************");
                	}
                	else {
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
                	int updateChoice=Integer.parseInt(br.readLine());
                	
                	System.out.println("Enter the text:");
                	String updateTextString=br.readLine();
                	
                	if(updateChoice>0 && updateChoice<6) 
                	{
                		addressBook.updateRecord(recordToBeUpdated, updateTextString, updateChoice);
                		System.out.println("Record updated successfully");
                	}
                	else {
						System.out.println("Invalid choice!!");
					}
                    break;  

                case 6:
                	addressBook.sort();
                    break;

                case 7:
                	clearScreen();
                	System.out.println("******************************* Search Entry *******************************");
                	System.out.println("\nSearch the entry by follwoing:");
                	System.out.println("1) By first name:");
                	System.out.println("2) By last name:");
                	System.out.println("3) By phone number :");
                	System.out.println("4) By address:");
                	System.out.println("5) By email id:");
                	System.out.println("6) By ID:");
                	System.out.println("Enter your choice:");
                	int searchChoice=Integer.parseInt(br.readLine());
                	if(searchChoice>0 && searchChoice<7)
                	{
                		System.out.println("Please enter text to be searched:");
                		String searchText=br.readLine();
                		List<AddressTemplate> searchResultList=(List<AddressTemplate>)addressBook.search(searchText, searchChoice);
                		if(searchResultList.size()==0)
                		{
                			System.out.println("No match found!!");
                		}
                		else {
                			for(AddressTemplate singleRecord: searchResultList)
                			{
                				System.out.println("***********************************************************************************************");
                				System.out.println("ID : "+singleRecord.getId());
                				System.out.println("First name : "+singleRecord.getFirstName());
                				System.out.println("last name : "+singleRecord.getLastName());
                				System.out.println("Phone number : "+singleRecord.getPhoneNumber());
                				System.out.println("Address : "+singleRecord.getAdress());
                				System.out.println("Email ID : "+singleRecord.getEmailAddress());
                			}
						}
                	}
                	else {
						System.out.println("Invalid choice!!");
					}
                    break;
                    
                case 8:
                	addressBook.display();
                	break;

                case 9:
                    System.out.println("\nExiting from the program...\n\nPress any key to get exit....");
                    br.readLine();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choic");
                    break;
            }
            System.out.println("\nPress any key to continue.....");
            br.readLine();
        }
    }

    public static void clearScreen() 
    {
        System. out. print("\033[H\033[2J");
        System. out. flush();
    }
}