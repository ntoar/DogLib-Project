package com.doglib;

import static org.junit.Assert.*;

import org.junit.Test;

public class DogTest {

	// Tests meet eq condition
	 	
	@Test
	public void meetsNameConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("name", "eq", "Pluto"));
	}
	
	@Test
	public void meetsBreedConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("breed", "eq", "Cartoon Puppy"));
	}
	
	@Test
	public void meetsWeightConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("weight", "=", "15"));
	}
	
	@Test
	public void meetsDateConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("date", "isEqual", "10-01-2010"));
	}
	
	
	// Test meet neq condition
	 	
	@Test
	public void meetsNameConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("name", "neq", "Plutos"));
	}
	
	@Test
	public void meetsBreedConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("breed", "neq", "Cartoons Puppy"));
	}
	
	@Test
	public void meetsWeightConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("weight", ">", "10"));
	}
	
	@Test
	public void meetsDateConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(true,d.meetsCondition("date", "isAfter", "05-01-2010"));
	}
	
	
	
	// Tests not meet eq condition
	 	
	@Test
	public void notMeetsNameConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("name", "eq", "Plutos"));
	}
	
	@Test
	public void notMeetsBreedConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("breed", "eq", "Cartoons Puppy"));
	}
	
	@Test
	public void notMeetsWeightConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("weight", "=", "10"));
	}
	
	@Test
	public void notMeetsDateConditionEqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("weight", "isEqual", "05-01-2010"));
	}
	
	
	
	// Tests not meet neq condition
	
	@Test
	public void notMeetsNameConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("name", "neq", "Pluto"));
	}
	
	@Test
	public void notMeetsBreedConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("breed", "neq", "Cartoon Puppy"));
	}
	
	@Test
	public void notMeetsWeightConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("weight", ">", "18"));
	}
	
	@Test
	public void notMeetsDateConditionNeqTest() {
		Dog d = new Dog("Pluto","10-01-2010",15,"Cartoon Puppy");
		
		assertEquals(false,d.meetsCondition("weight", "isAfter", "05-01-2010"));
	}

}
