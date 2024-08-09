/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class Conexion {
    
    /*static Connection conn =null;*/
    public static Connection conectar(){
        /*String url="jdbc:sqlserver://localhost\\DESKTOP-8FOS420\\SQLEXPRESS:1433;databaseName=db_ventas;encrypt=true;trustServerCertificate=true;";*/
       String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=db_ventas;"
                + "user=sa;"
                + "password=1234;"
                + "loginTimeout=30;"
                + "TrustServerCertificate=True;";

        try {
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}