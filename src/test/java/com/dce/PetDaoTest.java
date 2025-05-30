package com.dce;


import com.mongodb.client.*;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; //Discovered the beauty of static import to reduce repetition
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

    @Test
    public void testGetAllPetsReturnsList() {
        Document fakePet1 = new Document("petId", 111)
                .append("name", "MockDog1")
                .append("type", "Dog")
                .append("breed", "MockBreed1")
                .append("colour", "Brown")
                .append("size", "Small")
                .append("notes", "MockNotes1")
                .append("status", "Lost")
                .append("lastSeen", "MockLocation1");

        Document fakePet2 = new Document("petId", 222)
                .append("name", "MockCat1")
                .append("type", "Cat")
                .append("breed", "MockBreed2")
                .append("colour", "Black")
                .append("size", "Medium")
                .append("notes", "MockNotes2")
                .append("status", "Found")
                .append("lastSeen", "MockLocation2");
        //Cursor is mocked assemble the required M
        MongoCursor<Document> mockCursor = mock(MongoCursor.class);
        when(mockCursor.hasNext()).thenReturn(true, true, false);
        when(mockCursor.next()).thenReturn(fakePet1, fakePet2);

        FindIterable<Document> mockFindIterable = mock(FindIterable.class);
        when(mockFindIterable.iterator()).thenReturn(mockCursor);
        when(mockCollection.find()).thenReturn(mockFindIterable);

        List<Pet> result = petDao.getAllPets();

        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals("MockDog1", result.get(0).getName());
    }

    @Test
    public void testUpdatePetField() {
        UpdateResult mockResult = mock(UpdateResult.class);
        when(mockResult.getMatchedCount()).thenReturn(1L);
        when(mockCollection.updateOne(any(Document.class), any(Document.class))).thenReturn(mockResult);

        String result = petDao.updatePetField(1234, "status", "Found");

        assertEquals("Update successful.", result);
        verify(mockCollection, times(1)).updateOne(any(Document.class), any(Document.class));
    }

    @Test
    public void testDeletePetByIdNotFound() {
        DeleteResult mockResult = mock(DeleteResult.class);
        when(mockResult.getDeletedCount()).thenReturn(0L);
        when(mockCollection.deleteOne(any(Document.class))).thenReturn(mockResult);

        String result = petDao.deletePetById(9999);

        assertEquals("Pet not found.", result);
    }




}
