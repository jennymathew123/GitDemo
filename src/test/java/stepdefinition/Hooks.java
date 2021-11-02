package stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@deletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinitionClass m = new StepDefinitionClass();
		if(StepDefinitionClass.place_id==null)
		{
		m.add_place_payload("Mathew", "French", "USA");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Mathew", "POST");
		}//if placeid is null
	}//beforeScenario()
}//class
