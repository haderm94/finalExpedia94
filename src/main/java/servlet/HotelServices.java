package servlet;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import java.io.File;
import java.util.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HotelServices  {
	public JSONObject readJsonFromUrl(String url) throws IOException, ParseException {
		InputStream is = new URL(url).openStream();
		try {
		  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		  JSONParser jsonParser = new JSONParser();
		  JSONObject json = (JSONObject) jsonParser.parse(rd);	
		
		  return json;
		} finally {
		  is.close();
		}
	}
	public List<HotelInformation> allHotels(JSONArray HotelArray ) throws IOException, ParseException {
	
		List<HotelInformation> list=new ArrayList<HotelInformation>();
		Iterator i = HotelArray.iterator();
		
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			
			JSONObject offerDateRange = (JSONObject)innerObj.get("offerDateRange");
			JSONObject destination = (JSONObject)innerObj.get("destination");
			JSONObject hotelInfo = (JSONObject)innerObj.get("hotelInfo");
			JSONObject hotelPricingInfo = (JSONObject)innerObj.get("hotelPricingInfo");
			JSONObject hotelUrls = (JSONObject)innerObj.get("hotelUrls");
			
			String dest="Destination: "+ destination.get("country") + "-"+ destination.get("city") +",   Region ID: " + destination.get("regionID");
			String tripDate="Trip starts at: "+ hotelInfo.get("travelStartDate") + " To " + hotelInfo.get("travelEndDate")+ "<br>Length of Stay " + offerDateRange.get("lengthOfStay");
			String ratings="hotel Star Rating: "+ hotelInfo.get("hotelStarRating")+"<br>"+"hotel Guest Review Rating: " + hotelInfo.get("hotelGuestReviewRating");
			String hotelName=hotelInfo.get("hotelName").toString();
			String imgPath=hotelInfo.get("hotelImageUrl").toString();
			String description=hotelInfo.get("description").toString();
			String nightPrice=hotelPricingInfo.get("originalPricePerNight").toString();

			HotelInformation info=new HotelInformation();
			
			info.setDest(dest);
			info.setTripDate(tripDate);
			info.setRatings(ratings);
			info.setImgPath(imgPath);
			info.setDescription(description);
			info.setHotelName(hotelName);
			info.setPrice(nightPrice);
			list.add(info); /**/
					
			
		}
		return list;
	}
	
	
}
    

