import java.io.IOException;

import java.io.OutputStream;

import java.sql.SQLException;

import org.json.simple.JSONValue;

import com.mysql.jdbc.Connection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class APIHandler implements HttpHandler 
{
	Connection dbConn;
	
	public APIHandler(Connection conn)
	{
		dbConn = conn;
	}
	
	URLFlags flags;
    public void handle(HttpExchange t) throws IOException 
    {
	    System.out.println("Got an API request");
	    //System.out.println(t.getRequestURI());
	    URLParser httpParser = new URLParser(t.getRequestURI().toString());
	    flags = httpParser.parse();
	    /*System.out.println(flags.getLocation());
	    System.out.println(flags.getPm10_actual());
	    System.out.println(flags.getPm10_forecast());
	    System.out.println(flags.getIndex());*/
	    SQLRequest SQLR = new SQLRequest(dbConn, t.getRequestURI().toString(), flags);
	    String response = JSONValue.toJSONString(SQLR.getJsonArray()); 
	    
	    //gestion des erreurs
	    if(flags.getMalformedURL()== true){
	    	t.sendResponseHeaders(400, response.length());
	    }
	    else if(flags.getNotFound()== true){
	    	t.sendResponseHeaders(404, response.length());
    	}
	    else{
	    	t.sendResponseHeaders(200, response.length());
	    }
	    
	    OutputStream os = t.getResponseBody();
	    //os.write(flags.toString().getBytes());
	    os.write(response.getBytes());
	    os.close();
	    
	    
    }
}

