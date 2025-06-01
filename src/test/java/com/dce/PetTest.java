package com.dce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    private Pet pet;

    @BeforeEach
    void setup(){
        pet = new Pet(1, "Buddy", "Dog", "Beagle", "Brown", "Small", "Shy", "Lost", "Downtown");
    }


    @Test
    public void testPetGettersAndSetters() {

        assertEquals(1, pet.getPetId());
        assertEquals("Buddy", pet.getName());
        assertEquals("Dog", pet.getType());
        assertEquals("Beagle", pet.getBreed());
        assertEquals("Brown", pet.getColour());
        assertEquals("Small", pet.getSize());
        assertEquals("Shy", pet.getNotes());
        assertEquals("Lost", pet.getStatus());
        assertEquals("Downtown", pet.getLastSeen());
    }

    @Test
    public void testPetStatusUpdate() {

        pet.setStatus("Found");
        assertEquals("Found", pet.getStatus());
        assertTrue(pet.getStatus().equalsIgnoreCase("Found"));
    }

    @Test
    public void testToStringIncludesNameAndType() {

        String output = pet.toString();
        assertTrue(output.contains("Buddy"));
        assertTrue(output.contains("Dog"));
    }
}
