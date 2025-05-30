package com.dce;

import com.mongodb.client.*;

import org.bson.Document; //Using BSON to align with MongoDB

import java.util.ArrayList;
import java.util.List;


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

    public Pet getPetById(int id) {
        Document query = new Document("petId", id);
        Document result = petCollection.find(query).first();
        if (result == null) {
            return null;
        }else {
            return new Pet(
                    result.getInteger("petId"),
                    result.getString("name"),
                    result.getString("type"),
                    result.getString("breed"),
                    result.getString("colour"),
                    result.getString("size"),
                    result.getString("notes"),
                    result.getString("status"),
                    result.getString("lastSeen")
            );
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        for (Document doc : petCollection.find()) {
            Pet pet = new Pet(
                    doc.getInteger("petId"),
                    doc.getString("name"),
                    doc.getString("type"),
                    doc.getString("breed"),
                    doc.getString("colour"),
                    doc.getString("size"),
                    doc.getString("notes"),
                    doc.getString("status"),
                    doc.getString("lastSeen")
            );
            pets.add(pet);
        }
        return pets;
    }
    



}
