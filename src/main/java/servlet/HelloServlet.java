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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		HotelServices hotelServices=new HotelServices();//instance of the service class to do all processing
		PrintWriter out = resp.getWriter();		
		
		resp.setContentType("text/html"); 
        resp.setCharacterEncoding("UTF-8");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Exp94</title>");
		out.println("</head>");
		out.println("<body>");
	
		try {
				
			JSONObject jsonObject=hotelServices.readJsonFromUrl("https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel");				
			JSONObject offers = (JSONObject)jsonObject.get("offers");
			JSONArray HotelArray= (JSONArray) offers.get("Hotel");
			int hotelsCount=HotelArray.size();
			req.setAttribute("size",hotelsCount);
			out.println("<p>There is "+hotelsCount+" hotel deal as parsed from JSON API</p>");
			out.println("<br><br><hr>");
			
			List<HotelInformation> list=hotelServices.allHotels(HotelArray);
				
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
    

