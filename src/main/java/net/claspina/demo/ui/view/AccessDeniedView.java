package net.claspina.demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

//@Component // No SpringView annotation because this view can not be navigated to
@UIScope
@SpringView(name = AccessDeniedView.NAME)
public class AccessDeniedView extends VerticalLayout implements View {

    public static final String NAME = "access-denied";

    public AccessDeniedView() {
        setMargin(true);
        Label lbl = new Label("You don't have access to this view.");
        lbl.addStyleName(ValoTheme.LABEL_FAILURE);
        lbl.setSizeUndefined();
        addComponent(lbl);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
