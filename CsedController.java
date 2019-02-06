/**
 * Controller for the CSED UI.
 * This deals with passing data between the UI and the databases. 
 */

import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;
 
public class CsedController {
	
	// The maximum number of characters that can appear in the name of something on the title. 
	private static final int MAX_TITLE_LENGTH = 15;
	
	private CsedUi ui = new CsedUi();
	private IDatabase<Contact> contactsDb;
	private IDatabase<Note> notesDb;
	private IDatabase<Appointment> appointmentsDb;
	
	private boolean isEditing = false; // is the item on the right of the GUI being edited. 
	
	public CsedController() {
		// Loading up the databases
		try {
			contactsDb = (Database)DatabaseStorage.restore("contacts.data");
			notesDb = (NotesDatabase)DatabaseStorage.restore("notes.data");
			appointmentsDb = (AppointmentOrganiser)DatabaseStorage.restore("appointments.data");
		} catch (Exception e) { // TODO catch the IO and ClassNotFound exceptions separately			m = new NotesDatabase();
			contactsDb = new Database(); // Database is the contacts database name
			notesDb = new NotesDatabase();
			appointmentsDb = new AppointmentOrganiser();
			ui.showErrorDialog("Sorry, there was a problem loading your data.");
		}
		ui.show(); // showing the UI
		attachShutDownHook(); // This saves the databases out to file before quitting. 
		loadItemList(ui.getSelectedTab());
		addTabListener();
		addListSelectionListener();
		addListenerForAdd();
		addListenerForEdit();
		addListenerForDelete();
	}
	
	private void loadItemList(int selectedTab) {
		ui.setNoItemSelected();
		ui.setEditing(false);	
		switch (selectedTab) {
			case CsedUi.NOTES:
			for (int i = 0; i < notesDb.size(); i++) {
				ui.addItemToList(notesDb.getItemAtIndex(i).getTitle());
			}
			break;
			case CsedUi.CONTACTS:
			for (int i = 0; i < contactsDb.size(); i++) {
				ui.addItemToList(contactsDb.getItemAtIndex(i).getName());
			}
			break;
			case CsedUi.APPOINTMENTS:
			for (int i = 0; i < appointmentsDb.size(); i++) {
				ui.addItemToList(appointmentsDb.getItemAtIndex(i).getTitle());
			}
			break;
		}	
	}
	
	
	// Add a change listener to the tabs to react when page is switched
	public void addTabListener() {
		ui.addChangeListenerForTabs(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ui.loadSelectedTab();
				loadItemList(ui.getSelectedTab());
			}
		});
	}
	
	// Adding a new item to the database.
	private void addListenerForAdd() {
		ui.addActionListenerForCreateButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.clearListSelection();
				isEditing = true;
				ui.setEditing(isEditing);
				ui.createNew();
			}
		});
	}
	
	// Adds/updates an item in one of the 4 databases
	private void updateDb(int selectedTab, int selectedIndex) {
		switch(selectedTab) {
			case CsedUi.NOTES:
			Note n = new Note(ui.getNoteTitle(), ui.getNoteText());
			if (selectedIndex < 0) {
				notesDb.addItem(n);
				ui.addItemToList(n.getTitle());
				ui.selectListItem(n.getTitle());
			} else {
				notesDb.updateItemAtIndex(selectedIndex, n);
				ui.updateListItem(selectedIndex, n.getTitle());
			}
			break;
			case CsedUi.CONTACTS:
			Contact c = new Contact(ui.getContactName(), ui.getContactEmail(), ui.getContactHomeNumber(), ui.getContactWorkNumber(), ui.getContactMobileNumber(), ui.getContactAddress(), ui.getContactDescription());
			if (selectedIndex < 0) {
				contactsDb.addItem(c);
				ui.addItemToList(c.getName());
				ui.selectListItem(c.getName());
			} else {
				contactsDb.updateItemAtIndex(selectedIndex, c);
				ui.updateListItem(selectedIndex, c.getName());
			}
			break;
			case CsedUi.APPOINTMENTS:
			Appointment a = new Appointment(ui.getAppointmentTitle(), ui.getAppointmentDate(), ui.getAppointmentTime(), ui.getAppointmentLocation(), ui.getAppointmentReference());
			if (selectedIndex < 0) {
				appointmentsDb.addItem(a);
				ui.addItemToList(a.getTitle());
				ui.selectListItem(a.getTitle());
			} else {
				appointmentsDb.updateItemAtIndex(selectedIndex, a);
				ui.updateListItem(selectedIndex, a.getTitle());
			}
			break;
		}
	}
	
	// Edit/Save button
	// Turns on or off editing of selected item, if the item is being edited it is saved. 
	private void addListenerForEdit() {
		ui.addActionListenerForEditButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEditing) {
					int index = ui.getIndexOfSelectedListItem();
					updateDb(ui.getSelectedTab(), index);
					loadSelectedItem();
				}
				isEditing = !isEditing;
				ui.setEditing(isEditing);
			}
		});
	}
	
	// Delete/Cancel edit button
	// If editing revert item back to original
	// Otherwise ask user for confirmation and delete item.
	private void addListenerForDelete() {
		ui.addActionListenerForDeleteButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEditing) { // Delete = revert item back to original.
					isEditing = false;
					ui.setEditing(isEditing);
					loadSelectedItem();
				} else { // Delete item
					if (ui.showConfirmDialog("Do you really want to delete this item?", "Delete", "Cancel")) {
						deleteSelectedItem();
					}
				}
			}
		});
	}
	
	// Action when the selected list item changes.
	private void addListSelectionListener() {
		ui.addListSelectionListenerForItemList(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				loadSelectedItem();
			}
		});
	}
	
	private void loadSelectedItem() {
		int index = ui.getIndexOfSelectedListItem();
		if (index < 0) {
			ui.setNoItemSelected();
		} else {
			switch(ui.getSelectedTab()) {
				case CsedUi.NOTES:
				ui.setNoteEnabled();
				loadNote(notesDb.getItemAtIndex(ui.getIndexOfSelectedListItem()));
				break;
				case CsedUi.CONTACTS:
				ui.setContactEnabled();
				loadContact(contactsDb.getItemAtIndex(ui.getIndexOfSelectedListItem()));
				break;
				case CsedUi.APPOINTMENTS:
				ui.setAppointmentEnabled();
				loadAppointment(appointmentsDb.getItemAtIndex(ui.getIndexOfSelectedListItem()));
			}
		}
	}
	
	private void deleteSelectedItem() {
		int selection = ui.getIndexOfSelectedListItem();
		switch(ui.getSelectedTab()) {
			case CsedUi.CONTACTS:
			contactsDb.removeItemAtIndex(selection);
			break;
			case CsedUi.NOTES:
			notesDb.removeItemAtIndex(selection);
			break;
			case CsedUi.APPOINTMENTS:
			appointmentsDb.removeItemAtIndex(selection);
			break;
		}
		
		ui.removeItemFromList(selection);
	}
	
	// Loads a note into the UI's text field. 
	private void loadNote(Note n) {
		if (n!= null) {
			String shortTitle = n.getTitle();
			if (shortTitle.length() > MAX_TITLE_LENGTH) {
				shortTitle = shortTitle.substring(0, MAX_TITLE_LENGTH) + "...";
			}
			ui.setTitleLabelText("Displaying note \"" + shortTitle + "\"");
			ui.setNoteTitle(n.getTitle());
			ui.setNoteText(n.getText());
		}
	}
	
	// Loads a contact into the UI's text fields.
	private void loadContact(Contact c) {
		if (c != null) {
			String contactTitle = c.getName();
			if (contactTitle.length() > MAX_TITLE_LENGTH) {
				contactTitle = contactTitle.substring(0, MAX_TITLE_LENGTH) + "...";
			}
			ui.setTitleLabelText("\"" + contactTitle + "\"'s contact info");
			ui.setContactName(c.getName());
			ui.setContactDescription(c.getDescription());
			ui.setContactEmail(c.getEmail());
			ui.setContactHomeNumber(c.getHomeNumber());
			ui.setContactWorkNumber(c.getWorkNumber());
			ui.setContactMobileNumber(c.getMobileNumber());
			ui.setContactAddress(c.getAddress());
		}
	}
	
	private void loadAppointment(Appointment a) {
		if (a != null) {
			String appointmentTitle = a.getTitle();
			if (appointmentTitle.length() > MAX_TITLE_LENGTH) {
				appointmentTitle = appointmentTitle.substring(0, MAX_TITLE_LENGTH) + "...";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String appointmentDate = sdf.format(a.getDate());
			
			ui.setTitleLabelText("\"" + appointmentTitle + "\" on " + appointmentDate);
			ui.setAppointmentTitle(a.getTitle());
			ui.setAppointmentDate(a.getDate());
			ui.setAppointmentTime(a.getTime());
			ui.setAppointmentLocation(a.getLocation());
			ui.setAppointmentReference(a.getLocation());
		}
	}
	
	// Adds a shutdown hook to the program that saves out the databases to file.
	private void attachShutDownHook(){
		// Found here: http://hellotojavaworld.blogspot.co.uk/2010/11/runtimeaddshutdownhook.html
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					DatabaseStorage.save(contactsDb, "contacts.data");
					DatabaseStorage.save(notesDb, "notes.data");
					DatabaseStorage.save(appointmentsDb, "appointments.data");
				} catch (IOException e) {
					ui.showErrorDialog("There was a problem saving your ddata to file, sorry about that.");
				}
			}
		});
	}
	
}