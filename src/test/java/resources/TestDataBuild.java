package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Section69AddPlace;
import pojo.Section69Location;

public class TestDataBuild 
{
	public Section69AddPlace addPlacePayLoad(String name, String language, String address)
	{
		Section69AddPlace ap = new Section69AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		ap.setName(name);
		
		List<String> myListTypes = new ArrayList<String>();
		myListTypes.add("shoe park");
		myListTypes.add("shoe");
		ap.setTypes(myListTypes);
		
		Section69Location l = new Section69Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ap.setLocation(l);
		return ap;
	}//addPlacePayLoad()
	
	public String deletePlace(String place_id)
	{
		return ("{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}");
	}//deletePlace()
}//class TestDataBuild
