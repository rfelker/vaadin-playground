package de.codecentric.testproject.ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;

public class Footer extends CustomComponent {

	private static final long serialVersionUID = 1L;

	public Footer() {

		HorizontalLayout footerLayout = new HorizontalLayout();
		footerLayout.setMargin(false);

		HorizontalLayout linkLayout = new HorizontalLayout();
		linkLayout.setSpacing(true);

		footerLayout.addComponent(new Link()); // dummy
		footerLayout.addComponent(linkLayout);
		footerLayout.addComponent(new Link());// dummy

		ExternalResource resource = new ExternalResource("http://www.codecentric.de");
		Link contactLink = new Link("Contact", resource);
		Link aboutLink = new Link("About us", resource);
		Link couLink = new Link("Conditions of Use", resource);

		linkLayout.addComponent(contactLink);
		linkLayout.setComponentAlignment(contactLink, Alignment.MIDDLE_CENTER);
		linkLayout.addComponent(aboutLink);
		linkLayout.setComponentAlignment(aboutLink, Alignment.MIDDLE_CENTER);
		linkLayout.addComponent(couLink);
		linkLayout.setComponentAlignment(couLink, Alignment.MIDDLE_CENTER);

		footerLayout.setSizeUndefined();
		setSizeUndefined();

		setCompositionRoot(footerLayout);

	}

}
