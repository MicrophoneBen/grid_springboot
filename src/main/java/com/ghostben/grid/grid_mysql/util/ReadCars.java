package com.ghostben.grid.grid_mysql.util;

import com.ghostben.grid.grid_mysql.entity.Cars;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadCars {
    public static List<Cars> read()  {
        String url = "jdbc:mysql://localhost:3306/vaadin?useSSL=false";

        SimpleDriverDataSource ds = new SimpleDriverDataSource();

        try {
            ds.setDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(ReadCars.class.getName()).log(Level.SEVERE, null, ex);
        }

        ds.setUsername("root");
        ds.setPassword("123456");
        ds.setUrl(url);

        String sql = "SELECT * FROM Cars";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        List<Cars> cars = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Cars.class));

        return cars;
    }
}
