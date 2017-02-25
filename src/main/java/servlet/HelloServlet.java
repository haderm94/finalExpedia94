/**
 * @author mohammad.k.hader on 2/24/2016.
 * The class (Controller) that controls request and prepare response 
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
	
		try {
				
			JSONObject jsonObject=hotelServices.readJsonFromUrl("https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel");				
			JSONObject offers = (JSONObject)jsonObject.get("offers");
			JSONArray HotelArray= (JSONArray) offers.get("Hotel");
			
			hotelServices.display(out,HotelArray.size(),hotelServices.allHotels(HotelArray));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return;
	}
}
    

