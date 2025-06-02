package com.dce;

import org.junit.jupiter.api.Test;

import java.io.*; //Had to use InputStream and ByteArrayInputStream to simulate Scanner, could not mock a final class
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetDirectoryTest {

    private PetDao mockDao;
    private Scanner mockScanner;
    private PetDirectory directory;

    @Test
    public void testUpdatePetField_Success() {
        // Simulate user input as Scanner being a final class cound not be fully mocked.
        String simulatedInput = String.join("\n", "1234", "status", "Found");
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        PetDao mockDao = mock(PetDao.class);
        when(mockDao.updatePetField(1234, "status", "Found")).thenReturn("Update successful.");

        PetDirectory directory = new PetDirectory(mockDao, scanner);
        directory.updatePetField();

        verify(mockDao).updatePetField(1234, "status", "Found");
    }

    @Test
    public void testUpdatePetField_InvalidInput() {
        String invalidInput = "notAnInt\nstatus\nFound\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(invalidInput.getBytes()));

        PetDao mockDao = mock(PetDao.class);
        PetDirectory directory = new PetDirectory(mockDao, scanner);

        assertDoesNotThrow(directory::updatePetField);
        verify(mockDao, never()).updatePetField(anyInt(), anyString(), anyString());
    }

    @Test
    public void testDeletePet() {
        String simulatedInput = "456\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        PetDao mockDao = mock(PetDao.class);
        when(mockDao.deletePetById(456)).thenReturn("Pet deleted successfully.");

        PetDirectory directory = new PetDirectory(mockDao, scanner);
        directory.deletePet();

        verify(mockDao).deletePetById(456);
    }

    @Test
    public void testAddPet() {
        String simulatedInput = String.join("\n",
                "101",         // petId
                "MockName",       // name
                "MockType",         // type
                "MockBreed",      // breed
                "MockColour",       // colour
                "MockSize",       // size
                "MockNotes",    // notes
                "Lost",        // status
                "MockLocation"     // lastSeen
        );
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        PetDao mockDao = mock(PetDao.class);

        // Optional: validate values using argument captor
        doAnswer(invocation -> {
            Pet pet = invocation.getArgument(0);
            assertEquals("MockName", pet.getName());
            assertEquals("Lost", pet.getStatus());
            return null;
        }).when(mockDao).addPet(any(Pet.class));

        PetDirectory directory = new PetDirectory(mockDao, scanner);
        directory.addPet();

        verify(mockDao).addPet(any(Pet.class));
    }
}