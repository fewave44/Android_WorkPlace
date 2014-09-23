package myhttp;

import java.net.*;
import java.io.*;
public class MyHttp {

	private int timeOut = 5000;
	private HttpURLConnection connect;
	private int statCode = 0;
	public MyHttp()
	{
		
	}
	public String readHttpResponseFromGet(String url) throws Exception
	{
		String respondStr = "";
		if(!url.startsWith("http://"))
		{
			url += "http://" + url;
		}
		URL u = new URL(url);
		connect = (HttpURLConnection) u.openConnection();
		connect.setConnectTimeout(2000);
		connect.connect();
		
		statCode = connect.getResponseCode();
		BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream(),"utf-8"));
		String line ;
		while((line = br.readLine()) != null)
		{
			respondStr += line;
		}
		br.close();
		connect.disconnect();
		return respondStr;	
	}
	
	public String readHttpResponseFromPost(String url, String msg) throws Exception
	{
		String respondStr = ""; 
		if(!url.startsWith("http://"))
		{
			url += "http://" + url;
		}
		URL u = new URL(url);
		connect = (HttpURLConnection) u.openConnection();
		
		connect.setDoOutput(true);
		connect.setConnectTimeout(timeOut);
		connect.setDoInput(true);
		connect.setInstanceFollowRedirects(true);
		connect.setUseCaches(false);
		connect.setRequestMethod("POST");
		connect.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

		DataOutputStream dos = new DataOutputStream(connect.getOutputStream());
		dos.writeBytes(msg);		
		dos.flush();
		statCode = connect.getResponseCode();
		dos.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream(),"utf-8"));
		
		String line;
		while((line = br.readLine()) != null)
		{
			respondStr += line;
		}
		int right = respondStr.lastIndexOf("\"");
		int left = respondStr.lastIndexOf("\"", right - 1);
		return respondStr.substring(left + 1, right);
		
	}
	public int getResponseCode()
	{
		return statCode;
	}
}
