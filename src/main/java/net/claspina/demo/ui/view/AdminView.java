package net.claspina.demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = AdminView.NAME)
public class AdminView extends VerticalLayout implements View {

    public static final String NAME = "admin";

    public AdminView() {
        setMargin(true);
        addComponent(new Label("Admin view"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // NOP
    }
}
