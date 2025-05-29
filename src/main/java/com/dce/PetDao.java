package com.dce;

import com.mongodb.client.*;

import org.bson.Document; //Using BSON to align with MongoDB


public class PetDao {
    private final MongoCollection<Document> petCollection;

    public PetDao() {
        MongoDatabase db = DatabaseConnection.getDatabase();
        petCollection = db.getCollection("pets");
    }

    //Added 2nd constructor to allow mocking for testing
    public PetDao(MongoCollection<Document> petCollection) {
        this.petCollection = petCollection;
    }

    public void addPet(Pet pet) {
        Document doc = new Document("petId", pet.getPetId())
                .append("name", pet.getName())
                .append("type", pet.getType())
                .append("breed", pet.getBreed())
                .append("colour", pet.getColour())
                .append("size", pet.getSize())
                .append("notes", pet.getNotes())
                .append("status", pet.getStatus())
                .append("lastSeen", pet.getLastSeen());
        petCollection.insertOne(doc);
    }
}
