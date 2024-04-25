package com.apitesting.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostCreateUserRequest {
    
    @BeforeMethod
    public void setup() {
    	RestAssured.baseURI = "https://gorest.co.in";
    }
    
    //post -- add a user -- user id = 123 -- assert(201, body)
    //get --> get a user --> /users/123 -- 200 -- userid=123
        
    @Test
    public void addUserTest() {
        
        
        //1. add user - POST
        int userId = RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(new File("./src/test/resources/addUser.json"))
            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
        .when()
            .post("/public/v2/users/")
        .then().log().all()
            .statusCode(201)
            .extract()
            .path("id");
                
        System.out.println("user id -->" + userId);
        
        //2. get the same user and verify it: GET
        int actualUserId = RestAssured.given()
            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
        .when().log().all()
            .get("/public/v2/users/"+ userId)
        .then().extract().path("id");
        
        assertEquals(actualUserId, userId, "User IDs do not match");
    }
}
