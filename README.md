<H1>QAP1 - SDAT/DEVOPS
PROJECT: LOSTPETFINDER
AUTHOR: DC ELLIOTT SD-12
DATE: 06/01/2025
<H1/>
This project is the first version of a lost pet database.
The program allows for records of lost pets to be stored and accessed by users.

This program runs in a Terminal from Command line prompts.
All data is persistent and saved to a MongoDB database.

See DatabaseConnection.java to set any MongoDB parameters.
Current parameters are:
CONNECTION_STRING = "mongodb://localhost:27017"; 
DATABASE_NAME = "LostPetDB";

<H2> Examples of  Clean Code practice </H2>
See screenshots in directory 'Screenshots'.

Example 1. Began with methods affecting output directly in Main, moved all logic in to 
PetDirectory and left Main only for calling this class. Short and Sweet.

Example2. Only after encountering issues with mocking a final class (Scanner)
in PetDirectoryTest did the mocking have to be simulated in each test. On review
it was determined the @BeforeEach had become redundant and was removed.

Example3. All naming - from classes to methods - is symmetrical in that it mirrors main and tests. All names
were edited for consistency across classes.

All dependencies can from class examples involving mocking except slf4j which came from reading documentation about a warning.

The largest issues came from integrating MongoDB into a Java Maven project with mocking.


<H2> Functionalities to add: </H2>
1.Filter by Pet Type, Color, and Size
-Narrow search results quickly (e.g., "Small brown dog").

2.“Recently Seen” Indicator 
-Show a time/date stamp for the last report update.

3.Status Toggle: Lost / Found
-Let users mark pets as "found" — and maybe even see how long it took!

4.Community Notes / Comments
-Add optional notes: "Seen near 5th Ave!" — builds collaboration.

5.Pet Photo Support (for future GUI or file linking)
-With AI auto-matching with photos capability?

6.Urgency Level Tag
-Highlight reports where the pet is sick or is very young/old.

7.Sort by Time Reported
-Quickly view the newest or oldest cases still unresolved.

8.“Possible Matches” Alert
If someone adds a pet that closely matches an existing one, show a hint.

9.Area Activity Summary
List the number of pets currently reported lost in each neighborhood, integrated with maps.

10.Resolved Case Tracker
Keep stats, eg. “15 pets found so far”.
