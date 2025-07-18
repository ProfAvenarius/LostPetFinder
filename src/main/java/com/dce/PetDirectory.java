//Author: DC Elliott
//Description: Contains all the logic for the cli interface
//Date: June 1/2025

package com.dce;

import java.util.List;
import java.util.Scanner;

public class PetDirectory {
    private final PetDao petDao;
    private final Scanner scanner;


    public PetDirectory() {
        this.petDao = new PetDao();
        this.scanner = new Scanner(System.in);
    }

    // Test constructor substituting mock data
    public PetDirectory(PetDao petDao, Scanner scanner) {
        this.petDao = petDao;
        this.scanner = scanner;
    }

    public void start() {

        while (true) {
            System.out.println("\n=== Lost Pet Directory ===");
            System.out.println("1. Add Pet");
            System.out.println("2. Update Pet Field");
            System.out.println("3. Delete Pet");
            System.out.println("4. View All Pets");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> addPet();
                case "2" -> updatePetField();
                case "3" -> deletePet();
                case "4" -> viewAllPets();
                case "5" -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void addPet() {
        try {
            System.out.print("Pet ID: ");
            int petId = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Type: ");
            String type = scanner.nextLine();
            System.out.print("Breed: ");
            String breed = scanner.nextLine();
            System.out.print("Colour: ");
            String colour = scanner.nextLine();
            System.out.print("Size: ");
            String size = scanner.nextLine();
            System.out.print("Notes: ");
            String notes = scanner.nextLine();
            System.out.print("Status (Lost/Found): ");
            String status = scanner.nextLine();
            System.out.print("Last Seen Location: ");
            String lastSeen = scanner.nextLine();

            Pet pet = new Pet(petId, name, type, breed, colour, size, notes, status, lastSeen);
            petDao.addPet(pet);
            System.out.println("Pet added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding pet: " + e.getMessage());
        }
    }

    public void updatePetField() {
        try {
            System.out.print("Pet ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Field to update (e.g., status, notes): ");
            String field = scanner.nextLine();
            System.out.print("New value: ");
            String value = scanner.nextLine();

            String result = petDao.updatePetField(id, field, value);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error updating pet: " + e.getMessage());
        }
    }

    public void deletePet() {
        try {
            System.out.print("Pet ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            String result = petDao.deletePetById(id);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error deleting pet: " + e.getMessage());
        }
    }

    public void viewAllPets() {
        List<Pet> pets = petDao.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("No pets found in the database.");
        } else {
            pets.forEach(System.out::println);
        }
    }
}

