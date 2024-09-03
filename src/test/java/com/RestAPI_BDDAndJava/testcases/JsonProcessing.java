package com.RestAPI_BDDAndJava.testcases;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;


public class JsonProcessing {

	public ObjectMapper objectMapper = new ObjectMapper();
	
	public JsonProcessing() 
	{
	}
	
	public String ConvertModelToJSON(Object model)
	{		
		String postModelAsString = null;
		try 
		{
			postModelAsString = objectMapper.writeValueAsString(model);
			return postModelAsString;
		} 
		catch (JsonProcessingException e) 
		{
			e.printStackTrace();
		}
		
		return postModelAsString;		
	}
	
	public Map<?, ?> ConvertModelToMap(Object model)
	{
		Map<?, ?> mappedObject = objectMapper.convertValue(model, Map.class);
		return mappedObject;
	}

	public JsonNode ApiRequestAndResponse(String path,String RequestorResponse) throws Exception {
		String filePath = "./RequestAndResponse/"+path+".json"; // Specify the path to your JSON file
		String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));

		// Parse JSON content
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonContent);

		// Extract request and expected response
		JsonNode requestNode = rootNode.get(RequestorResponse);
		return requestNode;
	}



}