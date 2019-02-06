
public class TitleOutOfLimitsException extends Exception{
	
	/*
	 * Custom exception used to indicate that a title given is not within the preset character limit.
	 * Should be caught in the ToDo database when attempting to create/edit a ToDo object. 
	 */
	public TitleOutOfLimitsException(String message){
		super(message);
	}
}
