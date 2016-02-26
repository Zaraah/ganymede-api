import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;;

public class HTTPServer 
{
	public static void main(String[] args) throws Exception 
	{
		String fileName = "api.conf";
        //String line = null;
        MysqlDataSource dataSource = new MysqlDataSource();
        int port = 8000;
        
        //Set MysqlConnection
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            /*while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            } */ 
            
        	/*FileWriter fileWriter = new FileWriter(fileName);
        	fileWriter.write("user\npassword\naddress\ndatabase");
        	fileWriter.close();*/
        	
    	    dataSource.setUser(bufferedReader.readLine());
    		dataSource.setPassword(bufferedReader.readLine());
    		dataSource.setServerName(bufferedReader.readLine());
    		dataSource.setDatabaseName(bufferedReader.readLine());            
            
    		//String test = bufferedReader.readLine();
    		//System.out.println("port :" + test);
    		port = Integer.parseInt(bufferedReader.readLine());
    		
            bufferedReader.close();    
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to find file '" + fileName + "', creating a default configuration file.");   
            FileWriter fileWriter = new FileWriter(fileName);
        	fileWriter.write("user\npassword\naddress\ndatabase\n8000");
        	fileWriter.close();            
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
            // ex.printStackTrace();
        }
		
		//Start MysqlConnection
        try{
        	Connection conn = (Connection) dataSource.getConnection();
        
		//Set HTTP server
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
	    server.createContext("/", new APIHandler(conn));
	    server.setExecutor(null);
	    
	    //Start server		
	    System.out.println("Starting server");
	    server.start();
	    
        }
        
        catch(CommunicationsException e)
        {
        		System.out.println("Unable to reach DB server");
        		e.printStackTrace();
        		return;
        }
        
        catch(SQLException e)
        {
        	System.out.println("SQL request error");
        	e.printStackTrace();
        }
	    
	}
}
