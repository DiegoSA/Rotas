package com.example.diego.rotas.banco;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by DBlac on 21/05/2017.
 */

public class ConnectOracle {
    private Connection conn;
    private Statement stmt;
    public ConnectOracle() throws ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@192.168.25.200:15210/oracle";
            this.conn = DriverManager.getConnection(url,"DIEGO","diego123");
            this.conn.setAutoCommit(false);
            this.stmt = this.conn.createStatement();
        } catch(SQLException e) {
            Log.d("tag", e.getMessage());
        }
    }
    public ResultSet getResult() throws SQLException {
        ResultSet rset = stmt.executeQuery("select * from PEDIDO;");
        stmt.close();
        return rset;
    }

}
