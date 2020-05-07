import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileWriter {

	private ObjectOutputStream objectOutputStream;
	private FileOutputStream fileOutputStream;
	
	public FileWriter() {
		
	}
	
	public FileWriter(String fileName) {
		try {
			this.fileOutputStream = new FileOutputStream(fileName);
			this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void writeFile(List<AddressTemplate> addressBookList) {
		try {
			objectOutputStream.writeObject(addressBookList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.fileOutputStream.close();
			this.objectOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
