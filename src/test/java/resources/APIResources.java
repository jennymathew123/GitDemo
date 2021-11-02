package resources;

public enum APIResources 
{

	addPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"), 
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resources;
	//Create a constructor of this enum
	APIResources(String resources)
	{
		this.resources=resources;
	}//constructor APIResources
	
	public String getResources()
	{
		return resources;
	}//getResources


}//enum APIResources
