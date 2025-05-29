package com.dce;


import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

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

}
