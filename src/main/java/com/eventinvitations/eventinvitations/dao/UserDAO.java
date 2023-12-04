package com.eventinvitations.eventinvitations.dao;

import com.eventinvitations.eventinvitations.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO {
    private final MongoCollection<Document> users;

    public UserDAO(final MongoClient mongoClient, final String database) {
        this.users = mongoClient.getDatabase(database).getCollection("users");
    }

    public User findUser(String username) {
        Document document = users.find(eq("username", username)).first();
        if (document == null) {
            return null;
        }
        return user(document);
    }

    public void addUser(User user) {
        Document document = new Document();
        document.append("username", user.getUsername());
        document.append("password", user.getPassword());
        users.insertOne(document);
    }

    private User user(final Document document) {
        return new User(document.get("username").toString(), document.get("password").toString());
    }
}