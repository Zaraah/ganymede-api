//import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URLParser
{
	String path;
	URLFlags flags;
	
	public URLParser(String pPath)
	{
		path = pPath;
		flags = new URLFlags();
	}
	
	public URLFlags parse()
	{
		List<String> tree = new ArrayList<String>();
		tree = Arrays.asList(path.split("/"));
		int listLength = tree.size();	
		int location=-2;
		int index;
				
		if(listLength > 6)
		{
			flags.setMalformedURL(true);
			return flags;
		}			
		if(tree.get(1).equals("APIv1"))
		{
			if(listLength >= 3)
			{
				try
				{
					location = Integer.parseInt(tree.get(2));
					if(location == 0 || location == 75 || location == 77 || location == 78 || (location >= 91 && location<=95) )
						flags.setLocation(location);					
					else 
					{
						flags.setNotFound(true);
						return flags;
					}	
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("integer error");
					flags.setMalformedURL(true);
					return flags;
				}
			}	
			if(listLength == 3 || listLength ==2)
			{
				flags.setNo2_actual(true);
				flags.setO3_actual(true);
				flags.setPm10_actual(true);
				flags.setNo2_forecast(true);
				flags.setO3_forecast(true);
				flags.setPm10_forecast(true);
				flags.setGlobal_actual(true);
				flags.setGlobal_forecast(true);
			}
			if(listLength >= 4)
			{			
					switch(tree.get(3))
					{
						case "no2":
							flags.setNo2_actual(true);
							flags.setNo2_forecast(true);
							break;
						case "o3":
							flags.setO3_actual(true);
							flags.setO3_forecast(true);
							break;
						case "pm10":
							flags.setPm10_actual(true);
							flags.setPm10_forecast(true);
							break;
						case "global":
							flags.setGlobal_actual(true);
							flags.setGlobal_forecast(true);
							break;
						default :
							flags.setMalformedURL(true);
							return flags;
					}	
			}
			if(listLength >= 5)
			{
				switch(tree.get(4))
				{
					case "actual":
						flags.setNo2_forecast(false);
						flags.setO3_forecast(false);
						flags.setPm10_forecast(false);
						flags.setGlobal_forecast(false);
						break;
					case "forecast":
						flags.setNo2_actual(false);
						flags.setO3_actual(false);
						flags.setPm10_actual(false);
						flags.setGlobal_actual(false);
						break;
					default:
						flags.setMalformedURL(true);
						return flags;
				}	
			}
			if(listLength == 6)
			{
				try
				{
					index = Integer.parseInt(tree.get(5));
					if(index <= 15 )
					{
						flags.setIndex(index);
					}
					else
					{
						flags.setMalformedURL(true);
						return flags;
					}	
				}
				catch(NumberFormatException nfe)
				{
					flags.setMalformedURL(true);
					return flags;
				}
			}								
		}
		else
		{	
			flags.setMalformedURL(true);
			return flags;
		}
		return flags;
	}
}
