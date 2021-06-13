package tr.edu.yildiz.mobiletermproject;

public class EventsData {

    private String EventName, EventType, EventLocation, EventDate ;
    private Integer eventImage;

    public EventsData( String eventName, String eventType,String eventLocation,String eventDate, Integer eventImage) {
        EventName = eventName;
        EventType = eventType;
        EventLocation = eventLocation;
        EventDate = eventDate;
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public void setEventLocation(String eventLocation) {
        EventLocation = eventLocation;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public Integer getEventImage() {
        return eventImage;
    }

    public void setEventImage(Integer eventImage) {
        this.eventImage = eventImage;
    }
}
