package com.RestAPI_BDDAndJava.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import com.RestAPI_BDDAndJava.Action.Test2_RestAPIwithbaseURLAction;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test2_RestAPIwithbaseURL {

	Test2_RestAPIwithbaseURLAction action = new Test2_RestAPIwithbaseURLAction();


	@Given("user hit the Base URL")
	public void useHitTheBaseURL() {
		action.baseURLCalling();

	}

	@When("^user send a (Get|DELETE) request with endpoint")
	public void userSendGETRequestWithEndpoint(String requestType) {
		action.getEndPointCalling(requestType);

	}

	@Then("user verify status code was {int}")
	public void StatusCodeWas(int expectedStatusCode) {
		action.verifytheStatusCode(expectedStatusCode);

	}

	@Then("user verify the response values for Get calling$")
	public void verifyResponseValues(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		action.verifygetpostVales(dataMap);
	}

	@When("user send a (POST|Patch|PUT) request with endpoint$")
	public void verifyPostAndPatchValues(String requestType,DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		action.postAndPathCalling(dataMap,requestType);
	}

	@Then("^user verify the response values for (Post|Put) calling$")
	public void verifyResponseValuesForPostCalling(String requestType) {
		if(requestType.equals("Post")) {
			action.PostCallingResponse(requestType);
		}else {
			action.PostCallingResponse("");
		}

	}




}
