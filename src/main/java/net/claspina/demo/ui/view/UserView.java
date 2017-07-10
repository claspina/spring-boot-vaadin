package net.claspina.demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

import static net.hdavid.easylayout.L.*;

@UIScope
@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {

    public static final String NAME = "user";

    TextField search = new TextField("Search");
    Grid<DtoKeyVal> grid = new Grid();

    public UserView(){
        List<DtoKeyVal> list = Arrays.asList(
                new DtoKeyVal(1, "2017-07-10"),
                new DtoKeyVal(2, "2017-07-10")
        );

        grid.setItems(list);
        grid.addColumn(DtoKeyVal::getId).setCaption("ID");
        grid.addColumn(DtoKeyVal::getVal).setCaption("Value");

        ve(this, _MARGIN, _FULL_SIZE,
                    search, Alignment.TOP_LEFT,
                    grid, _FULL_SIZE, _EXPAND);

//        setMargin(true);
//        addComponent(new Label("User view"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DtoKeyVal {
        private long id;
        private String val;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // NOP
    }
}
