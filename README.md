## DogLib Project
The DogLib project is a simple java library to keep track of information about dogs, 
and provides some methods to query the list of dogs.

## Basic Considerations - Using the library in a project
* ### Plain JAR
  * Download the current jar of the library `maven-doglib-0.0.1-SNAPSHOT.jar`
  * Add the jar to the classpath
  
* ### Maven with jar dependency
  * Download the current jar of the library `maven-doglib-0.0.1-SNAPSHOT.jar`
  * Install the jar file in the maven local repository
  * Add the corresponding dependency
  ```xml
  <dependency>
    <groupId>com.doglib</groupId>
    <artifactId>maven-doglib</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </dependency>
  ```
  
* ### Import the library in the new class where it is going to be used
  ```java
  import com.doglib.DogLib;
  ```

* ### Have Fun with DogLib !!!!

## Library Method Description
* ### The Dog Class
  #### This class represents a dog with four attributes
    * Name (String)
    * Date of Birth (LocalDate)
    * Weight (double)
    * Breed (String)
    
* ### The DogLib Class
  This class makes use of the Dog class and stores the dog information in a list of dogs (List<Dog>).
  The list of dogs can be initialized as an empty list or from an xml file containing the dog info.
  In addtion the library provides some methods to run some queries on the list of dogs.
  Basic Methods : the methods for initializing the list when constructin it or adding new dogs
    * #### Empty Constructor : DogLib()
      Returns an empty list of dogs
    
    * #### XML Constructor : DogLib(File xml)
      Returns a list of dogs from the information in the xml File
      ```xml
      
  Methods:
    * double averageWeight(String breed)
  
  
  
   


