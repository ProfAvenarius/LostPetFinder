package com.dce;

public class Main {
    public static void main(String[] args) {
        PetDao petDao = new PetDao();
        Pet pet = new Pet(1,"Buddy", "Dog", "Beagle", "Brown", "Small", "Shy", "Lost", "Downtown");
        petDao.addPet(pet);
        System.out.println("Pet successfully added to Database.");
    }
}
