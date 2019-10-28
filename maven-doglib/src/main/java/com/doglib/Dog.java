package com.doglib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Dog {
	
	protected String name;
	protected LocalDate dateOfBirth;
	protected double weight;
	protected String breed;
	
	Dog(String name, String date, double weight, String breed){
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
