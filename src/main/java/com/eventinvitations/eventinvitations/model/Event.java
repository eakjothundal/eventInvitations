package com.eventinvitations.eventinvitations.model;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String username;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private List<String> confirmations;

    public Event(String username, String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription) {
        this.username = username;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.confirmations = new ArrayList<>();
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

    public List<String> getConfirmations() {
        return confirmations;
    }

    public void addConfirmation(String username) {
        if (!this.username.equals(username) && !confirmations.contains(username)) {
            confirmations.add(username);
        }
    }
}