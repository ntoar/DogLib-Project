package com.doglib;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.doglib.Dog;

public class DogLib {
	
	public List<Dog> dogs;	
	public File xmlFile;
	
	
	DogLib(String filePath){
		this.xmlFile = new File(filePath);
		dogs = new ArrayList<Dog>();
		initDogList(this.xmlFile);
		
	}
	
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
	
	public List<Dog> dogsByCondition(String a,String b,String c){
		List<Dog> listdog = new ArrayList<Dog>();
		for(int i=0;i<this.dogs.size();i++) {
			if(this.dogs.get(i).meetsCondition(a,b,c)) {
				listdog.add(this.dogs.get(i));
			}
		}		
		return listdog;
		
	}
	
	public Dog oldestDogAfterDate(LocalDate date) {
		
		Dog d=null;
		
		for(int i=0;i<this.dogs.size();i++) {
			if(d == null) {
				//System.out.println(this.dogs.get(i));
				if(this.dogs.get(i).getDate().isAfter(date))
					d=this.dogs.get(i);
			}
			else {
				if(this.dogs.get(i).getDate().isBefore(d.getDate())) {
					d=this.dogs.get(i);
				}
			}
		}
		
		return d;
	}
	
	
	
	// parse the xml dogs file using the DOM parser and initialize the dog list
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
