package servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;
//import org.json.*;
import javax.json.*;
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
			
		PrintWriter out = resp.getWriter();		
		File file=null;
		try {

			out.println("h1");
			file = new File("C:\\Users\\haderk\\Desktop\\Expedia94\\test\\src\\main\\java\\servlet\\getOffers.json");
			JsonParser jsonParser = new JsonParser();
			JsonObject obj = new JsonObject("{interests : [{interestKey:Dogs}, {interestKey:Cats}]}");
			List<String> list = new ArrayList<String>();
			JsonArray array = obj.getJsonArray("interests");
			for(int i = 0 ; i < array.length() ; i++){
				list.add(array.getJsonArray(i).getString("interestKey"));
				out.println(list.get(i));
			}

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	
	out.println("h2");

       
        out.close();
    }
	
    
}
