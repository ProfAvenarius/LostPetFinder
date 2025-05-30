package com.dce;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetDaoTest {

    private MongoCollection<Document> mockCollection;
    private PetDao petDao;

    @BeforeEach
    public void setup() {
        mockCollection = Mockito.mock(MongoCollection.class);
        petDao = new PetDao(mockCollection);
    }

    @Test
    public void testAddPetCallsInsertOne() {
        Pet pet = new Pet(1111, "TestLostDog", "TestDog", "TestGoodBoy", "TestColour", "TestSize", "TestDesc", "Lost", "TestLocation");
        petDao.addPet(pet);

        verify(mockCollection, times(1)).insertOne(any(Document.class));

    }

    @Test
    public void testGetPetByIdReturnsPet() {
        Document fakePet = new Document("petId", 111)
                .append("name", "MockLostDog")
                .append("type", "MockDog")
                .append("breed", "MockBreed")
                .append("colour", "MockColour")
                .append("size", "MockSize")
                .append("notes", "MockNotes")
                .append("status", "Lost")
                .append("lastSeen", "MockLocation");

        FindIterable<Document> mockFindIterable = mock(FindIterable.class);
        when(mockCollection.find(any(Document.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(fakePet);

        Pet result = petDao.getPetById(111);

        assertNotNull(result);
        assertEquals("MockLostDog", result.getName());
        assertEquals("Lost",result.getStatus());
    }
}
