package com.eventinvitations.eventinvitations.dao;

import com.eventinvitations.eventinvitations.model.Event;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

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
                .append("confirmations", event.getConfirmations());

        events.insertOne(document);
    }

    public void confirmEvent(String eventName, String attendee) {
        Document event = events.find(eq("eventName", eventName)).first();
        if (event != null) {
            events.updateOne(eq("eventName", eventName), push("confirmations", attendee));
        }
    }

    public List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        for (Document doc : events.find()) {
            Event e = new Event(
                    doc.getString("username"),
                    doc.getString("eventName"),
                    doc.getString("eventDate"),
                    doc.getString("eventTime"),
                    doc.getString("eventLocation"),
                    doc.getString("eventDescription"));

            // Check if confirmations list is not null
            List<String> confirmations = (List<String>) doc.get("confirmations");
            if (confirmations != null) {
                for (String confirmation : confirmations) {
                    e.addConfirmation(confirmation);
                }
            }
            allEvents.add(e);
        }
        return allEvents;
    }
}