package com.di.mergeo;

import com.di.mergeo.model.EndpointModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {

    public static void dbConnect(String dbname, String user, String pass) throws ClassNotFoundException, SQLException {

        String jdbcURI = "jdbc:postgresql://localhost:5432/" + dbname;
//        String username = "postgres";
//        String password = "postgres";

        Connection c = null;

        Class.forName("org.postgresql.Driver");

        c = DriverManager.getConnection(jdbcURI, user, pass);

        c.createStatement();
        System.out.println("Database successfully opened :)");

        c.close();
    }

    /******************************************************************************************************************/
    public static void dbCreate(EndpointModel model) throws ClassNotFoundException, SQLException {

        /* TODO - Assuming these are the defaults */
        String jdbcURI = "jdbc:postgresql://localhost:5432/template1";
        String username = "postgres";
        String password = "postgres";

        Connection con = null;
        Statement st = null;

        Class.forName("org.postgresql.Driver");

        con = DriverManager.getConnection(jdbcURI, username, password);

        System.out.println("Database successfully opened :)");

        st = con.createStatement();

        String query = "CREATE USER " + model.getUsername() + " WITH PASSWORD '"+ model.getPassword()+"' CREATEDB LOGIN";
        st.executeUpdate(query);

        query = "CREATE DATABASE " + model.getDbname() + " TEMPLATE template_postgis";
        st.executeUpdate(query);

        query="GRANT ALL PRIVILEGES ON DATABASE " + model.getDbname() +" TO " + model.getUsername();
        st.executeUpdate(query);

        query="ALTER DATABASE " + model.getDbname() + " OWNER TO " + model.getUsername();
        st.executeUpdate(query);

        st.close();
        con.close();
    }
}
