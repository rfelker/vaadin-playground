package de.codecentric.testproject;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(heartbeatInterval = 3, productionMode = false, ui = MyVaadinUI.class, widgetset = "de.codecentric.testproject.AppWidgetSet")
public class MyServlet extends VaadinServlet implements SessionInitListener,
		SessionDestroyListener, BootstrapListener {

	private static final long serialVersionUID = -2814562561177596808L;
	private static final Logger LOGGER = Logger.getLogger(MyServlet.class.getName());

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		getService().addSessionInitListener(this);
		getService().addSessionDestroyListener(this);
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {

		LOGGER.info("session destroy, event=" + event);
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		LOGGER.info("session init, event=" + event);

		event.getSession().addBootstrapListener(this);
	}

	@Override
	public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
		LOGGER.info("modifyBootstrapFragment, event=" + response);
	}

	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {
		LOGGER.info("modifyBootstrapPage, event=" + response);
		LOGGER.info("modifyBootstrapPage, document=" + response.getDocument());
	}
}
