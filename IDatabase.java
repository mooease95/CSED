/**
 * IDatabase is an interface that defines methods that any database in the PIM should implement, including the methods required for PersistendStorage
 * All items in a database should have an index - this could be the index of an element in an Array/ArrayList or key in a HashMap... or whatever. The only 
 * requirement is that it is an integer.
 * This IDatabase interface also extends Serializable which will allow the database to be saved using PersistandStorage.
 * When implementing the IDatabase, it is necessary to identify the type of items that are stored in the database. E.g for a database of Contact objects:
 *     public class ContactsDatabase implements IDatabase<Contact>
 */
// NOTE this is just an idea - if you don't like it just delete it! ;-)

import java.io.Serializable;

public interface IDatabase<Item> extends Serializable {
	
	/**
	 * Adds an item to the database
	 * @param item item to add
	 */
	public void addItem(Item item);
	
	/**
	 * Retrieves item at given index
	 * @param index index of item to get
	 * @return item at given index
	 */
	public Item getItemAtIndex(int index);
	
	/**
	 * Removes item with given index.
	 * @param itemIndex index of item to remove
	 * @return the item that was removed
	 */
	public Item removeItemAtIndex(int index);
	
	/**
	 * Updates an item in the database with a new item.
	 * @param itemIndex index of item to update
	 * @param newItem updated item.
	 */
	public void updateItemAtIndex(int itemIndex, Item item);
	
	
	/**
	 * Carries out a search of the database based on a particular String
	 * rather than an index.
	 * @param searchWord the word to search for in the database
	 */
	 public Item searchByWord(String searchWord);
	
	/**
	 * Returns the number of elements in the database.
	 * @return number of items in the database.
	 */
	public int size();
	
}
