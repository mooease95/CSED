/**
 * An idea for a user interface for the CSED.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class CsedUi {
	
	public CsedUi() {
		f = new JFrame();
		
		// Create main panel using boxLayout
		JPanel mainPanel = new JPanel();
		f.getContentPane().add(mainPanel);
		
		// Initialise the buttons and list
		cmdCreate = new JButton("New...");
		cmdEdit = new JButton("Edit");
		cmdDelete = new JButton("Delete");
		theList = new JList<String>();
		
		tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(800, 650));
		tabs.setBorder(null);
				
		tabs.addTab("Appointments", null, new JPanel());
		tabs.addTab("Contacts", null, new JPanel());
		tabs.addTab("Todo List", null, new JPanel());
		tabs.addTab("Notes", null, new JPanel());
		
		
		mainPanel.add(tabs);
	}
	
	/*
	 * JTabbedPane doesn't allow you to have the same component shared across multiple tabs - e.g the buttons or 
	 * item list on the left. 
	 * To solve this problem I've decided to initially load blank tabs and then load the panel into one of them when it gets selected. 
	 */
	public void loadSelectedTab() {
		
		int index = getSelectedTab();
		JPanel itemListPanel = createItemList();
		JPanel topControls = createTopControls();
		switch(index) {
			case APPOINTMENTS:
			tabs.setComponentAt(index, createAppointmentsTab(itemListPanel, topControls));
			break;
			case CONTACTS:
			tabs.setComponentAt(index, createContactsTab(itemListPanel, topControls));
			break;
			case TODO:
			tabs.setComponentAt(index, createTodoTab(itemListPanel, topControls));
			break;
			case NOTES:
			tabs.setComponentAt(index, createNoteTab(itemListPanel, topControls));
			break;
		}
	}
	
	/**
	 * Get text from search field
	 * @return contents of search field
	 */
	public String getSearchText() {
		return searchField.getText();
	}
	
	/**
	 * Add an action listener to the create button.
	 * @param l listener to add
	 */
	public void addActionListenerForCreateButton(ActionListener l) {
		cmdCreate.addActionListener(l);
	}
	
	/**
	 * Add an action listener to the edit button.
	 * @param l listener to add
	 */
	public void addActionListenerForEditButton(ActionListener l) {
		cmdEdit.addActionListener(l);
	}
	
	/**
	 * Add an action listener to the delete button.
	 * @param l listener to add
	 */
	public void addActionListenerForDeleteButton(ActionListener l) {
		cmdDelete.addActionListener(l);
	}
	
	/**
	 * Add a list selection listener to the item list.
	 * @param l listener to add
	 */
	public void addListSelectionListenerForItemList(ListSelectionListener l) {
		theList.addListSelectionListener(l);
	}
	
	
	/**
	 * Set title label text
	 * @param t text to set
	 */
	public void setTitleLabelText(String t) {
		titleLabel.setText(t);
	}
	
	/**
	 * @return value fo contactName text field
	 */
	public String getContactName() {
		return contactName.getText();
	}
	
	/**
	 * Sets value of contactName text field
	 */
	public void setContactName(String value) {
		contactName.setText(value);
	}
	
	/**
	 * @return value fo contactDescription text field
	 */
	public String getContactDescription() {
		return contactDescription.getText();
	}
	
	/**
	 * Sets value of contactDescription text field
	 */
	public void setContactDescription(String value) {
		contactDescription.setText(value);
	}
	
	/**
	 * @return value fo contactEmail text field
	 */
	public String getContactEmail() {
		return contactEmail.getText();
	}
	
	/**
	 * Sets value of contactEmail text field
	 */
	public void setContactEmail(String value) {
		contactEmail.setText(value);
	}
	
	/**
	 * @return value fo contactHomeNumber text field
	 */
	public String getContactHomeNumber() {
		return contactHomeNumber.getText();
	}
	
	/**
	 * Sets value of contactName text field
	 */
	public void setContactHomeNumber(String value) {
		contactHomeNumber.setText(value);
	}
	
	/**
	 * @return value fo contactWorkNumber text field
	 */
	public String getContactWorkNumber() {
		return contactWorkNumber.getText();
	}
	
	/**
	 * Sets value of contactWorkNumber text field
	 */
	public void setContactWorkNumber(String value) {
		contactWorkNumber.setText(value);
	}
	
	/**
	 * @return value fo contactMobileNumber text field
	 */
	public String getContactMobileNumber() {
		return contactMobileNumber.getText();
	}
	
	/**
	 * Sets value of contactMobileNumber text field
	 */
	public void setContactMobileNumber(String value) {
		contactMobileNumber.setText(value);
	}
	
	/**
	 * @return value fo contactAddress text field
	 */
	public String getContactAddress() {
		return contactAddress.getText();
	}
	
	/**
	 * Sets value of contactAddress text field
	 */
	public void setContactAddress(String value) {
		contactAddress.setText(value);
	}
	
	/**
	 * @return value fo appointmentTitle text field
	 */
	public String getAppointmentTitle() {
		return appointmentTitle.getText();
	}
	
	/**
	 * Sets value of appointmentTitle text field
	 */
	public void setAppointmentTitle(String value) {
		appointmentTitle.setText(value);
	}
	
	/**
	 * @return value fo appointmentDate text field
	 */
	public Date getAppointmentDate() {
		return (Date)appointmentDate.getValue();
	}
	
	/**
	 * Sets value of appointmentTitle text field
	 */
	public void setAppointmentDate(Date value) {
		appointmentDate.setValue(value);
	}
	
	/**
	 * @return value fo appointmentTime text field
	 */
	public Date getAppointmentTime() {
		return (Date)appointmentTime.getValue();
	}
	
	/**
	 * Sets value of appointmentTime text field
	 */
	public void setAppointmentTime(Date value) {
		appointmentTime.setValue(value); // TODO set value to current time. 
	}
	
	/**
	 * @return value fo appointmentLocation text field
	 */
	public String getAppointmentLocation() {
		return appointmentLocation.getText();
	}
	
	/**
	 * Sets value of appointmentLocation text field
	 */
	public void setAppointmentLocation(String value) {
		appointmentLocation.setText(value);
	}
	
	/**
	 * @return value fo appointmentReference text field
	 */
	public String getAppointmentReference() {
		return appointmentReference.getText();
	}
	
	/**
	 * Sets value of appointmentReference text field
	 */
	public void setAppointmentReference(String value) {
		appointmentReference.setText(value);
	}
	
	/**
	 * Clears the list selection
	 */
	public void clearListSelection() {
		theList.clearSelection();
	}
	
	/**
	 * Add an item to the list on the left of the GUI
	 * @param item the item to add. 
	 */
	public void addItemToList(String item) {
		itemListModel.addElement(item);
	}
	
	/**
	 * Set the item in the list with the specified value
	 * @param item value of item to select
	 */
	public void selectListItem(String item) {
		theList.setSelectedIndex(itemListModel.indexOf(item));
	}
	
	/**
	 * Remove the item with specified index from the list on the left of the GUI
	 * @param index
	 */
	public void removeItemFromList(int index) {
		itemListModel.remove(index);
		theList.clearSelection();
	}
	
	/**
	 * Update an item at the specified index in the list on the left of the GUI
	 * @param item the item to add. 
	 * @param index index of item to update - index of non-existant item wil do nothing. 
	 */
	public void updateListItem(int index, String item) {
		itemListModel.set(index, item);
	}
	
	/**
	 * Gets the selected index in the list. 
	 * @return selected index in the list
	 */
	public int getIndexOfSelectedListItem() {
		return theList.getSelectedIndex();
	}
	
	/**
	 * Returns the index of the selected tab
	 * @return index of selected tab
	 */
	public int getSelectedTab() {
		return tabs.getSelectedIndex();
	}
	
	/**
	 * Shows the user a confirm dialog box with given message. Returns true if the user clocked the default (yes) button
	 * @param message message to go in the dialog
	 */
	public boolean showConfirmDialog(String message) {
		int selection =	JOptionPane.showConfirmDialog(f, message,"", JOptionPane.YES_NO_OPTION);
		return selection == 0;
	}
	
	/**
	 * Display an error dialog message
	 * @param message error message to show. 
	 */
	public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(f, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Shows a YES/NO-style dialog box with a message and two buttons.
	 * @param message dialog message
	 * @param ok text for the OK (default) button text
	 * @param cancel text for the Cancel button text
	 * @return true if OK, false if Cancel
	 */
	public boolean showConfirmDialog(String message, String ok, String cancel) {
		int answer = JOptionPane.showOptionDialog(null, 
		        message, 
		        "", 
		        JOptionPane.YES_NO_OPTION, 
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        new String[]{ok, cancel},
		        ok);
		return answer == 0;
	}
	
	/**
	 * Shows a dialog message with 3 butons.
	 * @param message error message to show.
	 * @param yes yes button - this is the default button so it responds to ENTER.
	 * @param no no button.
	 * @param cancel - cancel button (reacts to escape) 
	 */
	public int showYesNoCancelDialog(String message, String yes, String no, String cancel) {
		int answer = JOptionPane.showOptionDialog(null, 
		        message, 
		        "", 
		        JOptionPane.YES_NO_CANCEL_OPTION, 
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        new String[]{yes, no, cancel},
		        "default");
		return answer;
	}
	
	/**
	 * Add a change listener to the JTabbedPane.
	 * @param l the change listener to add.
	 */
	public void addChangeListenerForTabs(ChangeListener l) {
		tabs.addChangeListener(l);
	}
	
	/**
	 * Shows the GUI
	 * Packs the JFrame, sets it location to the centre of the screen and sets it visible. 
	 */
	public void show() {
		f.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, 40);
		f.setVisible(true);
		tabs.setSelectedIndex(0);
		loadSelectedTab();
	}
	
	private JPanel createNoteTab(JPanel itemList, JPanel topControls) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
				
		JPanel note = createNotePanel();
		
		JSplitPane splitPane = createSplitPane(itemList, note, topControls);
		
		p.add(splitPane, BorderLayout.CENTER);
		return p;
	}
	
	private JPanel createAppointmentsTab(JPanel itemList, JPanel topControls) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
						
		JSplitPane splitPane = createSplitPane(itemList, createAppointmentsPanel(), topControls);
		
		p.add(splitPane, BorderLayout.CENTER);
		return p;
	}
	
	private JPanel createAppointmentsPanel() {
		JPanel appointmentsPanel = new JPanel();
		appointmentsPanel.setLayout(new GridBagLayout());
		appointmentsPanel.setBackground(Color.white);
		appointmentsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.EAST;
		c.gridheight = 1;
		
		c.gridy = 1;
		appointmentTitleLabel = new JLabel("Title:", SwingConstants.RIGHT);
		appointmentsPanel.add(appointmentTitleLabel, c);
		c.gridy = 2;
		c.gridheight = 2;
		appointmentDateLabel = new JLabel("When:", SwingConstants.RIGHT);
		appointmentsPanel.add(appointmentDateLabel, c);
		c.gridheight = 1;
		c.gridy = 4;
		appointmentLocationLabel = new JLabel("Location:", SwingConstants.RIGHT);
		appointmentsPanel.add(appointmentLocationLabel, c);
		c.gridy = 5;
		appointmentReferenceLabel = new JLabel("Reference:", SwingConstants.RIGHT);
		appointmentsPanel.add(appointmentReferenceLabel, c);
		
		// Now for the text fields. 
		c.gridwidth = 10;
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		
		appointmentTitle = new JTextField(20);
		appointmentTitle.setText("New Appointment");
		Font font = titleLabel.getFont();
		font = font.deriveFont(Font.BOLD, font.getSize());
		appointmentTitle.setFont(font);
		appointmentsPanel.add(appointmentTitle, c);
		c.gridy = 2;
		c.gridheight = 2;
		JPanel dateAndTimePanel = new JPanel();
		dateAndTimePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dateAndTimePanel.setBorder(new EmptyBorder(0,0,0,0));
		dateAndTimePanel.setBackground(null);
		appointmentOnLabel = new JLabel("On ");
		dateAndTimePanel.add(appointmentOnLabel);
		appointmentDate = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(appointmentDate, "dd/MM/yyyy");
		appointmentDate.setEditor(dateEditor);
		dateAndTimePanel.add(appointmentDate);
		appointmentAtLabel = new JLabel(" at ");
		dateAndTimePanel.add(appointmentAtLabel);
		appointmentTime =  new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(appointmentTime, "HH:mm");
		appointmentTime.setEditor(timeEditor);
		dateAndTimePanel.add(appointmentTime);
		appointmentsPanel.add(dateAndTimePanel,c);
		c.gridheight = 1;
		c.gridy = 4;
		appointmentLocation = new JTextField("");
		appointmentsPanel.add(appointmentLocation, c);
		c.gridy = 5;
		c.gridheight = 3;
		appointmentReference = new JTextArea(3,20);
		appointmentReference.setLineWrap(true);
		appointmentReference.setBorder(appointmentTitle.getBorder());
		appointmentsPanel.add(appointmentReference, c);
		
		return appointmentsPanel;
	}
	private void displayAppointmentLabels(boolean b) {
		if (b == true) {
			appointmentTitleLabel.setText("Title:");
			appointmentDateLabel.setText("When:");
			appointmentLocationLabel.setText("where:");
			appointmentReferenceLabel.setText("Reference:");
		} else {
			appointmentTitleLabel.setText("");
			appointmentDateLabel.setText("");
			appointmentLocationLabel.setText("");
			appointmentReferenceLabel.setText("");
		}
	}
	
	private JPanel createTodoTab(JPanel itemList, JPanel topControls) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
						
		JSplitPane splitPane = createSplitPane(itemList, new JPanel(), topControls);
		
		p.add(splitPane, BorderLayout.CENTER);
		return p;
	}
	private JPanel createContactsTab(JPanel itemList, JPanel topControls) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());	
		
		JSplitPane splitPane = createSplitPane(itemList, createContactsPanel(), topControls);
		
		p.add(splitPane, BorderLayout.CENTER);
		return p;
	}
	
	private JPanel createTopControls() {
		JPanel topControls = new JPanel();
		topControls.setLayout(new BorderLayout());
		//cmdDelete = new JButton("Delete");
		cmdDelete.setFocusable(false);
		topControls.add(cmdDelete, BorderLayout.WEST);
		titleLabel = new JLabel("", SwingConstants.CENTER);
		Font font = titleLabel.getFont();
		font = font.deriveFont(Font.BOLD, font.getSize());
		titleLabel.setFont(font);
		topControls.add(titleLabel, BorderLayout.CENTER);
		//cmdEdit = new JButton("Edit");
		cmdEdit.setFocusable(false);
		topControls.add(cmdEdit, BorderLayout.EAST);
		return topControls;
	}
	
	private JSplitPane createSplitPane(JPanel left, JPanel right, JPanel topControls) {
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(topControls, BorderLayout.PAGE_START);
		rightPanel.add(right, BorderLayout.CENTER);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		    left, rightPanel);
		
		splitPane.setDividerLocation(400);
		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(200);
		Dimension minimumSize = new Dimension(100, 50);
		left.setMinimumSize(minimumSize);
		right.setMinimumSize(minimumSize);
		return splitPane;
	}
	
	// Set up contacts panel
	private JPanel createContactsPanel() {
		JPanel contactsPanel = new JPanel();
		contactsPanel.setLayout(new GridBagLayout());
		
		contactsPanel.setBackground(Color.white);
		contactsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		GridBagConstraints  c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.WEST;
		c.gridheight = 1;
		
		c.anchor = GridBagConstraints.EAST;
		c.gridwidth = 1;
		// These are all the labels that indicate what each textfield is for...
		c.gridx = 1;
		c.gridy = 3;
		contactsPanel.add(new JLabel("Email:", SwingConstants.RIGHT), c);
		c.gridy = 4;
		contactsPanel.add(new JLabel("Home number:", SwingConstants.RIGHT), c);
		c.gridy = 5;
		contactsPanel.add(new JLabel("Work number:", SwingConstants.CENTER), c);
		c.gridy = 6;
		contactsPanel.add(new JLabel("Mobile number:", SwingConstants.CENTER), c);
		c.gridy = 7;
		contactsPanel.add(new JLabel("Address:", SwingConstants.RIGHT), c);
		
		// Add the text field
		c.gridwidth = 10;
		c.gridx = 2;
		c.gridy = 1;
		contactName = new JTextFieldWithPlaceholder("", "Name");
		Font font = contactName.getFont();
		font = font.deriveFont(Font.BOLD, font.getSize() * 2.0f);
		contactName.setFont(font);
		contactsPanel.add(contactName, c);
		c.gridy = 2;
		contactDescription = new JTextArea(2,20);
		contactDescription.setLineWrap(true);
		contactDescription.setBorder(contactName.getBorder());
		font = contactDescription.getFont();
		font = font.deriveFont(font.getSize() * 1.5f);
		contactDescription.setFont(font);
		contactsPanel.add(contactDescription, c);
		c.gridy = 3;
		contactEmail = new JTextField("");
		contactsPanel.add(contactEmail, c);
		c.gridy = 4;
		contactHomeNumber = new JTextField("");
		contactsPanel.add(contactHomeNumber, c);
		c.gridy = 5;
		contactWorkNumber = new JTextField("");
		contactsPanel.add(contactWorkNumber, c);
		c.gridy = 6;
		contactMobileNumber = new JTextField("");
		contactsPanel.add(contactMobileNumber, c);
		c.gridy = 7;
		c.gridheight = 5;
		contactAddress = new JTextArea(5, 10);
		contactAddress.setLineWrap(true);
		contactAddress.setBorder(contactName.getBorder());
		contactsPanel.add(contactAddress, c);
				
		return contactsPanel;
		
	}
	
	// Creates the note panel
	private JPanel createNotePanel() {
		JPanel notePanel = new JPanel();
		notePanel.setLayout(new BorderLayout());
		notePanel.setBackground(Color.white);
		noteTitle = new JTextFieldWithPlaceholder("No selection", "Your note's title...");
		noteTitle.setBorder(new EmptyBorder(5,10,5,10));
		Font font = noteTitle.getFont();
		font = font.deriveFont(Font.BOLD, font.getSize() * 2.0f);
		noteTitle.setFont(font);
		noteTitle.setEditable(false);
		notePanel.add(noteTitle, BorderLayout.PAGE_START);
		noteText = new JTextPane();
		noteText.setEditable(false);
		noteText.setBorder(new EmptyBorder(5,10,5,10));
		JScrollPane noteTextScroll = new JScrollPane(noteText);
		notePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		noteTextScroll.setBorder(null);
		notePanel.add(noteTextScroll, BorderLayout.CENTER);
		
		return notePanel;
	}
	/**
	 * Set whether or not the note is being edited.
	 * When a note is being edited the Edit button now reads save and the text fields are editable. 
	 * @param b is the note being edited?
	 */
	public void setEditing(boolean b) {
		theList.setEnabled(!b);
		cmdCreate.setEnabled(!b);
		switch (getSelectedTab()) {
			case NOTES:
			setEditingNote(b);
			break;
			case CONTACTS:
			setEditingContact(b);
			break;
			case APPOINTMENTS:
			setEditingAppointment(b);
			break;
		}
		
		if (b == true) {
			cmdEdit.setText("Save");
			cmdDelete.setText("Discard");
			titleLabel.setText(titleLabel.getText() + " (Editing)");
		} else {
			cmdEdit.setText("Edit");
			cmdDelete.setText("Delete");
		}
	}
	
	private void setEditingNote(boolean b) {
		
		noteTitle.setEditable(b);
		noteText.setEditable(b);
	}
	
	private void setEditingContact(boolean b) {
		contactName.setEditable(b);
		contactDescription.setEditable(b);
		contactEmail.setEditable(b);
		contactHomeNumber.setEditable(b);
		contactWorkNumber.setEditable(b);
		contactMobileNumber.setEditable(b);
		contactAddress.setEditable(b);
	}
	
	private void setEditingAppointment(boolean b) {
		displayAppointmentLabels(b);
		appointmentTitle.setEditable(b);
		appointmentDate.setEnabled(b);
		((JSpinner. DefaultEditor) appointmentDate.getEditor()).getTextField().setEditable(b);
		appointmentTime.setEnabled(b);
		((JSpinner. DefaultEditor) appointmentTime.getEditor()).getTextField().setEditable(b);
		appointmentLocation.setEditable(b);
		appointmentReference.setEditable(b);
		
		boolean textfieldsWereEnabled = ((JSpinner. DefaultEditor) appointmentDate.getEditor()).getTextField().isEnabled();
		
		appointmentDate.setEnabled(b);
		appointmentTime.setEnabled(b);
	}
	
	/**
	 * Greys out edit and delete buttons and title, set no sleection look on the selected tab. 
	 */
	public void setNoItemSelected() {
		cmdDelete.setEnabled(false);
		cmdEdit.setEnabled(false);
		switch(getSelectedTab()) {
			case NOTES:
			setNoNoteSelected();
			break;
			case CONTACTS:
			setNoContactSelected();
			break;
			case APPOINTMENTS:
			setNoAppointmentSelected();
			break;
		}
	}
	
	/**
	 * This method greys out the edit and delete buttons, prints "NO SELECTION" in the title and "Click a note in the list on the left to see it." in the text area.
	 */
	private void setNoNoteSelected() {
		titleLabel.setText("Your Notes");
		noteTitle.setText("No Selection");
		noteTitle.setEnabled(false);
		noteText.setText("Select a note in the list on the left to view it, or choose \"New...\" to create a new one.");
		noteText.setEnabled(false);
	}
	
	private void setNoContactSelected() {
		titleLabel.setText("Your Contacts");
		contactName.setText("No Selection");
		contactName.setEnabled(false);
		contactDescription.setText("Select a contact in the list on the left to view it, or choose \"New...\" to create a new one.");
		contactDescription.setEnabled(false);
		contactEmail.setText("");
		contactEmail.setEnabled(false);
		contactHomeNumber.setText("");
		contactHomeNumber.setEnabled(false);
		contactWorkNumber.setText("");
		contactWorkNumber.setEnabled(false);
		contactEmail.setText("");
		contactEmail.setEnabled(false);
		contactMobileNumber.setText("");
		contactMobileNumber.setEnabled(false);
		contactAddress.setText("");
		contactAddress.setEnabled(false);
	}
	
	private void setNoAppointmentSelected() {
		titleLabel.setText("Your Appointments");
		appointmentTitle.setEnabled(false);
		appointmentTitle.setText("No Selection");
		appointmentOnLabel.setEnabled(false);
		appointmentAtLabel.setEnabled(false);
		appointmentDate.setEnabled(false);
		appointmentTime.setEnabled(false);
		appointmentLocation.setText("");
		appointmentLocation.setEnabled(false);
		appointmentReference.setEnabled(false);
		appointmentReference.setText("Select an appointment on the left to view it, or choose \"New...\" to create a new one.");
	}
	
	/**
	 * Enables the edit and delete buttons
	 * Call this when there is an editable, deletable note loaded.
	 */
	public void setNoteEnabled() {
		cmdDelete.setEnabled(true);
		cmdEdit.setEnabled(true);
		noteTitle.setEnabled(true);
		noteText.setEnabled(true);
	}
	
	/**
	 * Enables the edit and delete buttons and all the text fields. 
	 * Call this when there is an editable, deletable contact loaded.
	 */
	public void setContactEnabled() {
		cmdDelete.setEnabled(true);
		cmdEdit.setEnabled(true);
		contactName.setEnabled(true);
		contactDescription.setEnabled(true);
		contactEmail.setEnabled(true);
		contactHomeNumber.setEnabled(true);
		contactWorkNumber.setEnabled(true);
		contactMobileNumber.setEnabled(true);
		contactAddress.setEnabled(true);
	}
	
	/**
	 * Enables the edit and delete buttons and all the text fields. 
	 * Call this when there is an editable, deletable appointment loaded.
	 */
	public void setAppointmentEnabled() {
		cmdEdit.setEnabled(true);
		cmdDelete.setEnabled(true);
		appointmentTitle.setEnabled(true);
		//appointmentDate.setEnabled(true);
		//appointmentTime.setEnabled(true);
		appointmentOnLabel.setEnabled(true);
		appointmentAtLabel.setEnabled(true);
		appointmentLocation.setEnabled(true);
		appointmentReference.setEnabled(true);
	}
	
	/**
	 * Sets text for note title
	 * @param t text to set
	 */
	public void setNoteTitle(String t) {
		noteTitle.setText(t);
	}
	
	/**
	 * Returns title of selected note
	 * @return note title
	 */
	public String getNoteTitle() {
		return noteTitle.getText();
	}
	
	/**
	 * Sets text for note text field
	 * @param t text to set
	 */
	public void setNoteText(String t) {
		noteText.setText(t);
	}
	
	/**
	 * Returns text of selected note. 
	 * @return note text
	 */
	public String getNoteText() {
		return noteText.getText();
	}
	
	/**
	 * Sets up Create new options for whichever tab is selected. 
	 * This usually means clearing text out of any fields and allowing them to be typed into. 
	 */
	public void createNew() {
		switch(getSelectedTab()) {
			case NOTES:
			createNewNote();
			break;
			case CONTACTS:
			createNewContact();
			break;
			case APPOINTMENTS:
			createNewAppointment();
			break;
		}
	}
	
	/**
	 * Sets up the UI ready for creating a new note. 
	 * This method:
	 *   >  Clears note pane on the right.
	 *   >  Turns on editing
	 */
	private void createNewNote() {
		setNoteEnabled();
		setEditingNote(true);
		titleLabel.setText("Create a new note...");
		noteTitle.setText("");
		noteText.setText("");
	}
	
	private void createNewContact() {
		setContactEnabled();
		setEditingContact(true);
		titleLabel.setText("Create a new contact...");
		contactName.setText("");
		contactDescription.setText("");
		contactEmail.setText("");
		contactHomeNumber.setText("");
		contactWorkNumber.setText("");
		contactMobileNumber.setText("");
		contactAddress.setText("");
	}
	
	private void createNewAppointment() {
		setAppointmentEnabled();
		setEditingAppointment(true);
		titleLabel.setText("Create a new appointment...");
		appointmentTitle.setText("");
		appointmentDate.setValue(new Date());
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		c.add(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MINUTE, 0);
		appointmentTime.setValue(c.getTime());
		appointmentLocation.setText("");
		appointmentReference.setText("");
	}
	
	public JPanel createItemList() {
		itemListModel = new DefaultListModel<String>();
		theList.setModel(itemListModel);
		JScrollPane listScrollPane = new JScrollPane(theList);
		JPanel itemList = new JPanel();
		itemList.setLayout(new BorderLayout());
		searchField = new JTextFieldWithPlaceholder("", "Search...");
		itemList.add(searchField, BorderLayout.PAGE_START);
		itemList.add(listScrollPane, BorderLayout.CENTER);
		//cmdCreate = new JButton("New...");
		cmdCreate.setFocusable(false);
		itemList.add(cmdCreate, BorderLayout.PAGE_END);
		return itemList;
	}
		
	private JFrame f;
	private JTabbedPane tabs;
	private JTextField searchField;
	private JList<String> theList;
	private DefaultListModel<String> itemListModel;
	private JButton cmdCreate;
	private JButton cmdEdit;
	private JButton cmdDelete;
	private JLabel titleLabel;
	
	// For notes
	private JTextField noteTitle;
	private JTextPane noteText;
	
	// For contacts
	private JTextField contactName;
	private JTextArea contactDescription;
	private JTextField contactEmail;
	private JTextField contactHomeNumber;
	private JTextField contactWorkNumber;
	private JTextField contactMobileNumber;
	private JTextArea contactAddress; 
	
	// For appointments
	private JSpinner appointmentDate;
	private JLabel appointmentDateLabel;
	private JSpinner appointmentTime;
	private JTextField appointmentTitle;
	private JLabel appointmentTitleLabel;
	private JTextField appointmentLocation;
	private JLabel appointmentLocationLabel;
	private JTextArea appointmentReference;
	private JLabel appointmentReferenceLabel;
	private JLabel appointmentOnLabel;
	private JLabel appointmentAtLabel;
	
	// Tap indexes
	public static final int APPOINTMENTS = 0;
	public static final int CONTACTS = 1;
	public static final int TODO = 2;
	public static final int NOTES = 3;
		
	// public static final int YES = JOptionPane.YES_OPTION;
	// public static final int NO = JOptionPane.NO_OPTION;
	// public static final int CANCEL = JOptionPane.CANCEL_OPTION;	
}