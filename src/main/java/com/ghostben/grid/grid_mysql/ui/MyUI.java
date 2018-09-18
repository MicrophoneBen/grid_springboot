package com.ghostben.grid.grid_mysql.ui;


import com.ghostben.grid.grid_mysql.entity.Cars;
import com.ghostben.grid.grid_mysql.util.ReadCars;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.GridRowDragger;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@Theme("mytheme")
@SpringUI
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final List<Cars> carslist = ReadCars.read();
        final ListDataProvider<Cars> carsListDataProvider = new ListDataProvider<>(carslist);
        //初始化Grid容器
        Grid<Cars> grid = new Grid<>();

        VerticalLayout vbox = new VerticalLayout();

        HorizontalLayout hbox = new HorizontalLayout();


        Button loadButton = new Button("Load data");
        loadButton.addClickListener(e -> {
            grid.setDataProvider(carsListDataProvider);
            grid.addColumn(Cars::getId).setCaption("车编号");
            grid.addColumn(Cars::getName).setCaption("车名");
            grid.addColumn(Cars::getPrice).setCaption("车价");

            grid.getDataProvider().refreshAll();

        });
        Button clearButton = new Button("Clear data");
        clearButton.addClickListener(e -> {
           grid.removeAllColumns();
        });


        new GridRowDragger<>(grid);
        vbox.addComponents(grid);
        hbox.addComponents(loadButton, clearButton);
        hbox.setSpacing(true);
        vbox.addComponent(hbox);
        vbox.setMargin(true);
        vbox.setSpacing(true);

        setContent(vbox);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
