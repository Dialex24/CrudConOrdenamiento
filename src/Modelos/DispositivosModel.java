
package Modelos;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DispositivosModel {
Connection MyConexion;
ResultSet result;


public DefaultTableModel ListarDatos()
{
    //realizar la conexion
    
    //limpiar la tabla vista empleados
    DefaultTableModel TablaModelo = new DefaultTableModel();
    TablaModelo.setRowCount(0);
    TablaModelo.setColumnCount(0);
    
    //prepara ek modelo de la tabla
    
        TablaModelo.addColumn("DISPOSITIVO");
    TablaModelo.addColumn("MARCA");
    TablaModelo.addColumn("TIPO");
    TablaModelo.addColumn("PRECIO");
    
    try
    {
        Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        result = sentencia.executeQuery("select * from dispositivos");  
       //return result;
       
       while(result.next())
       {
           TablaModelo.addRow(new Object[]{result.getString("dispositivo"), result.getString("marca"), result.getString("tipo"), result.getInt("precio")});
       }
       return TablaModelo;
    }
    
    
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, "No se Pudo Listar Dispositivos...."+e.getMessage());
        return TablaModelo;
    }
}


public void Actualizar(String dispositivos, String marca, String tipo, int precio)
{
        try
        {
          Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        sentencia.executeQuery("Update dispositivos set marca ="+"'"+marca+"',tipo="+"'"+tipo+"',precio="+"'"+precio+"' where dispositivo="+"'"+dispositivos+"'");
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "No se pudo Actualizar..."+ex.getMessage());
        }
        
          
}

public void Guardar(String dispositivo, String marca, String tipo, int precio)
{
     try
        {
          Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        sentencia.executeQuery("Insert into dispositivos values ("+"'"+dispositivo+"',"+"'"+marca+"',"+"'"+tipo+"',"+"'"+precio+"')");
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "No se pudo Guardar..."+ex.getMessage());
        }
         
     
    }

    public void Eliminar(String dispEliminar) throws SQLException
     {
         try
        {
          Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        sentencia.executeQuery("Delete from dispositivos where dispositivo="+"'"+dispEliminar+"'");
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "No se pudo Eliminar..."+ex.getMessage());
        }
     }
public DefaultTableModel Consultar(String consulta)
{
   
    //limpiar la tabla vista empleados
    DefaultTableModel TablaModelo1 = new DefaultTableModel();
    TablaModelo1.setRowCount(0);
    TablaModelo1.setColumnCount(0);
    
    //prepara ek modelo de la tabla
    
    TablaModelo1.addColumn("DISPOSITIVO");
    TablaModelo1.addColumn("MARCA");
    TablaModelo1.addColumn("TIPO");
    TablaModelo1.addColumn("PRECIO");
    
    try
    {
        Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        result = sentencia.executeQuery("select * FROM dispositivos WHERE tipo ="+"'"+consulta+"'");  
       //return result;
       
       while(result.next())
       {
           TablaModelo1.addRow(new Object[]{result.getString("dispositivo"), result.getString("marca"), result.getString("tipo"), result.getInt("precio")});
       }
       return TablaModelo1;
    }
    
    
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, "No se Pudo Consultar Dispositivos...."+e.getMessage());
        return TablaModelo1;
    }
   }
}
