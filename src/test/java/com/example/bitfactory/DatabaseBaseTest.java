package com.example.bitfactory;


import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.testcontainers.containers.MySQLContainer;

public abstract class DatabaseBaseTest {
    @ClassRule
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7")
            .withInitScript("db/init.sql");

    /**
     * 设置数据库连接属性
     */
    @BeforeClass
    public static void init() {
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.driver-class-name", mysql.getDriverClassName());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }
}
