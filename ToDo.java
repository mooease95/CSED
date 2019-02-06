
public class ToDo implements java.io.Serializable{
	private String title;
	private BetterDate deadline;
	private Priority priority;
	private boolean complete = false;
	private final static int TITLE_CHAR_LIMIT = 25; 
	private TitleOutOfLimitsException TitleOutOfLimitsException =
				new TitleOutOfLimitsException("String given is out of the " + TITLE_CHAR_LIMIT + " character limit.");
	
	/**
	 * Enum that defines the Priority type as either HIGH, MEDIUM or LOW.
	 */
	public enum Priority{
		HIGH, MEDIUM, LOW
	}
	
	/**
	 * Combinations of acceptable fields for constructors.
	 * 
	 * Title only constructor.
	 * 
	 * @throws TitleOutOfLimitsException - The string given is not valid for the set character limit.
	 */
	public ToDo (String title) throws TitleOutOfLimitsException{
		if(validateTitle(title)){
			this.title = title;
		}else{
			throw TitleOutOfLimitsException;
		}
		
	}
	
	/**
	 * Title and deadline constructor.
	 * 
	 * @throws TitleOutOfLimitsException - The string given is not valid for the set character limit.
	 */
	public ToDo (String title, BetterDate deadline) throws TitleOutOfLimitsException{
		if(validateTitle(title)){
			this.title = title;
		}else{
			throw TitleOutOfLimitsException;
		}
		this.deadline = deadline;
	}
	
	/**
	 * Title and priority constructor.
	 * 
	 * @throws TitleOutOfLimitsException - The string given is not valid for the set character limit.
	 */
	public ToDo (String title, Priority priority) throws TitleOutOfLimitsException{
		if(validateTitle(title)){
			this.title = title;
		}else{
			throw TitleOutOfLimitsException;
		}
		this.priority = priority;
	}
	
	/**
	 * Title, deadline and priority constructor
	 * 
	 * @throws TitleOutOfLimitsException - The string given is not valid for the set character limit.
	 */
	public ToDo (String title, BetterDate deadline, Priority priority) throws TitleOutOfLimitsException{
		if(validateTitle(title)){
			this.title = title;
		}else{
			throw TitleOutOfLimitsException;
		}
		this.deadline = deadline;
		this.priority = priority;
	}
	
	/***
	 * Takes a given string and compares it to the preset character limit.
	 * 
	 * @param newTitle - the string to be validated
	 * 
	 * @return true - if the string is within the character limit.
	 * @return false - if the string is not within the character limit.
	 */
	private boolean validateTitle(String newTitle){
		if(newTitle.length() <= TITLE_CHAR_LIMIT && newTitle.length() >0){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * Accessor for ToDo fields.
	 */
	public String getTitle(){return this.title;}
	public BetterDate getDeadline(){return this.deadline;}
	public Priority getPriority(){return this.priority;}
	public boolean isComplete(){return this.complete;}
	
	/**
	 * Mutators for ToDo fields.
	 * 
	 * @throws TitleOutOfLimitsException - The string given is not valid for the set character limit.
	 */
	public void setTitle(String newTitle) throws TitleOutOfLimitsException{
		if(validateTitle(newTitle)){
			this.title = newTitle;
		}else{
			throw TitleOutOfLimitsException;
		}
	}
	public void setDeadline(BetterDate newDeadline){this.deadline = newDeadline;}
	public void setPriority(Priority newPriority){this.priority = newPriority;}
	public void setComplete(boolean newComplete){this.complete = newComplete;}
}
