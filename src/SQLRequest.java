import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SQLRequest
{

	JSONArray jsonArray = new JSONArray();
	
	public SQLRequest(Connection conn, String path, URLFlags flags) {
	
		try
		{
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs;
			String base = "SELECT * FROM air_quality_local";
			String location_selector = " WHERE location=?";
			String single_limiter = " ORDER BY date DESC LIMIT ?,1" ;
			String limiter = " ORDER BY date DESC LIMIT 18";
			//String path = "api.ganymede.network/apiv1/75";
			List<String> tree = new ArrayList<String>();			
			tree = Arrays.asList(path.split("/"));
			int listLength = 0;
			String query = "";
			PreparedStatement selector = conn.prepareStatement(query);
			
			listLength = tree.size();
			//System.out.println(listLength);
			
			if(listLength == 2) {
				query = base + limiter;
				selector = conn.prepareStatement(query);
			}		
			
			if(listLength==3 || listLength==4 || listLength==5){
				query = base + location_selector + limiter;
				selector = conn.prepareStatement(query);
				try{
					selector.setInt(1, Integer.parseInt(tree.get(2))); //selection de location
				}catch (Exception e){
						System.out.println(e);
				}
			}
			
			if(listLength==6){
				query = base + location_selector + single_limiter;
				selector = conn.prepareStatement(query);
				selector.setInt(1, Integer.parseInt(tree.get(2))); //selection de location
				selector.setInt(2, Integer.parseInt(tree.get(5))); //selection de la nième "demi journee" avant maintenant
			}
			
			rs= selector.executeQuery();
			
			
			SQLToJSON json = new SQLToJSON(rs, flags, listLength);
			jsonArray = json.getJsonArray();
			
			rs.close();
			stmt.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public JSONArray getJsonArray()
	{
		return jsonArray;
	}

}
