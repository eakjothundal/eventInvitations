package com.eventinvitations.eventinvitations.model;

public class Event {
    private String username;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private Integer attendance;

    public Event(String username, String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription, int attendance) {
        this.username = username;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.attendance = attendance;
    }

    // Public getter methods

    public String getUsername() {
        return username;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getAttendance() {
        return attendance;
    }

    public void addAttendance(String username) {
        if (!this.username.equals(username)) {
            attendance += 1;
        }
    }
}