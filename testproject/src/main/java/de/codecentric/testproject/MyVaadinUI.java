package de.codecentric.testproject;

import java.io.File;
import java.util.logging.Logger;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ClientConnector.AttachListener;
import com.vaadin.server.ClientConnector.DetachListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class MyVaadinUI extends UI implements DetachListener, AttachListener {
	private static final Logger LOGGER = Logger.getLogger(MyVaadinUI.class.getName());

	@Override
	protected void init(VaadinRequest request) {
		addDetachListener(this);
		addAttachListener(this);

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		layout.setCaption("my caption");
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);

		Button logout = new Button("Logout");
		logout.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getPage().setLocation("/testproject/logout.html");
				getSession().close();
			}
		});
		layout.addComponent(logout);

		Page page = Page.getCurrent();

		File baseDirectory = VaadinService.getCurrent().getBaseDirectory();

		if (baseDirectory != null) {
			LOGGER.info("baseDir=" + baseDirectory.getAbsolutePath());
		} else {
			LOGGER.info("baseDir=null");
		}
	}

	@Override
	public void detach(DetachEvent event) {
		LOGGER.info("UI detach, event=" + event);
	}

	@Override
	public void attach(AttachEvent event) {
		LOGGER.info("UI attach, event=" + event);

	}
}
