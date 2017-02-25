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

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )

public class HelloServlet extends HttpServlet {
class HotelInformation{
    private String dest,tripDate,ratings,imgPath,description,hotelName,price;
	public HotelInformation(){}
	public String getPrice() {
        return price;
    }
	public void setPrice(String price) {
        this.price = price;
    }
	public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		  sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject readJsonFromUrl(String url) throws IOException, ParseException {
		InputStream is = new URL(url).openStream();
		try {
		  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		  //String jsonText = readAll(rd);
		  JSONParser jsonParser = new JSONParser();
		  JSONObject json = (JSONObject) jsonParser.parse(rd);	
		  //JSONObject json = new JSONObject(jsonText);
		  return json;
		} finally {
		  is.close();
		}
	}

	private static final String filePath = "getOffers.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
			
		PrintWriter out = resp.getWriter();		
		resp.setContentType("text/html"); 
        resp.setCharacterEncoding("UTF-8");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Exp94</title>");
		out.println("</head>");
		out.println("<body>");
	
		try {
			//FileReader reader = new FileReader(filePath);

			//JSONParser jsonParser = new JSONParser();
			//JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);	
			JSONObject jsonObject=readJsonFromUrl("https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel");				
			JSONObject offers = (JSONObject)jsonObject.get("offers");
			JSONArray HotelArray= (JSONArray) offers.get("Hotel");
			int hotelsCount=HotelArray.size();
			
			out.println("<p>fuckkkkkkkkThere is "+hotelsCount+" hotel deal as parsed from JSON API</p>");
			out.println("<br><br><hr>");
			
			List<HotelInformation> list=new ArrayList<HotelInformation>();
			Iterator i = HotelArray.iterator();
			
			int count=0;
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

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	

		out.println("</body>");
		out.println("</html>");
		out.close();
		return;
    }
	}
    

