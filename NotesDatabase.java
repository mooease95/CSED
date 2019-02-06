/**
 * Notes database
 * Keeps a store of notes. 
 * Allows adding, editing and deleting notes.
 */

import java.util.ArrayList;

public class NotesDatabase implements IDatabase<Note> {
	
	private ArrayList<Note> items;
	
	/**
	 * Create a new database of notes.
	 */
	public NotesDatabase() {
		items = new ArrayList<Note>();
	}
	
	public void addItem(Note n) {
		items.add(n);
	}
	
	public Note getItemAtIndex(int index) {
		Note itemToGet = null;
		if (index >= 0 && index < items.size()) {
			itemToGet = items.get(index);
		}
		return itemToGet;
	}
	
	// returns null if there is nothing stored at this index
	public Note removeItemAtIndex(int index) {
		Note removedItem = getItemAtIndex(index);
		if (removedItem != null) { // There was something stored at this index - remove it. 
			items.remove(index);
		}
		return removedItem;
	}
	
	public void updateItemAtIndex(int index, Note newNote) {
		if (index >= 0 && index < items.size()) {
			items.set(index, newNote);
		}
	} 
	
	/**
	 * Returns index of note with given title, -1 if none
	 * @param noteTitle title of note to get
	 * @return index of note with given title, -1 if none
	 */
	public int getIndexOfNoteWithTitle(String noteTitle) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getTitle().equalsIgnoreCase(noteTitle)) {
				return i;
			}
		}
		// NOt found
		return -1;
	}
	
	/**
	 * Returns index of first item with specified tags
	 * @param tags tags that note must have
	 * @return index of first note in database with these tags, -1 if none. 
	 */
	public int getIndexOfNoteWithTags(String[] tags) {
		
		return -1;
	}
	
	public int size() {
		return items.size();
	}
	
	public Note searchByWord(String word) {
		return null;
	}
	
}