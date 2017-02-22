package servlet;

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
		JSONObject obj = new JSONObject("{interests : [{interestKey:Dogs}, {interestKey:Cats}]}");

List<String> list = new ArrayList<String>();
JSONArray array = obj.getJSONArray("interests");
for(int i = 0 ; i < array.length() ; i++){
    list.add(array.getJSONObject(i).getString("interestKey"));
}
        out.write("hello heroku2".getBytes());
        out.flush();
        out.close();
    }
    
}
