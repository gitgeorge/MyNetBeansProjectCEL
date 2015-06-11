/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice101;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public ResultSet rst;

    public DatabaseConnect() {

        try {

            Class.forName("org.gjt.mm.mysql.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp", "root", "root");

            System.out.println("connection initiliazed");

        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();

            System.out.println("Not Connected...");
        }
    }

    public ArrayList temparature() {
        ArrayList<Temparature> list = new ArrayList<Temparature>();

        try {
            String sql = "select * from temp_table";
            // Create a prepared statement
            stmt = con.createStatement();
            rst = stmt.executeQuery(sql);

            while (rst.next()) {
                
                Temparature tmp = new Temparature();
                tmp.setId(rst.getInt("id"));
                tmp.setCeclcius(rst.getInt("celcius"));
                tmp.setFerenheit(rst.getFloat("farenheit"));
                tmp.setDateModiefied(rst.getString("datemodified"));
                list.add(tmp);
            }

            if (!rst.next()) {

                System.out.println("no records to process");

            }

        } catch (Exception e) {

        }

        return list;

    }

    public void updateTemp(int number,int id) {

        try {
            
            stmt2 = con.createStatement();
            
            String update = "Update temp_table set farenheit = '" + number + "' where id = '" + id + "'";
            
            stmt2.executeUpdate(update);
            
            System.out.println("Record updated with " + number + "\tat id\t " + id);

        } catch (Exception e) {

        }

    }
}
