import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	private ObjectInputStream objectInputStream;
	private FileInputStream fileInputStream;
	
	public FileReader() {
		
	}
	public FileReader(String filenameString) {
		try {
			this.fileInputStream = new FileInputStream(filenameString);
			this.objectInputStream = new ObjectInputStream(this.fileInputStream);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public List<AddressTemplate> readFile()	{
		List<AddressTemplate> addressBookList = new ArrayList<AddressTemplate>();
		try {
			addressBookList = (List<AddressTemplate>) objectInputStream.readObject();
			return addressBookList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return addressBookList;
		}
	}
	
	public void close() {
		try {
			this.fileInputStream.close();
			this.objectInputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
