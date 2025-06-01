package com.dce;

import java.util.List;
import java.util.Scanner;

public class PetDirectory {
    private final PetDao petDao = new PetDao();
    private final Scanner scanner = new Scanner(System.in);

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

    private void addPet() {

    }

    private void updatePetField() {

    }

    private void deletePet() {

    }

    private void viewAllPets() {

}

