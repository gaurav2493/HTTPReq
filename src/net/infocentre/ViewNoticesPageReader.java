package net.infocentre;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class ViewNoticesPageReader extends PageReader {
 
	protected static String noticeLink = "http://210.212.85.155/notice/notices.php";
	
	public ViewNoticesPageReader()
	{		
	}
 
  @Override
  protected void parseContents(String htmlTableContent)
  {

	  Document doc = Jsoup.parse(htmlTableContent);
	  int i=0;
		Element loginform = doc.getElementsByTag("table").get(4);
		System.out.println("Date\tSubject\tPostedBy\tlink");
		Elements inputElements = loginform.getElementsByTag("td");
		for (Element inputElement : inputElements) {
			
			String data = inputElement.html();
			
			if(++i==4)
			{
				String link= inputElement.getElementsByTag("a").first().attr("href");
				System.out.println(link);
				i=0;
			}
			else
				System.out.print(data+"\t");
			
		}
  }

@Override
protected String getLink(int pageID) {
	return noticeLink+"?start="+(pageID-1)*15;
} 


 
}
