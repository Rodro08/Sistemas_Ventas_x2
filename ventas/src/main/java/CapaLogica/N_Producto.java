/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class N_Producto {
    private Conexion SQL = new Conexion();
    private Connection cn = SQL.conectar();
    String sql="";
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String[] titulos={"ID","Serie","Nombre","Fecha Ingreso","Fecha Vencimiento","Precio Compra","Precio Venta","Cantidad","ID Cat","Categoria"};
        modelo=new DefaultTableModel(null,titulos);
        String[] registro =new String[10];
        
        sql=("exec sp_buscar_cliente '"+ buscar+"'");
        try{
            Statement st =cn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            
            while(rs.next()){
                registro[0]=rs.getString(1);
                registro[1]=rs.getString(2);
                registro[2]=rs.getString(3);
                registro[3]=rs.getString(4);
                registro[4]=rs.getString(5);
                registro[5]=rs.getString(6);
                registro[6]=rs.getString(7);
                registro[7]=rs.getString(8);
                registro[8]=rs.getString(9);
                modelo.addRow(registro);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
}
