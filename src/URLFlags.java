public class URLFlags
{
	int location = -1;
	int index = -1;
	boolean global_actual = false;
	boolean global_forecast = false;
	boolean no2_actual = false;
	boolean no2_forecast = false;
	boolean o3_actual = false;
	boolean o3_forecast = false;
	boolean pm10_actual = false;
	boolean pm10_forecast = false;
	boolean malformedURL = false;
	boolean notFound = false;
	
	public int getLocation()
	{
		return location;
	}
	public void setLocation(int loc)
	{
		location = loc;
	}
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int i)
	{
		index = i;
	}
	public boolean getGlobal_actual()
	{
		return global_actual;
	}
	public void setGlobal_actual(boolean ga)
	{
		global_actual = ga;
	}
	public boolean getGlobal_forecast()
	{
		return global_forecast;
	}
	public void setGlobal_forecast(boolean gf)
	{
		global_forecast = gf;
	}
	public boolean getNo2_actual()
	{
		return no2_actual;
	}
	public void setNo2_actual(boolean na)
	{
		no2_actual = na;
	}
	public boolean getNo2_forecast()
	{
		return no2_forecast;
	}
	public void setNo2_forecast(boolean nf)
	{
		no2_forecast = nf;
	}
	public boolean getO3_actual()
	{
		return o3_actual;
	}
	public void setO3_actual(boolean oa)
	{
		o3_actual = oa;
	}
	public boolean getO3_forecast()
	{
		return o3_forecast;
	}
	public void setO3_forecast(boolean of)
	{
		o3_forecast = of;
	}
	public boolean getPm10_actual()
	{
		return pm10_actual;
	}
	public void setPm10_actual(boolean pa)
	{
		pm10_actual = pa;
	}
	public boolean getPm10_forecast()
	{
		return pm10_forecast;
	}
	public void setPm10_forecast(boolean pf)
	{
		pm10_forecast = pf;
	}
	public boolean getMalformedURL()
	{
		return malformedURL;
	}
	public void setMalformedURL(boolean mfURL)
	{
		malformedURL = mfURL;
	}
	public boolean getNotFound()
	{
		return notFound;
	}
	public void setNotFound(boolean nf)
	{
		notFound = nf;
	}
	
	
}
