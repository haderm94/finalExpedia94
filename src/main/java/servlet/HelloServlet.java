package servlet;
//import org.json.simple.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;
//import org.json.*;


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
		//File file=null;
		try {
			out.println("Working Directory = " +
              System.getProperty("user.dir"));
			out.println("h1");
			//file = new File("app/getOffers.json");
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);	
			
			String firstName = (String) jsonObject.get("firstname");
			out.println("The first name is: " + firstName);

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
			/* JSONObject obj = new JSONObject("{interests : [{interestKey:Dogs}, {interestKey:Cats}]}");
			List<String> list = new ArrayList<String>();
			JSONArray array = obj.getJSONArray("interests");
			for(int i = 0 ; i < array.length() ; i++){
				list.add(array.getJSONObject(i).getString("interestKey"));
				out.println(list.get(i));
			} */

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	
	out.println("h2");

       
        out.close();
    }
	
    
}
