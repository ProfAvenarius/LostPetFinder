//Description: Connects to MongoDB, based on previous work with Postgres
//Author: DC Elliott
//Date: May 29/2025


package com.dce;

import com.mongodb.client.*;


public class DatabaseConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "LostPetDB";
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            MongoClient client = MongoClients.create(CONNECTION_STRING);
            database = client.getDatabase(DATABASE_NAME);
        }
        return database;
    }
}
