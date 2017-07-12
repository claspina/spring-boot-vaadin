package net.claspina.demo.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import net.claspina.demo.app.ApplicationConstant;
import net.claspina.demo.app.security.SecurityUtils;
import net.claspina.demo.ui.view.AccessDeniedView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@Theme(ValoTheme.THEME_NAME)
@Title(ApplicationConstant.NOMBRE_SISTEMA)
@Viewport("width=device-width, initial-scale=1")
public class MyUI extends UI {

    @Autowired
    private LoginView loginView;

    @Autowired
    private MainView dashboardView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle(ApplicationConstant.INICIO_SESION);

        setErrorHandler(this::handleError);

        if (SecurityUtils.isLoggedIn()) {
            dashboardView.configure();
            setContent(dashboardView);
        } else {
            showLogin();
        }
    }

    private void showLogin() {
        loginView.configure();
        setContent(loginView);
    }

    private void handleError(com.vaadin.server.ErrorEvent event) {
        Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
        if (t instanceof AccessDeniedException) {

            AccessDeniedException accessDeniedException = (AccessDeniedException) t;
            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);
            //Notification.show("You do not have permission to perform this operation", Notification.Type.WARNING_MESSAGE);
            getUI().getNavigator().navigateTo(AccessDeniedView.NAME);
            return;

        }else if (event.getThrowable().getCause().getCause().getCause() instanceof AccessDeniedException) {

            AccessDeniedException accessDeniedException = (AccessDeniedException) event.getThrowable().getCause().getCause().getCause();
            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);
            //Notification.show("You do not have permission to perform this operation", Notification.Type.WARNING_MESSAGE);
            getUI().getNavigator().navigateTo(AccessDeniedView.NAME);
            return;

        } else {
            DefaultErrorHandler.doDefault(event);
        }
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
