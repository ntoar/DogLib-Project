package com.doglib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

public class Dog {
	
	protected String name;
	protected LocalDate dateOfBirth;
	protected double weight;
	protected String breed;
	
	
	/*
	 * Initialize the dog with all the required attributes
	 * That is: a name, date of birth, weight and breed
	 * Date of birth is being passed as a string with format dd-MM-yyyy
	 * If not passing the correct format, will return exception of not a valid format
	 */
	public Dog(String name, String date, double weight, String breed) {
		this.name = name;
		
		// assign the datedOfBirth from a string in the format dd-MM-yyyy
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			this.dateOfBirth = LocalDate.parse(date,formatter);
		}catch(DateTimeParseException e) {
			System.out.println("Not a valid date format "+e);
		}
		
		this.weight = weight;
		this.breed = breed;
		
	}
	
	/*
	 * The getters for the attributes
	 * At this stage, there is no need for setters
	 * given the dog is initialized with all the required attributes in the constructor
	 */
	
	public String getName() {
		return this.name;
	}
	
	public LocalDate getDate() {
		return this.dateOfBirth;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public String getBreed() {
		return this.breed;
	}
	
	/*
	 * Filter a dog with some predicates
	 */
	
	// Breed Predicates
	public static Predicate<Dog> isEqBreed(String breed){
		return p -> p.getBreed().equals(breed);
	}
	
	public static Predicate<Dog> isNeqBreed(String breed){
		return p -> ! p.getBreed().equals(breed);
	}
	
	
	// Name Predicates
	public static Predicate<Dog> isEqName(String name){
		return p -> p.getName().equals(name);
	}
	
	public static Predicate<Dog> isNeqName(String name){
		return p -> ! p.getName().equals(name);
	}
	
	
	// Weight Predicates
	public static Predicate<Dog> isGreaterWeight(double weight){
		return p -> p.getWeight() > weight;
	}
	
	public static Predicate<Dog> isLesserWeight(double weight){
		return p -> p.getWeight() < weight;
	}
	
	
	// Date Predicates
	public static Predicate<Dog> isAfterDate(LocalDate date){
		return p -> p.getDate().isAfter(date);
	}
	
	public static Predicate<Dog> isBeforeDate(LocalDate date){
		return p -> p.getDate().isBefore(date);
	}
	
	
	
	public String toString() {
		return "Name: "+this.name+"\nDate of Birth: "+this.dateOfBirth+"\nWeight: "+this.weight+"\nBreed: "+this.breed;
		
	}
	

}
