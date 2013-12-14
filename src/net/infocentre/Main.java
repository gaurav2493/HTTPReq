package net.infocentre;

import java.util.List;
import java.util.Scanner;

public class Main {
	
	  public static void main(String[] args) throws Exception{
		  		  
			List<String> cookies=InfoAuthenticator.getCookies();
			//printNotices(cookies);
			printANotice(cookies);
		
	  }	  


static void printNotices(List<String> cookies)
{
	ViewNoticesPageReader http = new ViewNoticesPageReader();
	http.setCookies(cookies);
	int pageno=0;
	Scanner sc = new Scanner(System.in);
	while(true)
	{
		//System.out.println("Enter page no or 0 to quit");
		pageno=1;
		if(pageno==0)
			break;
		else
		{
			String result = http.GetPageContent(http.getLink(pageno));
			//System.out.println(result);
			http.parseContents(result);
		}
		break;
	}
	sc.close();
  }
static void printANotice(List<String> cookies)
{
	NoticeExtractor http=new NoticeExtractor();
	http.setCookies(cookies);
	int noticeId=0;
	Scanner sc = new Scanner(System.in);
	while(true)
	{
		System.out.println("Enter Notice ID to view Notice");
		noticeId=5467;
		String result = http.GetPageContent(http.getLink(noticeId));
		http.parseContents(result);
		break;
	}
	sc.close();
  }
}

