package net.infocentre;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public class FileDownloader {
	
	  protected List<String> cookies;
	  protected HttpURLConnection conn;
	  private String downloadUrl;
	  private final String USER_AGENT = "Mozilla/5.0";
	  
	public FileDownloader(String id) {
		this.setCookies(cookies);
		this.downloadUrl="http://210.212.85.155/file/download.php?id="+id;		
	}
	  public boolean saveDownloadTo(String path)
	  {
		  GetPageContent(downloadUrl,path);
		  return true;
	  }	
	  private String GetPageContent(String url,String path) {
		  
			URL obj = null;
			try {
				obj = new URL(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn = (HttpURLConnection) obj.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			// default is GET
			try {
				conn.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			conn.setUseCaches(false);
		 
			// act like a browser
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			if (cookies != null) {
				for (String cookie : this.cookies) {
					conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
				}
			}
			byte[] bytes=null;
			String filename=null;
			try {
				Map<String, List<String>> map=conn.getHeaderFields();
			//	Content-Disposition: attachment; filename=NOTICE_b4_external.pdf;
				filename=map.get("Content-Disposition").get(0);
				bytes = IOUtils.toByteArray(conn.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Get the response cookies
			setCookies(conn.getHeaderFields().get("Set-Cookie"));
		 try {
			Files.write(Paths.get(path+filename.substring(21,filename.length()-1)), bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
		 
		  }
		 
		  public List<String> getCookies() {
			return cookies;
		  }
		 
		  public void setCookies(List<String> cookies) {
			this.cookies = cookies;
		  }
		  

}
