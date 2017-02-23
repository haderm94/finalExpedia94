package servlet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;

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
			
			JSONObject offerInfo = (JSONObject)jsonObject.get("offerInfo");
			String SiteId=(String)offerInfo.get("siteID");
			req.setAttribute("id",SiteId);
			out.println("The SiteId is: " +SiteId );
			out.println("h1");
			
			JSONObject offers = (JSONObject)jsonObject.get("offers");
			JSONArray HotelArray= (JSONArray) offers.get("Hotel");
			
			// take the elements of the json array
			/* for(int i=0; i<HotelArray.size(); i++){
				out.println("The " + i + " element of the array: "+HotelArray.get(i));
			} */
			Iterator i = HotelArray.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				
				JSONObject offerDateRange = (JSONObject)innerObj.get("offerDateRange");
				JSONObject destination = (JSONObject)innerObj.get("destination");
				JSONObject hotelInfo = (JSONObject)innerObj.get("hotelInfo");
				JSONObject hotelPricingInfo = (JSONObject)innerObj.get("hotelPricingInfo");
				JSONObject hotelUrls = (JSONObject)innerObj.get("hotelUrls");
				
				out.println("Destination: "+ destination.get("country") + "-"+ destination.get("city") +
						", Region ID: " + destination.get("regionID"));
				
				out.println("Trip starts at: "+ hotelInfo.get("travelStartDate") + 
						" To " + hotelInfo.get("travelEndDate")+ ". LengthOfStay " + offerDateRange.get("lengthOfStay"));
						
				out.println("hotelStarRating: "+ hotelInfo.get("hotelStarRating") ); 
				out.println(" hotelGuestReviewRating " + hotelInfo.get("hotelGuestReviewRating"));
				out.println();
			}
			/* // get a number from the JSON object
			long id =  (long) jsonObject.get("id");
			System.out.println("The id is: " + id);

			// get an array from the JSON object
			JSONArray lang= (JSONArray) jsonObject.get("languages");
			
			// take the elements of the json array
			for(int i=0; i<lang.size(); i++){
				System.out.println("The " + i + " element of the array: "+lang.get(i));
			}
			Iterator i = lang.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println("language "+ innerObj.get("lang") + 
						" with level " + innerObj.get("knowledge"));
			}
			// handle a structure into the json object
			JSONObject structure = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, name: " + structure.get("name"));
			 */
			

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	
	out.println("h2");

       
        out.close();
		RequestDispatcher reqDispatcher=req.getRequestDispatcher("index.jsp");//forward the same req
        reqDispatcher.forward(req, resp);
    }
	}
    
}
