package com.doglib;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DogLibTest {

	@Test
	public void initDogLibTest() {
		DogLib dl = new DogLib("dogstest.xml");
		
		assertEquals(4, dl.dogs.size());
		assertEquals("Havko",dl.dogs.get(3).getName());
		assertEquals("Shiba Inu",dl.dogs.get(2).getBreed());
		assertEquals("Bernese Mountain Dog",dl.dogs.get(1).getBreed());
	}
	
	@Test
	public void addDogTest() {
		DogLib dl = new DogLib();
		Dog d = new Dog("Rocky","10-01-2010", 15, "Shiba Inu");
		dl.addDog(d);
		
		assertEquals(1, dl.dogs.size());
		assertEquals("Rocky",dl.dogs.get(0).getName());		
	}
	
	@Test
	public void averageWeightTest() {
		DogLib dl = new DogLib("dogstest.xml");
		
		assertEquals(true, dl.averageWeight("Shiba Inu")==17);
	}
	
	@Test
	public void averageWeightPerBreedTest() {
		DogLib dl = new DogLib("dogstest.xml");
		Map<String,Double> breedStats = dl.averageWeightPerBreed();
		
		assertEquals(true, breedStats.get("Shiba Inu")==17 && breedStats.get("Siberian Husky")==25);
	}
		
	@Test
	public void dogsByConditionPredicateTest() {
		DogLib dl = new DogLib("dogstest.xml");
		
		List<Dog> doglist;
		
		// Name
		doglist = dl.dogsByConditionPredicate(Dog.isEqName("Havko"));		
		assertEquals(1,doglist.size());
		assertEquals("Siberian Husky",doglist.get(0).getBreed());
		
		doglist.clear();
		
		doglist = dl.dogsByConditionPredicate(Dog.isNeqName("Havko"));		
		assertEquals(3,doglist.size());
		assertEquals("Rhodesian Ridgeback",doglist.get(0).getBreed());
		assertEquals("Baldi",doglist.get(1).getName());
		assertEquals(17,doglist.get(2).getWeight(),0.00001);		
		
		doglist.clear();
		
		//Breed
		doglist = dl.dogsByConditionPredicate(Dog.isEqBreed("Bernese Mountain Dog"));		
		assertEquals(1,doglist.size());
		assertEquals("Baldi",doglist.get(0).getName());
		
		doglist.clear();
		
		doglist = dl.dogsByConditionPredicate(Dog.isNeqBreed("Rhodesian Ridgeback"));		
		assertEquals(3,doglist.size());
		assertEquals("Baldi",doglist.get(0).getName());
		assertEquals("Shiba Inu",doglist.get(1).getBreed());
		assertEquals(25,doglist.get(2).getWeight(),0.00001);
		
		
		doglist.clear();
		
		//Weight
		doglist = dl.dogsByConditionPredicate(Dog.isGreaterWeight(10));		
		assertEquals(4,doglist.size());
		assertTrue(doglist.get(0).getWeight()>10);
		assertTrue(doglist.get(1).getWeight()>10);
		assertTrue(doglist.get(2).getWeight()>10);
		assertTrue(doglist.get(3).getWeight()>10);
		
		
		doglist.clear();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date=LocalDate.parse("10-01-2012",formatter);	
		doglist = dl.dogsByConditionPredicate(Dog.isAfterDate(date));		
		assertEquals(2,doglist.size());
		assertTrue(doglist.get(0).getDate().isAfter(date));
		assertTrue(doglist.get(1).getDate().isAfter(date));
		assertEquals("Shiba Inu",doglist.get(0).getBreed());
		assertEquals("Siberian Husky",doglist.get(1).getBreed());
		
		
	}
	
	@Test
	public void oldestDogAfterDateTest() {
		DogLib dl = new DogLib("dogstest.xml");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date=LocalDate.parse("10-01-2012",formatter);		
		Dog d = dl.oldestDogAfterDate(date);
		
		assertEquals(true, d.getBreed().equals("Shiba Inu"));
	}

}
