package com.doglib;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DogLib {
	
	/*
	 * This library will be used to keep track of dog information as a list of dogs
	 * In order to initialize the dog list, an xml document with the list of dogs has to be passed
	 * Example of xml doc:
	 * 
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *	<dogs>
	 *		<dog name="Corrie">
     *			<dateOfBirth>18-03-2012</dateOfBirth>
     *			<weight>17</weight>
     *			<breed>Shiba Inu</breed>
  	 *		</dog>
  	 *		<dog name="Havko">
     *			<dateOfBirth>25-08-2014</dateOfBirth>
     *			<weight>25</weight>
     *			<breed>Siberian Husky</breed>
  	 *		</dog>
	 *	</dogs>
	 *
	 */
	
	public List<Dog> dogs;	
	public File xmlFile;
	
	
	/*
	 * Initialize an empty dog list
	 */
	public DogLib() {
		this.dogs = new ArrayList<Dog>();
	}
	
	
	/*
	 * Initialize the dog list from the xml file using the method initDogList 
	 */
	public DogLib(String filePath){
		this.xmlFile = new File(filePath);
		this.dogs = new ArrayList<Dog>();
		initDogList(this.xmlFile);
		
	}
	
	/*
	 * Insert new dog in the list
	 */
	public void addDog(Dog d) {
		this.dogs.add(d);
	}
	
	
	/*
	 * This method returns the average weight of the dogs of a certain breed entered as a parameter
	 * It loops through the list and sums the weight of all the dogs belonging to the breed
	 * It also gets the number of dogs that belong to that breed
	 * Then it returns the sum of the weight / the number of dogs to get the average weight 
	 */
	public double averageWeight(String breed) {
		double sum=0;
		int num=0;
		for(int i=0;i<this.dogs.size();i++) {
			Dog d=this.dogs.get(i);
			if(d.getBreed().contentEquals(breed)) {
				sum+=d.getWeight();
				num++;
			}
		}
		return sum/num;
	}
	
	/*
	 * This method returns the average weight for all breeds in the form of a Map of String,Double
	 * So you would end having the map like:
	 * [Shiba Inu] -> [17]
	 * [Siberian Husky] -> [25]
	 * 
	 * In order to calculate this, first we get a map of String,List<Double> so to have 
	 * a list of doubles that represent the weight for every dog of the same breed
	 * Then we iterate over the map calculating the average value 
	 */
	
	public Map<String,Double> averageWeightPerBreed(){
		
		Map<String,List<Double>> breedList = new HashMap<String,List<Double>>();
		Map<String,Double> breedStats = new HashMap<String,Double>();
		
		for(int i=0;i<this.dogs.size();i++) {
			String breed = this.dogs.get(i).getBreed();
			double weight = this.dogs.get(i).getWeight();
			
			breedList.putIfAbsent(breed, new ArrayList<Double>());
			breedList.get(breed).add(weight);
			
		}
		
		
		for(Map.Entry<String,List<Double>> entry : breedList.entrySet()) {
			String breed=entry.getKey();
			List<Double> weightValues=entry.getValue();
			
			double sum=0;
			int num=0;
			for(int i=0;i<weightValues.size();i++) {
				sum+=weightValues.get(i);
				num++;				
			}
			
			breedStats.put(breed, sum/num);
			
			
		}
		
		return breedStats;
	}
	
	
	/*
	 * This method returns a list of the dogs that meet a certain condition
	 * 
	 */
	
	public List<Dog> dogsByConditionPredicate(Predicate<Dog> predicate){
		return this.dogs.stream().filter(predicate).collect(Collectors.<Dog>toList());
	}
	
	/*
	 * This method returns the oldest dog after a certain date of birth
	 */
	
	public Dog oldestDogAfterDate(LocalDate date) {
		
		Dog d=null;
		
		for(int i=0;i<this.dogs.size();i++) {
			if(d == null) {
				//System.out.println(this.dogs.get(i));
				if(this.dogs.get(i).getDate().isAfter(date))
					d=this.dogs.get(i);
			}
			else {
				if(this.dogs.get(i).getDate().isAfter(date) && this.dogs.get(i).getDate().isBefore(d.getDate())) {
					d=this.dogs.get(i);
				}
			}
		}
		
		return d;
	}
	
	
	
	/* 
	 * Parse the xml dogs file using the DOM parser and initialize the dog list
	 */
	private void initDogList(File f) {
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			doc.getDocumentElement();
			NodeList dogNodes=doc.getElementsByTagName("dog");
			for(int i=0;i<dogNodes.getLength();i++) {
				Node dogNode = dogNodes.item(i);
				
				if(dogNode.getNodeType() == Node.ELEMENT_NODE) {
					Element dogElement = (Element) dogNode;
					
					String dogName = dogElement.getAttribute("name");
					String dogDate = dogElement.getElementsByTagName("dateOfBirth").item(0).getTextContent();
					Double dogWeight = Double.parseDouble(dogElement.getElementsByTagName("weight").item(0).getTextContent());
					String dogBreed = dogElement.getElementsByTagName("breed").item(0).getTextContent();
					
					this.dogs.add(new Dog(dogName,dogDate,dogWeight,dogBreed));
				}
			}			
		}catch(Exception e) {
			System.out.println("Can't parse document "+ this.xmlFile);
		}
			
		
		
	}

}
