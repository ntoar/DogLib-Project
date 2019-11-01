package com.doglib;

import static org.junit.Assert.*;

import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DogTest {

	@Test
	public void getNameTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		assertEquals("Pluto",d.getName());
	}
	
	@Test
	public void getBreedTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		assertEquals("Cartoon Puppy",d.getBreed());
	}
	
	@Test
	public void getWeightTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		assertEquals(15,d.getWeight(),0.00001);
	}
	
	@Test
	public void getDateTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		assertEquals("10-01-2010",d.getDate().format(formatter));
	}
	
	@Test
	public void setWeightTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		d.setWeight(17);
		
		assertEquals(17,d.getWeight(),0.00001);
	}

}
