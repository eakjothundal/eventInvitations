package com.eventinvitations.eventinvitations.dao;

import com.eventinvitations.eventinvitations.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

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
        // Hash the password before storing it
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        document.append("password", hashedPassword);
        users.insertOne(document);
    }

    // Authenticate user with username and password
    public boolean authenticateUser(String username, String password) {
        User user = findUser(username);
        if (user == null) {
            return false;
        }
        // Check if the hashed password matches the one stored in the database
        return BCrypt.checkpw(password, user.getPassword());
    }

    private User user(final Document document) {
        return new User(document.get("username").toString(), document.get("password").toString());
    }
}
