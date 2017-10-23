package com.di.mergeo.validator;

import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EndpointValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

        EndpointModel endpoint = (EndpointModel) o;
        Statement st;

        List<String> namelist = new ArrayList<String>();
        String[] names = { "endpoint","sextant","geotriples","index","info","strabon-endpoint-3.3.2-SNAPSHOT","SextantOL3","endpoint_default" };
        namelist.addAll( Arrays.asList(names) );

        try {
            Connection con = PostgreSQLJDBC.dbConnect("endpoint", "postgres", "postgres");
            st = con.createStatement();

            String query = "SELECT 1 FROM pg_roles WHERE rolname='" + endpoint.getUsername()+ "'";

            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(1));
                errors.rejectValue("username", "error.username");
            }

            query = "SELECT 1 from pg_database where datname='" + endpoint.getDbname()+ "'";

            rs = st.executeQuery(query);
            while (rs.next())
            {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(1));
                errors.rejectValue("dbname", "error.dbname");
            }

            if( endpoint.getEndpointname().contains(" ") || namelist.contains(endpoint.getEndpointname())){
                errors.rejectValue("endpointname", "error.endpointname");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
