package com.di.mergeo.validator;

import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EndpointValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

        EndpointModel endpoint = (EndpointModel) o;
        Statement st;

        if(endpoint.getEndpointname() == null) {
            errors.rejectValue("DataBase Username:", "error X");
        }

        try {
            Connection con = PostgreSQLJDBC.dbConnect("endpoint", "postgres", "postgres");
            st = con.createStatement();

            String query = "SELECT 1 FROM pg_roles WHERE rolname='" + endpoint.getUsername()+ "'";

            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));
                errors.rejectValue("username", "error XXX");
            }

            query = "SELECT 1 from pg_database where datname='" + endpoint.getDbname()+ "'";

            rs = st.executeQuery(query);
            while (rs.next())
            {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));
                errors.rejectValue("dbname", "error XXX");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
