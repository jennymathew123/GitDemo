Feature: Validation Place APIs

@addPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given add place payload "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "POST" http request
Then the API call si success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
	|name    	|language 	|address 		  | 
	|Jenny M 	|English  	|World cosss center|
#	|Piyush N	|French   	|Sea cross center  |
	
@deletePlace	
Scenario: Verify if Delete Place functionality is working
Given DeletePlace payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call si success with status code 200
And "status" in response body is "OK"