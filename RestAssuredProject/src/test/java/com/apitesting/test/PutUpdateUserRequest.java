package com.apitesting.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PutUpdateUserRequest {
	public static String getRandomEmailId() {
		return "tripuratest"+System.currentTimeMillis()+"@mail.com";
	}
	
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in";
    }
    
    @Test
    public void updateUserTest() {
    	
    	JSONObject json = new JSONObject();
    	json.put("name", "tripura");    	
    	json.put("email", getRandomEmailId());
    	json.put("gender", "female");
    	json.put("status", "active");
    	

    	
    	Response response  = RestAssured.given().log().all()
    	            .contentType(ContentType.JSON)
    	            .body(json)
    	            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
    	        .when().log().all()
    	            .post("/public/v2/users/");
    	
    	String userId = response.jsonPath().get("id").toString();    	
    	response.prettyPrint();
    	
    	json.put("name", "tripura sundari");
    	json.put("status", "inactive");  	
    	 
     
    	
        
        Response res =RestAssured.given()
            .contentType(ContentType.JSON)
            .body(json) 
            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
            .when().log().all()
            .put("/public/v2/users/" + userId);
            
        res.prettyPrint();
        
        
        
    }
}
