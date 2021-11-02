package stepdefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Section69AddPlace;
import pojo.Section69Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitionClass extends Utils
{
	ResponseSpecification respec;
	RequestSpecification theaddedMapLocation;
	Response res;
	static String place_id;
	JsonPath js;
	TestDataBuild data = new TestDataBuild();
	
	@Given("add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException
	{
		theaddedMapLocation =given().spec(requestSpecification())
		.body(data.addPlacePayLoad(name,language,address)); //here we are sending java object of main pojo class
		
		
	}//add_place_payload()
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resources, String method)
	{
		//Constructor will be called
		APIResources resourceAPI = APIResources.valueOf(resources);
		System.out.println(resourceAPI.getResources());
		
		respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		res = theaddedMapLocation.when()
				.post(resourceAPI.getResources())
				.then().spec(respec)
				.extract().response();
		else if(method.equalsIgnoreCase("GET"))
			res = theaddedMapLocation.when()
			.get(resourceAPI.getResources())
			.then().spec(respec)
			.extract().response();
		
	}//user_calls_with_post_http_request()
	
	@Then("the API call si success with status code {int}")
	public void the_api_call_si_success_with_status_code(Integer int1) 
	{
		assertEquals(res.getStatusCode(),200);
	}//the_api_call_si_success_with_status_code()
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expecteValue) {
	//	String re = res.asString();
	//	   js = new JsonPath(re);
	//	   place_id = js.get("place_id");
		   assertEquals(getJsonPath(res, keyValue),expecteValue);
	}//in_response_body_is()
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException 
	{
		//place_id = getJsonPath(res,"place_id");
		place_id = getJsonPath(res, "place_id");
		theaddedMapLocation =given().spec(requestSpecification())
				.queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(res, "name");
		assertEquals(actualName,expectedName);
	}//verify_place_id_created_maps_to_using()
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException 
	{
		theaddedMapLocation = given().spec(requestSpecification())
		.body(data.deletePlace(place_id));
	}//delete_place_payload()
	
}//class StepDefinitionClass

