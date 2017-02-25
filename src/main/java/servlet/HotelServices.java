/**
 * @author mohammad.k.hader on 2/24/2016.
 * The class (Service) that serve the controller with bunch of services 
 */
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
	/**
     *  method with 1 parameter of type String
     *  to readand parse JSON objects from a given URL
     *  @param url holder of JSON API objects
     */
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
	/**
     *  method with 1 parameter of type JSONArray
     *  to return a list of information about hotels
     *  @param HotelArray holder of JSONArray array
     */
	public List<HotelInformation> allHotels(JSONArray HotelArray ) throws IOException, ParseException {
	
		List<HotelInformation> list=new ArrayList<HotelInformation>();
		
		for (JSONObject innerObj : HotelArray){
			
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
	public void display(PrintWriter out,int hotelsCount,List<HotelInformation> list){
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Exp94</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>There is "+hotelsCount+" hotel deal as parsed from JSON API</p>");
		out.println("<br><br><hr>");
		for(HotelInformation hotel : list){
			out.println("<h3>"+hotel.getHotelName()+"</h3>");
			out.println("<img src=\""+hotel.getImgPath()+"\">");
			out.println("<p>"+hotel.getDest()+"</p>");
			out.println("<p>"+hotel.getTripDate()+"</p>");
			out.println("<p>"+hotel.getRatings()+"</p>");
			out.println("<p>"+hotel.getDescription()+"</p><br>");
			out.println("<p> Price per night: "+hotel.getPrice()+" US</p>");
			
			out.println("<br><br><hr>");
		 
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
}
    

