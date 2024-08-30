package com.RestAPI_BDDAndJava.testcases;



import java.util.Map;

//import constants.RestAssuredConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;


public class RestAssuredHelper {

	public Response SpecifyAndSendRequest(String requestType, String url, Map<String, String> requestBody,Map<String, String> queryParams,  Map<String, String> pathParams, String extensionpath) {
		
		// Create a RequestSpecification object
		RequestSpecification request = RestAssured.given();		
		 // Set Content-Type header
//		request.header(RestAssuredConstants.ContentType, RestAssuredConstants.ApplicationJson);
		// Set Content-Type header using RestAssured's built-in ContentType
        request.contentType(ContentType.JSON);

		// Add query parameters if they exist
		if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }
		
		
		  // Add path parameters if they exist
        if (pathParams != null && !pathParams.isEmpty()) {
            request.pathParams(pathParams);
        }
        
        //extensionpath path
        String fullpath;
		if (extensionpath != null && !extensionpath.isEmpty()) {
			fullpath = url+"/"+extensionpath;
        }else {
        	fullpath = url;
        }
		
		// Add the request body if it exists
		if(requestBody != null) 
		{
			request.body(requestBody);					
		}

		// Initialize the response object
		Response response = null;
  
		// Handle different request types
		switch(RequestType.valueOf(requestType)) 
		{
		case Delete:
		case DELETE:
		case delete:	
			response = request.delete(fullpath);
			break;
		case Get:
		case GET:
		case get:	
			response = request.get(fullpath);
			break;
		case Patch:
		case PATCH:
		case patch:	
			response = request.patch(fullpath);
			break;
		case Post:
		case POST:
		case post:	
			response = request.post(fullpath);
			break;
		case Put:
		case PUT:
		case put:	
			response = request.put(fullpath);
			break;
		default:
			throw new UnsupportedOperationException("Request type is not supported.");		
		}
		return response;
		
	}
}
