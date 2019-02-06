/**
 * Represents a single Note object in the Personal Information Manager
 */
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Note implements Serializable
{
		
	private String title;
	private String text;
	//private Date dateCreated;
	private ArrayList<String> tags;
	
	/**
	 * Creates a Note with empty title and text
	 */
	public Note() 
	{
		this.title = "";
		this.text = "";
		// initialise dateCreated
		tags = new ArrayList<String>();
	}
	
	/**
	 * Creates a Note with specified title and text
	 * @param title; title of note
	 * @param text: text of note
	 */
	public Note(String title, String text)
	{
		this.title = title;
		this.text = text;
		// initialise dateCreated
		tags = new ArrayList<String>();
	}
	
	/**
	 * Sets note's title
	 * @param title title of note
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * Returns the note's title
	 * @return title of note
	 */
	public String getTitle() 
	{
		return this.title;
	}
	
	/**
	 * Sets note's text
	 * Multiline text is allowed, but will have to be dealt with in the user interface.
	 * @param text text of note
	 */
	public void setText(String text) 
	{
		this.text = text;
	}
	
	/**
	 * Returns the note's text
	 * Multiline text is allowed, but will have to be dealt with in the user interface.
	 * @return text of note
	 */
	public String getText() 
	{
		return this.text;
	}
	
	/**
	 * Sets note's tags
	 * @param tags tags to add to the note
	 */
	public void setTags(String[] tags) 
	{
		this.tags = new ArrayList<String>(Arrays.asList(tags));
	}
	
	/**
	 * Returns an array of the note's tags
	 * @return note's tags
	 */
	public String[] getTags() 
	{
		return tags.toArray(new String[tags.size()]);
	}
	
	/**
	 * Adds tag to note
	 * @param tag: 
	 */
	public void addTag(String tag) 
	{
		tags.add(tag);
	}
	
	/**
	 * Remove tag with specified name (not case sensitive)
	 * @param tagName tag to delete
	 */
	public void removeTag(String tagName) 
	{
		// TODO if anyone has a more efficient/nicer way to do this, please add it :-)
		for (int i = 0; i < tags.size(); i++) 
		{
			if (tags.get(i).equalsIgnoreCase(tagName)) 
			{	// Tag to remove found - remove it and exit for loop.
				tags.remove(i);
				break;
			}
		}
	}
	public Date addDate() {
		Date creationDate = new Date();
		return creationDate;
	}
	
}
