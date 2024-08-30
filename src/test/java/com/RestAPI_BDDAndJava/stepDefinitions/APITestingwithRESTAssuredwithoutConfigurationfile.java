package com.RestAPI_BDDAndJava.stepDefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Map.Entry;

import com.RestAPI_BDDAndJava.Test.ScenarioContext;


public class APITestingwithRESTAssuredwithoutConfigurationfile {

	private String apiEndpoint;
	Response res ;
	private ScenarioContext scenarioContext = new ScenarioContext();

	@Given("the user API endpoint is {string}")
	public void theApiEndpointIs(String endpoint) {
		apiEndpoint = endpoint;
	}

	@When("^user send a (GET|DELETE) request to the endpoint$")
	public void iSendAGetRequestToTheEndpoint(String requestType) {

		if(requestType.equalsIgnoreCase("GET")) {
			res =  given()
					.when().get(apiEndpoint);
		}

		if(requestType.equalsIgnoreCase("DELETE")) {
			int id = scenarioContext.getId();
			res =  given()
					.when().delete(apiEndpoint+"/id");

		}
	}

	@Then("user verify status code should be {int}")
	public void theStatusCodeShouldBe(int expectedStatusCode) {
		assertEquals(expectedStatusCode,res.getStatusCode());
	}

	@Then("user verify the page size {int}")
	public void userVerifyThePageSize(int pageSize) {
		assertEquals(pageSize,res.jsonPath().getInt("page"));
	}

	@When("^user send a (POST|PATCH|PUT) request to the endpoint$")
	public void iSendAPostAndPatchRequestToTheEndpoint(String requestType, DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

		if(requestType.equalsIgnoreCase("POST")) {
			res =  given()
					.contentType("application/json")
					.body(dataMap)
					.when().post(apiEndpoint);
			int id = res.jsonPath().getInt("id");
			scenarioContext.setId(id);  // Store the id in ScenarioContext


		}
		if(requestType.equalsIgnoreCase("PATCH")) {
			res =  given()
					.contentType("application/json")
					.body(dataMap)
					.when().patch(apiEndpoint);

		}

		if(requestType.equalsIgnoreCase("PUT")) {
			int id = scenarioContext.getId();  // Retrieve the id from ScenarioContext
			System.out.println("All geting id: "+id);
			res =  given()
					.contentType("application/json")
					.body(dataMap)
					.when().put(apiEndpoint+"/id");
		}
	}





}
