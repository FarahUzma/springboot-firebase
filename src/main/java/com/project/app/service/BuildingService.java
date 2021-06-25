package com.project.app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project.app.model.Building;
import com.project.app.model.GoldenData;
import com.project.app.model.GoldenDataResidents;

@Service
public class BuildingService {
	 
	 public static final String COL_NAME="Buildings"; 
	  
	 
	 
	 public String savePatientDetails(Building building) throws InterruptedException, ExecutionException {  
		 Firestore dbFirestore = FirestoreClient.getFirestore();  
		 ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(building.getBuildingKey()).set(building);  
		 return collectionsApiFuture.get().getUpdateTime().toString();  
	 }  
	 
	 public List<Building> getBuildingNames() throws InterruptedException, ExecutionException {  
		 Firestore db = FirestoreClient.getFirestore();
		 List<Building> builds=new ArrayList<>();
		    ApiFuture<QuerySnapshot> query = db.collection(COL_NAME).get();
		    QuerySnapshot querySnapshot = query.get();
		    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		    for(QueryDocumentSnapshot document:documents)
		    {
		    	Building build = document.toObject(Building.class);
		    	builds.add(build);
		    	
		    }
		 return builds;  
	 } 
	 public List<String> getRoomNumbers(String buildingKey) throws InterruptedException, ExecutionException
	 {
		 List<String> rooms = new ArrayList<>();
		 Firestore db = FirestoreClient.getFirestore();
		    ApiFuture<QuerySnapshot> query = db.collection(buildingKey).get();
		    QuerySnapshot querySnapshot = query.get();
		    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		    
		    List<GoldenDataResidents> allData = new ArrayList<>();
		    for(QueryDocumentSnapshot document:documents)
		    {
		    	GoldenDataResidents goldenData = document.toObject(GoldenDataResidents.class);
		    	allData.add(goldenData);
		    	
		    }
		    
		    Collections.sort(allData, new Comparator<GoldenDataResidents>() {
	           @Override
				public int compare(GoldenDataResidents o1, GoldenDataResidents o2) {
					 return ((Integer)o1.getsNo()).compareTo(o2.getsNo());
				}
	        });
		    
		    for(GoldenDataResidents document:allData)
		    {
		    	
		    	rooms.add(document.getRoomNo());
		    }
		    
		return rooms;
	 }
	 public String getVcNo(String buildingKey, String roomNo) throws InterruptedException, ExecutionException {
		 
		 Firestore db = FirestoreClient.getFirestore();
		    ApiFuture<QuerySnapshot> query = db.collection(buildingKey).get();
		    QuerySnapshot querySnapshot = query.get();
		    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		    for(QueryDocumentSnapshot document:documents)
		    {
		    	GoldenDataResidents goldenData = document.toObject(GoldenDataResidents.class);
		    	if(goldenData.getRoomNo().equalsIgnoreCase(roomNo))
				{
		    		return goldenData.getVcNo();
				}
		    	
		    }
			return "FAILURE";
		    
		}
	 
	 public String updatePatientDetails(Building building) throws InterruptedException, ExecutionException {  
		 Firestore dbFirestore = FirestoreClient.getFirestore();  
		 ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(building.getBuildingKey()).set(building);  
		 return collectionsApiFuture.get().getUpdateTime().toString();  
	 }  
	 public String deletePatient(String name) {  
		 Firestore dbFirestore = FirestoreClient.getFirestore();  
		 ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();  
	 return "Document with Patient ID "+name+" has been deleted";  
	 }

	

}
