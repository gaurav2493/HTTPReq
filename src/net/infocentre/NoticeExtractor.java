package net.infocentre;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.apache.commons.lang.StringEscapeUtils;

public class NoticeExtractor extends PageReader {
	

	@Override
	protected List<String[]> parseContents(String htmlTableContent) {
		Document doc = Jsoup.parse(htmlTableContent);
		//  int i=0;
			Element loginform = doc.getElementsByTag("table").get(3);
			
			String notice=loginform.getElementsByTag("p").get(2).html();
			System.out.println(StringEscapeUtils.unescapeHtml(notice));
			return null;
	}

	@Override
	protected String getLink(int noticeId) {
		return "http://210.212.85.155/notice/view_notice.php?id="+noticeId;
	} 

}
