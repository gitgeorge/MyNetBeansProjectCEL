/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author george
 */
public class DatabaseConnect {

    public Connection con;
    public Statement stmt, stmt2;
    public ResultSet rst, rst2;

    public DatabaseConnect() {

        try {

            Class.forName("org.gjt.mm.mysql.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/people", "root", "root");

            System.out.println("connection initiliazed");

        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();

            System.out.println("Not Connected...");
        }
    }

    public ArrayList fetchPeoples() {
        ArrayList<PersonDetail> list = new ArrayList<PersonDetail>();

        try {
            String sql = " select * from people p inner join addresses a using(personID)";
            // Create a prepared statement
            stmt = con.createStatement();
            rst = stmt.executeQuery(sql);

            System.out.println("fetching data and adding to Arraylist");

            while (rst.next()) {

                PersonDetail person = new PersonDetail();
                person.setPersonID(rst.getInt("personID"));
                person.setFirstName(rst.getString("firstaName"));
                person.setLastName(rst.getString("lastName"));
                person.setAddressID(rst.getInt("addressID"));
                person.setAdrress(rst.getString("address"));
                person.setPostalCode(rst.getString("postalCode"));
                person.setMobileNUmber(rst.getString("mobileNumber"));

                list.add(person);

            }

            System.out.println("data fetched.......");

            if (!rst.next()) {

                System.out.println("no records to process");

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return list;

    }

    public void insertMultipleReocords() {

        try {

            RandomString msr = new RandomString();

            RandomNumbers ms = new RandomNumbers();

            for (int i = 1; i <= 5000; i++) {

                // the mysql insert statement
                String query = " insert into people (firstaName, lastName)"
                        + " values (?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                preparedStmt.setString(1, msr.generateRandomString());
                preparedStmt.setString(2, msr.generateRandomString());

                // execute the preparedstatement
                preparedStmt.execute();

                ResultSet rs = preparedStmt.getGeneratedKeys();

                int last_inserted_id = 0;

                if (rs.next()) {
                    last_inserted_id = rs.getInt(1);
                }

                System.out.println("" + last_inserted_id);

                String query2 = " insert into addresses (address, postalCode,mobileNumber,personID)"
                        + " values (?, ?,?,?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt2 = con.prepareStatement(query2);

                preparedStmt2.setString(1, ms.generateRandomString());

                preparedStmt2.setString(2, ms.generateRandomString());

                preparedStmt2.setString(3, ms.generateRandomString());

                preparedStmt2.setString(4, "" + last_inserted_id);

                // execute the preparedstatement
                preparedStmt2.execute();

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

}
