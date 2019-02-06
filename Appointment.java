/**
 * Created by RiccardoBroggi on 18/02/2016.
 */

import java.util.Date;

public class Appointment implements java.io.Serializable {
    private Date date;
    private Date time;
    private String location;
    private String title;
    private String reference;

    public Appointment (String titleGiven, Date dateGiven, Date timeGiven, String locationGiven, String referenceGiven){
        date = dateGiven;
        time = timeGiven;
        location = locationGiven;
        title = titleGiven;
        reference = referenceGiven;
    }

    public void setDate(Date newDate){
        date = newDate;
    }

    public void setTime(Date newTime){
        time = newTime;
    }

    public void setLocation(String newLocation){
        location = newLocation;
    }

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public void setReference(String newReference){
        reference = newReference;
    }

    public Date getDate(){
        return date;
    }

    public Date getTime(){
        return time;
    }

    public String getLocation(){
        return location;
    }

    public String getTitle(){
        return title;
    }

    public String getReference(){
        return reference;
    }
}
