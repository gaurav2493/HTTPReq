

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
 
public class HTTPRequestHandler {
 
	private final String USER_AGENT = "Mozilla/5.0";
	private String url;
	private String urlParameters;
	private int responseCode;
	private String responseString;
	
	public int getResponseCode() {
		return responseCode;
	}
	public String getResponseString() {
		return responseString;
	}
	public HTTPRequestHandler(String url,String urlParameter) {
		
		this.url=url;
		this.urlParameters=urlParameter;
		
	}
	public HTTPRequestHandler() {
		// TODO Auto-generated constructor stub
	}
 
	public static void main(String[] args) throws Exception {
 
HTTPRequestHandler http = new HTTPRequestHandler("http://www.google.com/search","q=gaurav");
 
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		System.out.println(http.getResponseCode());
 
		System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
		//System.out.println(http.getResponseCode()+"\n"+http.getResponseString());
 
	}
 
	public void sendGet() throws Exception {
		 
		String url =this.url+"?"+this.urlParameters;
 
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		HttpResponse response = client.execute(request);
 
		responseCode=response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		responseString= result.toString();
 
	}
	public void sendPost() throws Exception {
		 
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
 
		// add header
		post.setHeader("User-Agent", USER_AGENT);
 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
 
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
 
		HttpResponse response = client.execute(post);
		responseCode=response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
 
	}
}