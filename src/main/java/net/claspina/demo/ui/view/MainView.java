package net.claspina.demo.ui.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemusa.sidemenu.SideMenu;
import net.claspina.demo.app.ApplicationConstant;
import net.claspina.demo.app.security.SecurityUtils;

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
        navigator.navigateTo(AdminView.NAME);

        sideMenu.setMenuCaption(ApplicationConstant.NOMBRE_SISTEMA);

        addDivision("Inicio");
        addView("Facturar", VaadinIcons.MONEY, AdminView.NAME, AdminView.class);
        addView("usuario", VaadinIcons.USER, UserView.NAME, UserView.class);
        addView("Admin", VaadinIcons.LOCK, AdminView.NAME, AdminView.class);

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
        sideMenu.addUserMenuItem("Settings", VaadinIcons.WRENCH, () -> {
            Notification.show("Showing settings", Notification.Type.TRAY_NOTIFICATION);
        });
        sideMenu.addUserMenuItem("Sign out", () -> {
            Notification.show("Logging out..", Notification.Type.TRAY_NOTIFICATION);
            logout();

        });

        sideMenu.addUserMenuItem("Hide logo", () -> {
            if (!logoVisible) {
                sideMenu.setMenuCaption(menuCaption, VaadinIcons.USER);
            } else {
                sideMenu.setMenuCaption(menuCaption);
            }
            logoVisible = !logoVisible;
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
