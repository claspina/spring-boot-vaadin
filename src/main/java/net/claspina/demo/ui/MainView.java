package net.claspina.demo.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import net.claspina.demo.app.ApplicationConstant;
import net.claspina.demo.app.security.SecurityUtils;
import net.claspina.demo.ui.view.AccessDeniedView;
import net.claspina.demo.ui.view.AdminView;
import net.claspina.demo.ui.view.ErrorView;
import net.claspina.demo.ui.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemusa.sidemenu.SideMenu;

import java.lang.reflect.Field;

/**
 * Created by Bruno on 11-Dec-15.
 */
@UIScope
@SpringView(name = MainView.NAME)
public class MainView extends HorizontalLayout implements View {

    public static final String NAME = "main";


    @Autowired
    private SpringViewProvider viewProvider;

    private SideMenu sideMenu;
    private Navigator navigator;
    private String menuCaption = "Menu Principal";
    private boolean logoVisible = false;

    public void configure() {
        setSizeFull();
        addStyleName("outlined");
        setSpacing(false);
        setMargin(false);

        buildSideMenu();

        UI.getCurrent().setContent(this);
    }

    private void buildSideMenu() {
        sideMenu = new SideMenu();
        sideMenu = new SideMenu();

        navigator = new Navigator(UI.getCurrent(), sideMenu);

        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        navigator.addProvider(viewProvider);
        navigator.setErrorView(ErrorView.class);

        UI.getCurrent().setNavigator(navigator);

        navigator.addView(AdminView.NAME, AdminView.class);
        navigator.addView(UserView.NAME, UserView.class);
        navigator.navigateTo(UserView.NAME);

        sideMenu.setMenuCaption(ApplicationConstant.NOMBRE_SISTEMA);
        sideMenu.setResponsive(true);

        addDivision("Inicio");
        addView("Vista usuario", VaadinIcons.USER, UserView.NAME, UserView.class);
        addView("Vista administrador", VaadinIcons.LOCK, AdminView.NAME, AdminView.class);

        setUser(SecurityUtils.getUsername(), VaadinIcons.USER);

        addComponent(sideMenu);
    }

    private void addDivision(String name) {
        try {
            Field fieldMenuItemLayout = sideMenu.getClass().getDeclaredField("menuItemsLayout");
            fieldMenuItemLayout.setAccessible(true);
            CssLayout menuItemLayout = (CssLayout) fieldMenuItemLayout.get(sideMenu);
            menuItemLayout.addComponent(new Label("&nbsp; &nbsp;"+ FontAwesome.BARS.getHtml()+"&nbsp; "+name, ContentMode.HTML));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addView(String menuName, Resource icon, String viewname, Class<? extends View> viewClass) {
        sideMenu.addNavigation(menuName, icon, viewname);
        navigator.addView(viewname, viewClass);
    }

    private void setUser(String name, Resource icon) {
        sideMenu.setUserName(name);
        sideMenu.setUserIcon(icon);

        sideMenu.clearUserMenu();
        sideMenu.addUserMenuItem("Opciones", VaadinIcons.WRENCH, () -> {
            Notification.show("Showing settings", Notification.Type.TRAY_NOTIFICATION);
        });
        sideMenu.addUserMenuItem("Cerrar Sesión", () -> {
            Notification.show("Logging out..", Notification.Type.TRAY_NOTIFICATION);
            logout();

        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


    private void logout() {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
        getSession().close();
    }
}
