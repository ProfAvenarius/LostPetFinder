//Author: DC Elliott
//Description: Makes a Pet object, basis POGO.
//Date: May 28/2025

package com.dce;

public class Pet {
    private int petId;
    private String name;
    private String type;
    private String breed;
    private String colour;
    private String size;
    private String notes;
    private String status;
    private String lastSeen;

    public Pet(int petId, String name, String type, String breed, String colour, String size, String notes, String status, String lastSeen) {
        this.petId = petId;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.colour = colour;
        this.size = size;
        this.notes = notes;
        this.status = status;
        this.lastSeen = lastSeen;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public String toString() {
        return String.format(
                "Pet ID: %d | Name: %s | Type: %s | Breed: %s | Colour: %s | Size: %s | Notes: %s | Status: %s | Last Seen: %s",
                petId, name, type, breed, colour, size, notes, status, lastSeen
        );
    }
}
