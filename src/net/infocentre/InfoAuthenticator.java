package net.infocentre;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class InfoAuthenticator {
	
	private final static String USER_AGENT = "Mozilla/5.0";
	//private final String url = "http://210.212.85.155/login/index.php";
	
	 public static List<String> getCookies() throws Exception {
		 
		    CookieHandler.setDefault(new CookieManager());		 	
		 	String postParams = getFormParams();
		 	List<String> cookies;
			URL obj = new URL("http://210.212.85.155/login/login.php");
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		 
			// Acts like a browser
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Host", "accounts.google.com");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			cookies=new ArrayList<String>();
			for (String cookie : cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Referer", "http://210.212.85.155");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
		 
			conn.setDoOutput(true);
			conn.setDoInput(true);
		 
			// Send post request
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
		 
			/*int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);*/
		 
			BufferedReader in = 
		             new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
		 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// System.out.println(response.toString());
			return cookies;
		 
		  }
	 	private static String getFormParams()
				throws UnsupportedEncodingException {
	 		
	 		String username="11it056";
	 		String password="9013669956";
			return "user_id="+URLEncoder.encode(username, "UTF-8")+"&password="+URLEncoder.encode(password, "UTF-8");
		  }

}
