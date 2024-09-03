package com.RestAPI_BDDAndJava.Action;


import static org.junit.Assert.assertEquals;


import java.util.Map;
import java.util.Map.Entry;


import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;


import com.RestAPI_BDDAndJava.testcases.BaseTests;
import com.RestAPI_BDDAndJava.testcases.RestAssuredHelper;
import com.RestAPI_BDDAndJava.utilities.ReadConfigAPI;
import com.RestAPI_BDDAndJava.utilities.TestKeyValueWriteAndRead;
import com.RestAPI_BDDAndJava.testcases.JsonProcessing;

import io.restassured.response.Response;

public class Test2_RestAPIwithbaseURLAction extends RestAssuredHelper {
    JsonProcessing JsonProcessing = new JsonProcessing();


    BaseTests test;

    public void baseURLCalling() {
        test = new BaseTests();
        test.logger.info("Base URl is Initialized");
    }

    Response response;
    Map<String, String> postOrPatchpairs;

    public void getEndPointCalling(String requestType) {
        response = SpecifyAndSendRequest(requestType, ReadConfigAPI.demoGetPostRequest(), null, null, null, null);
        test.logger.info("It's a Get call");
    }

    public void verifytheStatusCode(int statusCode) {

        test.AssertStatusCode(response, statusCode);
    }

    public void verifygetpostVales(Map<String, String> dataMap) {
        String actualResponseBody = response.jsonPath().getMap("data").toString();
        System.out.println("Actual Response Content:" + actualResponseBody);

        String expectedResponseBody = dataMap.toString();
        System.out.println("Expected Response Content:" + expectedResponseBody);


        assertEquals(actualResponseBody, expectedResponseBody);
    }

    public void postAndPathCalling(Map<String, String> dataMap, String requestType) {
        if (requestType.equals("POST") || requestType.equals("Post") || requestType.equals("post")) {
            response = SpecifyAndSendRequest(requestType, ReadConfigAPI.postcallingRequest(), dataMap, null, null, null);
            postOrPatchpairs = dataMap;
            test.logger.info("It's a Post call");
        } else if (requestType.equals("Put") || requestType.equals("PUT") || requestType.equals("PUT")) {
            String id = TestKeyValueWriteAndRead.readtheDataFromFile("id");
            response = SpecifyAndSendRequest(requestType, ReadConfigAPI.postcallingRequest(), dataMap, null, null, id);
            test.logger.info("It's a Put call");
            postOrPatchpairs = dataMap;

        }
    }

    public void PostCallingResponse(String requestType) {
        for (Entry<String, String> dataArr : postOrPatchpairs.entrySet()) {
            String actualResponsePairValue = response.jsonPath().get(dataArr.getKey());
            assertEquals(actualResponsePairValue, dataArr.getValue());
        }
        if (requestType.equals("Post")) {
            String idValue = String.valueOf(response.jsonPath().getInt("id"));
            TestKeyValueWriteAndRead.writetheDataintoFile("id", idValue);
        }

    }

	public void  verifySchemaForPost(String jsonSchemaPath) {
        System.out.println("Response Body: "+response.prettyPrint());
        test.verifySchema(response, jsonSchemaPath);

	}

    public void getEndPointCallingwithqueryParams(String requestType,Map<String, String> dataMap) {
        response = SpecifyAndSendRequest(requestType, ReadConfigAPI.postcallingRequest(), null, dataMap, null, null);
        test.logger.info("It's a Get call");
    }

    // this All methods are part of json file
    public void verifytheGetRequest() throws Exception {
        JsonNode expectedResponseNode = JsonProcessing.ApiRequestAndResponse("listUser", "response");
        String expectedResponseBody = expectedResponseNode.toString();
        String actualResponseBody = JsonProcessing.ConvertModelToJSON(response.jsonPath().get()).toString();
        System.out.println("Actual Response Content:" + actualResponseBody);
        System.out.println("Expected Response Content:" + expectedResponseBody);
        assertEquals(actualResponseBody, expectedResponseBody);


    }

    public void hitCreateUserRequest() throws Exception {
        JsonNode requesteNode = JsonProcessing.ApiRequestAndResponse("CreatePost", "response");
        Map<?, ?> mapPostRequest = JsonProcessing.ConvertModelToMap(requesteNode);

        response = SpecifyAndSendRequest("post", ReadConfigAPI.postcallingRequest(), mapPostRequest, null, null, null);

    }

    public void verifyJsonPostResponse() throws Exception {
        JsonNode expectedResponseNode = JsonProcessing.ApiRequestAndResponse("CreatePost", "request");
        String expectedResponseBody = expectedResponseNode.toString();


        String actualResponseBody = JsonProcessing.ConvertModelToJSON(response.jsonPath().get()).toString();
        System.out.println("Actual Response Content:" + actualResponseBody);
        System.out.println("Expected Response Content:" + expectedResponseBody);

    }




}
