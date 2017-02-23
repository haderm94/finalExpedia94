package servlet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )

public class HelloServlet extends HttpServlet {
	private static final String filePath = "getOffers.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
			
		PrintWriter out = resp.getWriter();		
		
		try {
			
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);	
								
			JSONObject offers = (JSONObject)jsonObject.get("offers");
			JSONArray HotelArray= (JSONArray) offers.get("Hotel");
			int hotelsCount=HotelArray.size();
			req.setAttribute("hotelsCount",hotelsCount);
			
			//List<String> list=new ArrayList<String>();
			//HotelInfo list[] = new HotelInfo[hotelsCount];
			Iterator i = HotelArray.iterator();
			int count=0;
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				
				JSONObject offerDateRange = (JSONObject)innerObj.get("offerDateRange");
				JSONObject destination = (JSONObject)innerObj.get("destination");
				JSONObject hotelInfo = (JSONObject)innerObj.get("hotelInfo");
				JSONObject hotelPricingInfo = (JSONObject)innerObj.get("hotelPricingInfo");
				JSONObject hotelUrls = (JSONObject)innerObj.get("hotelUrls");
				
				String dest="Destination: "+ destination.get("country") + "-"+ destination.get("city") +", Region ID: " + destination.get("regionID");
				String tripDate="Trip starts at: "+ hotelInfo.get("travelStartDate") + " To " + hotelInfo.get("travelEndDate")+ ". LengthOfStay " + offerDateRange.get("lengthOfStay");
				String ratings="hotelStarRating: "+ hotelInfo.get("hotelStarRating")+"\n"+"hotelGuestReviewRating " + hotelInfo.get("hotelGuestReviewRating");
				String imgPath=hotelInfo.get("hotelImageUrl").toString();
				String description=hotelInfo.get("description").toString();
				
				// HotelInfo hotelInfo=new HotelInfo();
				/*
				list[count].setDest(dest);
				list[count].setTripDate(tripDate);
				list[count].setRatings(ratings);
				list[count].setImgPath(imgPath);
				list[count].setDescription(description);
				count++;
				//list.add(hotelInfo); */
						
				
			}
			//req.setAttribute("listOfHotels",list);
			

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	

		RequestDispatcher dd=req.getRequestDispatcher("deals.jsp");
		dd.forward(req, resp);
		//out.close();
		out.println("h3");//
		return;
    }
	}
    

