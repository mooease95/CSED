import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DatabaseStorage {
	
	/**
	 * Saves an IDatabase object to a file.
	 * @param db IDatabase to save
	 * @param filename file name to use when saving.
	 */
	public static void save(IDatabase db, String filename) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(db);
		out.close();
		fileOut.close();
	}
	
	/**
	 * Restores an IDatabase object from a saved file with given name
	 * @param filename name of file where IDatabase object to resotre is saved. 
	 */
	public static IDatabase restore(String filename) throws IOException, FileNotFoundException, ClassNotFoundException {
		IDatabase db = null;
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		db = (IDatabase) in.readObject();
		in.close();
		fileIn.close();
		return db;
	}
}