import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by RiccardoBroggi & Emma James on 18/02/2016.
 */

public class AppointmentOrganiser implements IDatabase<Appointment> {

    protected ArrayList<Appointment> collection;

    public AppointmentOrganiser() {
        collection = new ArrayList<Appointment>();
    }

    public Appointment getAppointment(String titleOfAppointment) {
        int indexOfAppointment = findAppointmentByTitle(titleOfAppointment);
        return collection.get(indexOfAppointment);
    }

    // public void addAppointment(String titleGiven, String dateGiven, String timeGiven, String locationGiven, String referenceGiven) {
    //     collection.add(new Appointment(titleGiven, dateGiven, timeGiven, locationGiven, referenceGiven));
    // }

    public void removeAppointment(String titleOfAppointment) throws Exception {
        int indexOfAppointment = findAppointmentByTitle(titleOfAppointment);
        if (indexOfAppointment == -1) {
            throw new Exception("No Appointment Exists");
        }
        collection.remove(indexOfAppointment);
    }

    // public void changeAppointment(String titleOfAppointment, String[] whatToChange, String[] whatToChangeTo) throws Exception {
    //     if (whatToChange.length != whatToChangeTo.length) {
    //         throw new Exception("Invalid Argument for Changing Appointment");
    //     }
    //
    //     int indexOfAppointment = findAppointmentByTitle(titleOfAppointment);
    //     if (indexOfAppointment == -1) {
    //         System.err.println("No Appointment Exists");
    //     } else {
    //         for (int i = 0; i < whatToChange.length; i++) {
    //             switch (whatToChange[i]) {
    //                 case "title":
    //                     collection.get(indexOfAppointment).setTitle(whatToChangeTo[i]);
    //                     break;
    //                 case "date":
    //                     collection.get(indexOfAppointment).setDate(whatToChangeTo[i]);
    //                     break;
    //                 case "time":
    //                     collection.get(indexOfAppointment).setTime(whatToChangeTo[i]);
    //                     break;
    //                 case "location":
    //                     collection.get(indexOfAppointment).setLocation(whatToChangeTo[i]);
    //                     break;
    //                 case "reference":
    //                     collection.get(indexOfAppointment).setReference(whatToChangeTo[i]);
    //                     break;
    //                 default:
    //                     throw new Exception("Trying to change something that doesn't exist");
    //             }
    //         }
    //
    //
    //     }
    // }


    public String printAllAppointments() {
        String output = "";
        for (int i = 0; i < collection.size(); i++) {
            output += collection.get(i).getTitle();
            output += ", ";
            output += collection.get(i).getDate();
            output += ", ";
            output += collection.get(i).getTime();
            output += ", ";
            output += collection.get(i).getLocation();
            output += ", ";
            output += collection.get(i).getReference();
            if ((i + 1) != collection.size()) {
                output += "\n";
            }
        }
        return output;
    }

    public int findAppointmentByTitle(String titleToFind) {
        int indexToReturn = -1;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getTitle().equals(titleToFind)) {
                indexToReturn = i;
                break;
            }
        }
        return indexToReturn;
    }
	
	// Implementing IDatabase to make it easier to add appointments to the UI. 
	public void addItem(Appointment a) {
		collection.add(a);
	}
	public Appointment getItemAtIndex(int index) {
		return collection.get(index);
	}
	public Appointment removeItemAtIndex(int index) {
		Appointment removed = collection.get(index);
		collection.remove(index);
		return removed;
	}
	public void updateItemAtIndex(int index, Appointment newAppointment) {
		collection.set(index, newAppointment);
	}
	public int size() {
		return collection.size();
	}
	public Appointment searchByWord(String word) {
		// TODO use one of the getAppointmentBy... methods to search. 
		return null;
	}

}
