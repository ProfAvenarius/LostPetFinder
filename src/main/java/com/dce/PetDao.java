package com.dce;

import com.mongodb.client.*;
//import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.*; //Had to add sub-package separately.

import org.bson.Document; //Using BSON to align with MongoDB.

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

    public String updatePetField(int petId, String fieldName, String newValue) {
        List<String> validFields = List.of("name", "type", "breed", "colour", "size", "notes", "status", "lastSeen");

        if (!validFields.contains(fieldName)) {
            return "Invalid field name.";
        }
        Document petToUpdate = new Document("petId", petId);
        Document fieldToUpdate = new Document("$set", new Document(fieldName, newValue));
        UpdateResult result = petCollection.updateOne(petToUpdate, fieldToUpdate);

        if (result.getMatchedCount() == 0) {
            return "Pet not found.";
        }

        return "Update successful.";
    }

    public String deletePetById(int id) {
        Document query = new Document ("petId", id);
        DeleteResult result = petCollection.deleteOne(query);

        if (result.getDeletedCount() == 0) {
            return "Pet not found.";
        }

        return "Pet deleted successfully.";
    }



}





