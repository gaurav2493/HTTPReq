import java.util.List;

import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailConnection;
import com.googlecode.gmail4j.GmailMessage;
import com.googlecode.gmail4j.http.HttpGmailConnection;
import com.googlecode.gmail4j.rss.RssGmailClient;
import com.googlecode.gmail4j.util.LoginDialog;


public class GmailAPIImpl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GmailClient client = new RssGmailClient();
		GmailConnection connection = new HttpGmailConnection(LoginDialog.getInstance().show("Enter Gmail Login"));
		client.setConnection(connection);
		final List<GmailMessage> messages = client.getUnreadMessages();
		for (GmailMessage message : messages) {
		    System.out.println(message);
		}
	}

}
