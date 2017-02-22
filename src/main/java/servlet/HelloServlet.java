package servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.*;
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
        ServletOutputStream out = resp.getOutputStream();
		//File file=null;
		try {

			out.println("h1");
			 //file = new File("C:\\Users\\haderk\\Desktop\\Expedia94\\test\\src\\main\\java\\servlet\\getOffers.json");
			JSONObject obj = new JSONObject("{interests : [{interestKey:Dogs}, {interestKey:Cats}]}");
			List<String> list = new ArrayList<String>();
			JSONArray array = obj.getJSONArray("interests");
			for(int i = 0 ; i < array.length() ; i++){
				list.add(array.getJSONObject(i).getString("interestKey"));
				out.write(i.toString());
			}

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	
	out.write("h2".getBytes());

        //out.write("hello heroku2".getBytes());
        out.flush();
        out.close();
    }
	/* public void parseJson(JSONObject jsonObject ,ServletOutputStream out) throws ParseException {

    Set<Object> set = jsonObject.keySet();
    Iterator<Object> iterator = set.iterator();
    while (iterator.hasNext()) {
        Object obj = iterator.next();
        if (jsonObject.get(obj) instanceof JSONArray) {
            out.println(obj.toString());
            getArray(jsonObject.get(obj));
        } else {
            if (jsonObject.get(obj) instanceof JSONObject) {
                parseJson((JSONObject) jsonObject.get(obj));
            } else {
                out.println(obj.toString() + "\t"
                        + jsonObject.get(obj));
				}
			}
		}
	} */
    
}
