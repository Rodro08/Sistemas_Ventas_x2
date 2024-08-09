/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import CapaDatos.M_Cliente;
import java.sql.Connection;

/**
 *
 * @author Rodrigo
 */
public class N_Cliente {
    
    private Conexion SQL = new Conexion();
    private Connection cn =SQL.conectar();
    String sql="";
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String[] titulos={"ID","Nombre","Apellidos","DNI","RUC","Edad","sexo","telefono","direccion"};
        modelo=new DefaultTableModel(null,titulos);
        String[] registro =new String[9];
        
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
    public boolean insertar(M_Cliente dts){
        sql=("insert into clientes values (?,?,?,?,?,?,?,?,?)");
        
        try{
            PreparedStatement pst =cn.prepareStatement(sql);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApellidos());
            pst.setString(4, dts.getDni());
            pst.setString(5, dts.getRuc());
            pst.setInt(6, dts.getEdad());
            pst.setString(7, dts.getSexo());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getDireccion());
            
            int n= pst.executeUpdate();
            
            if(n != 0){
                return true;
            }else{
                return false;
            }
        } catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
      public boolean editar(M_Cliente dts){
        sql=("{call sp_editar_clientes (?,?,?,?,?,?,?,?,?)}");
        
        try{
            PreparedStatement pst =cn.prepareStatement(sql);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApellidos());
            pst.setString(4, dts.getDni());
            pst.setString(5, dts.getRuc());
            pst.setInt(6, dts.getEdad());
            pst.setString(7, dts.getSexo());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getDireccion());
            
            int n= pst.executeUpdate();
            
            if(n != 0){
                return true;
            }else{
                return false;
            }
        } catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
      
      public boolean eliminar(M_Cliente dts){
          sql=("{call sp_eliminar_cliente(?)}");
          
          try {
              PreparedStatement pst = cn.prepareStatement(sql);
              pst.setString(1, dts.getCodigo());
              
              int n = pst.executeUpdate();
              if(n != 0){
                return true;
            }else{
                return false;
            }
          }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
                  
      }
      
      public int gernerarIdCliente(){
          String sql=("select max(idcliente) as id from clientes");
          int cod =0;
          
          try{
              Statement st =cn.createStatement();
              ResultSet rs=st.executeQuery(sql);
              
              if(rs.next()){
                  cod=rs.getInt("id")+1;
              }
          }catch(SQLException e1){
                   JOptionPane.showConfirmDialog(null,"error sql:"+ e1.getMessage());
          }
          return cod;
      }
}
