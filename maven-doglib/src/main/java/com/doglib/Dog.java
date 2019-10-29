package com.doglib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
	 * Method to evaluate if the dog meets a certain condition
	 * It takes three String params:
	 * a: the dog attribute to check for the condition (breed, name, weight, date)
	 * b: the comparison operator depending on the attribute can be
	 * 		eq, neq for breed and name
	 * 		>, <, = for weight
	 * 		isAfter, isBefore, isEqual for date
	 */
	
	public boolean meetsCondition(String a, String b, String c) {
		
		switch(a) {
		case "breed":
			if(b.equals("eq")) {
				return this.breed.equals(c);
			}
			else if (b.contentEquals("neq")) {
				return !this.breed.equals(c);
			}
			else return false;
			
		case "name":
			if(b.equals("eq")) {
				return this.name.equals(c);
			}
			else if (b.contentEquals("neq")) {
				return !this.name.equals(c);
			}
			else return false;
				
		case "weight":
			if(b.equals(">")) {
				return this.weight > Double.parseDouble(c);
			}
			else if (b.equals("<")) {
				return this.weight < Double.parseDouble(c);
			}
			else if (b.contentEquals("=")) {
				return this.weight == Double.parseDouble(c);
			}
			else return false;
			
		case "date":
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(c,formatter);
			if(b.equals("isAfter")) {
				return this.dateOfBirth.isAfter(date);
			}
			else if(b.equals("isBefore")) {
				return this.dateOfBirth.isBefore(date);
			}
			else if(b.equals("isEqual")) {
				return this.dateOfBirth.isEqual(date);
			}
			else return false;
			
		default:
			return false;
			
		}
	}
	

}
