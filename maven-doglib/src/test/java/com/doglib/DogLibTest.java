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
		
		assertEquals(15, dl.dogs.size());
		assertEquals("Havko",dl.dogs.get(3).getName());
		assertEquals("Shiba Inu",dl.dogs.get(2).getBreed());
		assertEquals("Bernese Mountain Dog",dl.dogs.get(1).getBreed());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");		
		assertEquals("05-02-2005",dl.dogs.get(0).getDate().format(formatter));
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
		
		assertEquals(15, dl.dogs.size());
		assertEquals(12.25, dl.averageWeight("Shiba Inu"),0.00001);
	}
	
	@Test
	public void averageWeightPerBreedTest() {
		DogLib dl = new DogLib("dogstest.xml");
		Map<String,Double> breedStats = dl.averageWeightPerBreed();
		
		assertEquals(15, dl.dogs.size());
		assertEquals(12.25, breedStats.get("Shiba Inu"),0.00001);
		assertEquals(29,breedStats.get("Siberian Husky"),0.00001);
		assertEquals(12, breedStats.get("Japanese Spitz"),0.00001);
		assertEquals(24.4,breedStats.get("German Shepherd"),0.00001);
		
	}
		
	/*@Test
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
		
		
	}*/
	
	@Test
	public void dogsByConditionTest() {
		DogLib dl = new DogLib("dogstest.xml");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		List<Dog> doglist;
		
		// Name
		doglist = dl.dogsByCondition(p->p.getName().equals("Havko"));		
		
		assertEquals(1,doglist.size());
		assertEquals("Siberian Husky",doglist.get(0).getBreed());
		
		doglist.clear();
		
		//Breed
		doglist = dl.dogsByCondition(p->p.getBreed().equals("Shiba Inu"));		
		
		assertEquals(2,doglist.size());
		assertEquals("Fuji",doglist.get(1).getName());
		assertEquals(7.5,doglist.get(1).getWeight(),0.00001);
		
		assertEquals(17,doglist.get(0).getWeight(),0.00001);
		
		
		assertEquals("18-03-2012",doglist.get(0).getDate().format(formatter));
		
		
		doglist.clear();
		
		// Weight
		doglist = dl.dogsByCondition(p->p.getWeight()>10);
		
		assertEquals(13,doglist.size());
		assertEquals("Kuki",doglist.get(4).getName());
		assertEquals("Siberian Husky",doglist.get(4).getBreed());
		
		assertEquals("Nikko",doglist.get(10).getName());
		assertEquals("Japanese Spitz",doglist.get(10).getBreed());
		
		doglist.clear();
		
		// Date
		LocalDate date=LocalDate.parse("10-01-2012",formatter);
		doglist = dl.dogsByCondition(p->p.getDate().isAfter(date));
		
		assertEquals(10,doglist.size());
		assertEquals("Havko",doglist.get(1).getName());
		assertEquals("Siberian Husky",doglist.get(1).getBreed());
		
		assertEquals("Fuji",doglist.get(9).getName());
		assertEquals("Shiba Inu",doglist.get(9).getBreed());
		
		
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
