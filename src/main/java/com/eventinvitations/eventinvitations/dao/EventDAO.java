package com.eventinvitations.eventinvitations.dao;

import com.eventinvitations.eventinvitations.model.Event;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class EventDAO {
    private final MongoCollection<Document> events;

    public EventDAO(final MongoClient mongoClient, final String database) {
        this.events = mongoClient.getDatabase(database).getCollection("events");
    }

    public void createEvent(Event event) {
        Document document = new Document();
        document.append("username", event.getUsername())
                .append("eventName", event.getEventName())
                .append("eventDate", event.getEventDate())
                .append("eventTime", event.getEventTime())
                .append("eventLocation", event.getEventLocation())
                .append("eventDescription", event.getEventDescription())
                .append("attendance", event.getAttendance()); // Storing initial attendance

        events.insertOne(document);
    }

    public void incrementAttendance(String eventName) {
        // Increment the attendance count for the event
        events.updateOne(eq("eventName", eventName), inc("attendance", 1));
        System.out.println("New Attendance: " + getAttendance(eventName));
    }

    public List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        for (Document doc : events.find()) {
            int attendance = doc.getInteger("attendance", 0); // Get the attendance, default to 0
            Event e = new Event(
                    doc.getString("username"),
                    doc.getString("eventName"),
                    doc.getString("eventDate"),
                    doc.getString("eventTime"),
                    doc.getString("eventLocation"),
                    doc.getString("eventDescription"),
                    attendance);

            allEvents.add(e);
        }
        return allEvents;
    }

    public int getAttendance(String eventName) {
        Document doc = events.find(eq("eventName", eventName)).first();
        assert doc != null;
        return doc.getInteger("attendance", 0);
    }

    public void deleteEvent(String eventName) {
        events.deleteOne(eq("eventName", eventName));
    }
}
