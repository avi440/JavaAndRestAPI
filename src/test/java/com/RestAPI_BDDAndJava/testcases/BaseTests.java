package com.RestAPI_BDDAndJava.testcases;

import static org.junit.Assert.*;

import java.util.Map;

import com.RestAPI_BDDAndJava.utilities.ReadConfigAPI;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class BaseTests {
	ReadConfigAPI readConfigAPI;
	JsonProcessing data;

	RestAssuredHelper restAssuredHelper;
	public Logger logger; 

	public BaseTests(){
		readConfigAPI = new ReadConfigAPI();
		SetBaseUri();
		data = new JsonProcessing();
		restAssuredHelper = new RestAssuredHelper();
		logger =  Logger.getLogger("ReastAPI");
		 PropertyConfigurator.configure("./src/test/resources/log4j.properties");
	}

	public void SetBaseUri(){
		RestAssured.baseURI = ReadConfigAPI.baseURL();
	}

	public void AssertStatusCode(Response response, int expectedStatusCode){
		int actualStatusCode = response.getStatusCode();
		System.out.println("Response Status Code: " + actualStatusCode);
		assertEquals(expectedStatusCode, actualStatusCode);
	}

	public void AssertContent(Object postModel, Response response){
		Map<?, ?> actualResponseBody = response.jsonPath().get();
		System.out.println("Actual Response Content:" + actualResponseBody);

		Map<?, ?> expectedResponseBody = data.ConvertModelToMap(postModel);
		System.out.println("Expected Response Content:" + expectedResponseBody);

		assertEquals(expectedResponseBody, actualResponseBody);
	}	
}
