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
  
  #### Basic Methods : the methods for initializing the list when constructing it or adding new dogs
  
    * ##### Empty Constructor : DogLib()
      Returns an empty list of dogs
    
    * ##### The xml Constructor : DogLib(File xml)
      Returns a list of dogs from the information in the xml File
      
      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
	     <dogs>
	      <dog name="Corrie">
      		<dateOfBirth>18-03-2012</dateOfBirth>
     			<weight>17</weight>
     			<breed>Shiba Inu</breed>
  	 		 </dog>
  	 		 <dog name="Havko">
     			<dateOfBirth>25-08-2014</dateOfBirth>
     			<weight>25</weight>
     			<breed>Siberian Husky</breed>
  	 		 </dog>
	     </dogs>
      ```
      
    * ##### void addDog(Dog d)
      This method is used for adding dogs to the list
    
      
  #### Methods: the methods for querying the list
  
    * #### double averageWeight(String breed)
      The method calculates the average weight for all the dogs of the same breed in the list
    
    * #### Map<String,Double\> averageWeightPerBreed()
      This method returns the average weight for all breeds in the form of a Map of String,Double
	     So you would end having the map like:
	     [Shiba Inu] -> [17]
	     [Siberian Husky] -> [25]
	  
    * #### List<Dog> dogsByCondition(String a, String b, String c)
      This method returns a list of the dogs that meet a certain condition
	     This makes use of the meetsCondition method in the Dog class
	     It takes three String params:
	       a: the dog attribute to check for the condition (breed, name, weight, date)
	       b: the comparison operator depending on the attribute can be
	  		     eq, neq for breed and name
	  		     \>, <, = for weight
	  		     isAfter, isBefore, isEqual for date
                 
    * #### Dog oldestDogAfterDate(LocalDate date)
      This method returns the oldest dog after a certain date of birth
      
## Basic Example
```java
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.doglib.Dog;
import com.doglib.DogLib;

public class DogliBenchmark {

 public static void main(String[] args) {
  DogLib dl = new DogLib("dogs.xml");
		
  // averageWeight example
  double d = dl.averageWeight("Shiba Inu");
  
  // averageWeightPerBreed example
  Map<String, Double> m = dl.averageWeightPerBreed();
		
  // dogsByCondition example		
  List<Dog> d = dl.dogsByCondition("breed", "eq", "Shiba Inu");
  
  // oldestDogAfterDate example
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  LocalDate date=LocalDate.parse("10-01-2010",formatter);		
  Dog d = dl.oldestDogAfterDate(date);
 }
}
```
  
  
  
   


