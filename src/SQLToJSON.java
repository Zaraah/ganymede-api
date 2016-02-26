import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.jdbc.Connection;

public class SQLToJSON
{
	JSONArray jsonArray = new JSONArray();
	
	public SQLToJSON(ResultSet rs, URLFlags flags, int listLength) {
		
		//JSONArray jsonArray = new JSONArray();
		
		try
		{
			while(rs.next())
			{
				//rs.getDate("date").toString() + " " + rs.getTime("date").toString()
				//System.out.println(rs.toString());
				JSONObject jsonObject = new JSONObject();
				String date = rs.getDate("date").toString() + " " + rs.getTime("date").toString();
				jsonObject.put("date", date);
				
				if(listLength == 2)
				{
					jsonObject.put("location", rs.getInt("location"));
				}
				else
				{
					jsonObject.put("location", flags.getLocation());
				}
				
				if(flags.pm10_actual==true)
					jsonObject.put("pm10_actual", rs.getInt("pm10_actual"));
				if(flags.pm10_forecast==true)
					jsonObject.put("pm10_forecast", rs.getInt("pm10_forecast"));
				if(flags.no2_actual==true)
					jsonObject.put("no2_actual", rs.getInt("no2_actual"));
				if(flags.no2_forecast==true)
					jsonObject.put("no2_forecast", rs.getInt("no2_forecast"));
				if(flags.o3_actual==true)
					jsonObject.put("o3_actual", rs.getInt("o3_actual"));
				if(flags.o3_forecast==true)
					jsonObject.put("o3_forecast", rs.getInt("o3_forecast"));				
				if(flags.global_actual==true)	
					jsonObject.put("global_actual", rs.getInt("global_actual"));
				if(flags.global_forecast==true)
					jsonObject.put("global_forecast", rs.getInt("global_forecast"));
				
				
				jsonArray.add(jsonObject);
			}
			System.out.println(jsonArray);
			
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
