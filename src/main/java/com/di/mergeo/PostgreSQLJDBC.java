package com.di.mergeo;

import com.di.mergeo.model.EndpointModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {

    public static Connection dbConnect(String dbname, String user, String pass) throws ClassNotFoundException, SQLException {

        String jdbcURI = "jdbc:postgresql://localhost:5432/" + dbname;
        Connection c = null;
        Class.forName("org.postgresql.Driver");

        c = DriverManager.getConnection(jdbcURI, user, pass);

        System.out.println("[Status] Database successfully opened");

        // c.close();
        return c;
    }

    /******************************************************************************************************************/
    public static void dbCreate(EndpointModel model) throws ClassNotFoundException, SQLException {

        /* TODO - Assuming these are the defaults */
        String jdbcURI = "jdbc:postgresql://localhost:5432/endpoint";
        String username = "postgres";
        String password = "postgres";

        Connection con = null;
        Statement st = null;

        Class.forName("org.postgresql.Driver");

        con = DriverManager.getConnection(jdbcURI, username, password);

        System.out.println("[Status] Database successfully connected");

        st = con.createStatement();

        String query = "CREATE USER " + model.getUsername() + " WITH PASSWORD '"+ model.getPassword()+"' CREATEDB LOGIN";
        st.executeUpdate(query);

        query = "CREATE DATABASE " + model.getDbname() + " TEMPLATE template_mergeo";
        st.executeUpdate(query);

        query="GRANT ALL PRIVILEGES ON DATABASE " + model.getDbname() +" TO " + model.getUsername();
        st.executeUpdate(query);

        query="ALTER DATABASE " + model.getDbname() + " OWNER TO " + model.getUsername();
        st.executeUpdate(query);

        System.out.println("[Status] Database successfully created");

        st.close();
        con.close();
    }
}
