package com.RestAPI_BDDAndJava.Action;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.RestAPI_BDDAndJava.Test.ScenarioContext;
import com.RestAPI_BDDAndJava.testcases.BaseTests;
import com.RestAPI_BDDAndJava.testcases.RestAssuredHelper;
import com.RestAPI_BDDAndJava.utilities.ReadConfigAPI;
import com.RestAPI_BDDAndJava.utilities.TestKeyValueWriteAndRead;

import io.restassured.response.Response;

public class Test2_RestAPIwithbaseURLAction extends RestAssuredHelper {
//	 private ScenarioContext scenarioContext = new ScenarioContext();
	 

	BaseTests test ;
	public void  baseURLCalling(){
		test = new BaseTests();
		test.logger.info("Base URl is Initialized");
	}

	Response response;
	Map<String, String> postOrPatchpairs;

	public void  getEndPointCalling(String requestType){
		response = SpecifyAndSendRequest(requestType, ReadConfigAPI.demoGetPostRequest(), null, null, null, null);
		test.logger.info("It's a Get call");
	}

	public void  verifytheStatusCode(int statusCode){

		test.AssertStatusCode(response, statusCode);
	}

	public void  verifygetpostVales(Map<String, String> dataMap){
		String actualResponseBody = response.jsonPath().getMap("data").toString();
		System.out.println("Actual Response Content:" + actualResponseBody);
		
		String expectedResponseBody = dataMap.toString();
		System.out.println("Expected Response Content:" + expectedResponseBody);

		assertEquals(actualResponseBody, expectedResponseBody);
	}
	
	public void  postAndPathCalling(Map<String, String> dataMap, String requestType){
		if(requestType.equals("POST")|| requestType.equals("Post") || requestType.equals("post")) {
		response = SpecifyAndSendRequest(requestType,ReadConfigAPI.postcallingRequest(), dataMap, null, null, null);
		postOrPatchpairs = dataMap;
		test.logger.info("It's a Post call");
		}else if(requestType.equals("Put")|| requestType.equals("PUT") || requestType.equals("PUT")){
			String id = TestKeyValueWriteAndRead.readtheDataFromFile("id");
			response = SpecifyAndSendRequest(requestType,ReadConfigAPI.postcallingRequest(), dataMap, null, null, id);
			test.logger.info("It's a Put call");
			postOrPatchpairs = dataMap;
			
		}
	}
	
	public void  PostCallingResponse(String requestType){
		for(Entry<String, String> dataArr :postOrPatchpairs.entrySet()) {
			String actualResponsePairValue = response.jsonPath().get(dataArr.getKey());
			assertEquals(actualResponsePairValue, dataArr.getValue());
		}
		if(requestType.equals("Post")) {
		String idValue = String.valueOf(response.jsonPath().getInt("id"));
		TestKeyValueWriteAndRead.writetheDataintoFile("id", idValue);
	}

	}
	

	
	
}
