package de.codecentric.testproject;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;

public class MySystemMessageProvider implements SystemMessagesProvider {

	private static final long serialVersionUID = -4026964328558816759L;

	@Override
	public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
		CustomizedSystemMessages messages = new CustomizedSystemMessages();

		messages.setSessionExpiredCaption("Your Session is expired");
		messages.setSessionExpiredMessage("You should use this fantastic application much often!");
		messages.setSessionExpiredNotificationEnabled(true);
		messages.setSessionExpiredURL("http://www.codecentric.de/");
		return messages;
	}

}
