package com.formation.app.dao.jdbc;

import com.formation.app.util.ConnectionManager;

import java.sql.Connection;

public abstract class JdbceDao {

    protected Connection connection;
    public JdbceDao() {
        this.connection = ConnectionManager.getConnection();
    }

}
