package de.codecentric.testproject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ClientConnector.AttachListener;
import com.vaadin.server.ClientConnector.DetachListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class MyVaadinUI extends UI implements DetachListener, AttachListener {
	private static final int TAB_COUNT = 8;
	final private static Logger LOGGER = Logger.getLogger(MyVaadinUI.class.getName());
	final private List<VerticalLayout> tabList = new ArrayList<VerticalLayout>();;

	@Override
	protected void init(VaadinRequest request) {
		addDetachListener(this);
		addAttachListener(this);

		final HorizontalLayout rootLayout = new HorizontalLayout();
		rootLayout.setMargin(false);
		rootLayout.setSizeFull();
		setContent(rootLayout);

		final VerticalLayout leftMenu = new VerticalLayout();
		createDummyLeftMenuEntries(leftMenu);
		leftMenu.setWidth(200, Unit.PIXELS);
		leftMenu.setHeight(100, Unit.PERCENTAGE);
		leftMenu.setMargin(false);
		rootLayout.addComponent(leftMenu);

		final VerticalLayout rightArea = new VerticalLayout();
		rightArea.setMargin(false);
		rightArea.setSizeFull();
		rootLayout.addComponent(rightArea);
		rootLayout.setExpandRatio(rightArea, 1);

		TabSheet tabSheet = new TabSheet();
		createDummyTabs(tabSheet);
		// tabSheet.setSizeFull();

		rightArea.addComponent(tabSheet);
		rightArea.setExpandRatio(tabSheet, 1);
		rightArea.addComponent(createFooter());

	}

	private void createDummyLeftMenuEntries(final VerticalLayout leftMenu) {

		for (int i = 0; i < TAB_COUNT; i++) {

			Button button = new Button("Click Me " + (i + 1));
			button.setData(i);

			button.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					getTabList().get((Integer) event.getButton().getData()).addComponent(
							new Label("Thank you for clicking"));
				}
			});
			leftMenu.addComponent(button);
			leftMenu.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		}

		Button logout = new Button("Logout");
		logout.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getPage().setLocation("/testproject/logout.html");
				getSession().close();
			}
		});
		leftMenu.addComponent(logout);
		leftMenu.setComponentAlignment(logout, Alignment.MIDDLE_CENTER);
	}

	private void createDummyTabs(TabSheet tabSheet) {
		for (int i = 0; i < TAB_COUNT; i++) {
			final VerticalLayout tabLayout = new VerticalLayout(new Label("Content " + (i + 1)));
			getTabList().add(tabLayout);
			tabLayout.setMargin(false);
			tabLayout.setSizeFull();
			tabSheet.addTab(tabLayout, "Tab " + (i + 1));
		}
	}

	private Component createFooter() {
		HorizontalLayout footerLayout = new HorizontalLayout();
		footerLayout.setMargin(false);
		footerLayout.setSizeFull();
		footerLayout.setHeight(30, Unit.PIXELS);

		HorizontalLayout linkLayout = new HorizontalLayout();
		linkLayout.setSizeFull();
		linkLayout.setMargin(false);
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

		return footerLayout;
	}

	@Override
	public void detach(DetachEvent event) {
		LOGGER.info("UI detach, event=" + event);
	}

	@Override
	public void attach(AttachEvent event) {
		LOGGER.info("UI attach, event=" + event);

	}

	public List<VerticalLayout> getTabList() {
		return tabList;
	}
}
