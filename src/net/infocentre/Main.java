package net.infocentre;

import java.util.List;
import java.util.Scanner;

public class Main {
	
	  public static void main(String[] args) throws Exception{
		  		  
			InfoAuthenticator.authenticate("11it056","9013669956");
			//System.out.print(InfoAuthenticator.validate("11it056","9013669956"));
			printNotices();
			//printANotice();
			FileDownloader fd=new FileDownloader(10691);
			fd.saveDownloadTo("D:\\");
			//printFiles();
			
			//System.out.print(InfoAuthenticator.validate("",""));
		
	  }	  


static void printNotices()
{
	ViewNoticesPageReader http = new ViewNoticesPageReader();
	List<String[]> list=http.parseContents(http.GetPageContent(http.getLink(1)));
	for (int i=0;i<list.size();i++)
	{
		String[] myarr=list.get(i);
		for (int j=0;j<myarr.length;j++)
		{
			System.out.print(myarr[j]+"  ");
		}
		System.out.println();
	}
  }
static void printANotice()
{
	NoticeExtractor http=new NoticeExtractor();
	int noticeId=0;
	Scanner sc = new Scanner(System.in);
	while(true)
	{
		System.out.println("Enter Notice ID to view Notice");
		noticeId=5462;
		String result = http.GetPageContent(http.getLink(noticeId));
		http.parseContents(result);
		break;
	}
	sc.close();
  }
static void printFiles()
{
	ViewFilesPageReader pr=new ViewFilesPageReader();
	List<String[]> list=pr.parseContents(pr.GetPageContent(pr.getLink(1)));
	for (int i=0;i<list.size();i++)
	{
		String[] myarr=list.get(i);
		for (int j=0;j<myarr.length;j++)
		{
			System.out.print(myarr[j]+"  ");
		}
		System.out.println();
	}
}
}

