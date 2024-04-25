package com.apitesting.test;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUserRequestTest {
	RequestSpecification request;
	@BeforeTest
	public void setup() {
		// Request:
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");

	}
	
	@Test
	public void getAllUsersAPITest() {

		Response response = request.get("/public/v2/users/");
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		response.prettyPrint();
		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

	}
	@Test
	public void getAllUsersWithQueryParameter() {

		request.queryParam("email", "tripura@gmail.com");
		request.queryParam("status", "active");

		Response response = request.get("/public/v2/users");

		// =================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);

		String id = response.jsonPath().get("id").toString();
		System.out.println("id status code : " + id);
		// verification point:
		Assert.assertEquals(statusCode, 200);

		// status mesg:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

	}


}
