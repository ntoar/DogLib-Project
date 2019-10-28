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
		DogLib dl = new DogLib("C:\\Users\\USUARIO\\assignment\\dogstest.xml");
		
		assertEquals(true, dl.dogs.size()==4);
	}
	
	@Test
	public void averageWeightTest() {
		DogLib dl = new DogLib("C:\\Users\\USUARIO\\assignment\\dogstest.xml");
		
		assertEquals(true, dl.averageWeight("Shiba Inu")==17);
	}
	
	@Test
	public void averageWeightPerBreedTest() {
		DogLib dl = new DogLib("C:\\Users\\USUARIO\\assignment\\dogstest.xml");
		Map<String,Double> breedStats = dl.averageWeightPerBreed();
		
		assertEquals(true, breedStats.get("Shiba Inu")==17 && breedStats.get("Siberian Husky")==25);
	}
	
	@Test
	public void dogsByConditionTest() {
		DogLib dl = new DogLib("C:\\Users\\USUARIO\\assignment\\dogstest.xml");
		List<Dog> listdog = dl.dogsByCondition("breed", "eq", "Shiba Inu");
		
		assertEquals(true, listdog.size()==1);
	}
	
	@Test
	public void oldestDogAfterDateTest() {
		DogLib dl = new DogLib("C:\\Users\\USUARIO\\assignment\\dogstest.xml");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date=LocalDate.parse("10-01-2012",formatter);		
		Dog d = dl.oldestDogAfterDate(date);
		
		assertEquals(true, d.getBreed().equals("Shiba Inu"));
	}

}
