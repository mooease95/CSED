import java.util.List;
import java.util.ArrayList;

public class Database implements IDatabase<Contact>
{
	// Lists of objects to store here
	// Class diagram needed
	// Partial implementation of contacts used as an example
	List<Contact> contacts;

	public Database() 
	{
		contacts = new ArrayList<Contact>();
	}

	public List<Contact> getContacts() 
	{
		return contacts;
	}

	public void addItem(Contact c) {
		addContact(c);
	}
	public Contact getItemAtIndex(int index) {
		return contacts.get(index);
	}
	public void updateItemAtIndex(int index, Contact c) {
		contacts.set(index, c);
	}
	public Contact removeItemAtIndex(int index) {
		Contact removed = contacts.get(index);
		contacts.remove(index);
		return removed;
	}
	
	public void addContact(Contact c) 
	{
		contacts.add(c);
	}
	
	// /**
	// @Override
	// */
	// public Contact searchByWord(String searchWord) throws ContactNotFound{
	//
	// 	for(int i = 0; i < contacts.size(); i++){
	// 		Contact testItem = contacts.get(0);
	// 		if(checkMatch(testItem, searchWord)){
	// 			return testItem;
	// 		}
	// 	}
	// 	throw new ContactNotFound("Contact not found in database");
	// }
	
	public boolean checkMatch(Contact contact, String searchName){
		String actualName = contact.getName();
		if(actualName.toLowerCase().contains(searchName.toLowerCase())){
			return true;
		} else {
			return false;
		}
	}
	
	public int size() {
		return contacts.size();
	}
	
	public Contact searchByWord(String searchWord) {
		for(int i = 0; i < contacts.size(); i++){
			Contact testItem = contacts.get(0);
			if(checkMatch(testItem, searchWord)){
				return testItem;
			}
		}
		return null;
	}
	
}
