package com.project.app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.project.app.model.GoldenData;

@Service
public class Utility {

	public void insertData() {
		List<GoldenData> goldList=new ArrayList<>();
		String line = "";  
		String splitBy = ",";  
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
			
		
		BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Chickpea.csv"));  
		
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] goldenStrings = line.split(splitBy);    // use comma as separator  
			GoldenData n=new GoldenData();
			n.setBuildingKey(goldenStrings[0]);
			n.setRoomNo(goldenStrings[1]);
			n.setVcNo(goldenStrings[2]);
			n.setStbNo(goldenStrings[3]);
			n.setsNo(Integer.parseInt(goldenStrings[4]));
			goldList.add(n);
		}  
		 Firestore dbFirestore = FirestoreClient.getFirestore();  
		 for(GoldenData gold:goldList)
		 {
			 dbFirestore.collection("Chickpea").add(gold);  
				 
		 }
		
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();  
		}  
		
	}
}
