/**
  * Custom exception for when contacts are not found.
  */
public class ContactNotFound extends Exception {
  public ContactNotFound() { 
    super(); 
  }
  public ContactNotFound(String message) {
    super(message); 
  }
}
