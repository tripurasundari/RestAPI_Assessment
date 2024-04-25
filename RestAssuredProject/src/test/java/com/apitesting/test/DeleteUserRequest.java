package com.apitesting.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;


public class DeleteUserRequest {
	ObjectMapper objectMapper = new ObjectMapper();
	String email;
    @BeforeTest
    public void setup() {
    	
        RestAssured.baseURI = "https://gorest.co.in";
        
    }
    
    
            
    @Test
    public void deleteAddedUserTest() {
    	JSONObject json = new JSONObject();
    	json.put("name", "tripura");    	
    	json.put("email", "tripuraTest@gmail.com");
    	json.put("gender", "female");
    	json.put("status", "active");
    	// 1. create userid - POST
    	Response response  = RestAssured.given().log().all()
	            .contentType(ContentType.JSON)
	            .body(json)
	            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	        .when().log().all()
	            .post("/public/v2/users/");
	
    	String userId = response.jsonPath().get("id").toString();    	
		       // 2. get userid - POST
    	RestAssured.given().header("Authorization",
			  "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			 .when().log().all() .get("/public/v2/users/" + userId)
			  .then().statusCode(200);
        
        
        
        
        // 3. Delete the user - DELETE
		
		  RestAssured.given() .header("Authorization",
		  "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		 .when().log().all() .delete("/public/v2/users/" + userId)
		  .then().statusCode(204);
		  
		  // 4. Try to get the user again - GET (expecting 404) RestAssured.given()
		  RestAssured.given().header("Authorization",
		  "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		 .when().log().all() .get("/public/v2/users/" + userId)
		  .then().statusCode(404);
		 
       
    }
}
