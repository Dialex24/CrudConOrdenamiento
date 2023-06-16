package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DispositivosController implements ActionListener, MouseListener{
 frmDispositivos VistaDispositivos;
 frmPrincipal VistaPrincipal;
 frmConsulta VistaConsulta;
 DispositivosModel ModeloDispositivos;
 
 
    

    public DispositivosController(frmDispositivos VistaDispositivos, frmPrincipal VistaPrincipal,frmConsulta VistaConsulta, DispositivosModel ModeloDispositivos) {
        this.VistaDispositivos = VistaDispositivos;
        this.VistaPrincipal = VistaPrincipal;
        this.VistaConsulta = VistaConsulta;
        this.ModeloDispositivos = ModeloDispositivos;
        
        /*LEVANTAR LAS VISTAS*/
      this.VistaPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
      this.VistaPrincipal.setVisible(true);
      
      /*PONER A LA ESCUCHA LOS BOTONES*/
      this.VistaDispositivos.btn_Agregar.addActionListener(this);
      this.VistaConsulta.btn_Editar.addActionListener(this);
      //this.VistaDispositivos.btnEliminar.addActionListener(this);
      this.VistaPrincipal.jmAgDisp.addMouseListener(this);
      this.VistaPrincipal.jmEdDis.addMouseListener(this);
      this.VistaConsulta.btnEjecutar.addActionListener(this);
      this.VistaConsulta.jtbBuscar.addMouseListener(this);
        
      /*REALIZAR LA CONEXION*/
            
            //Limpiar la tabla Vista Empleados
                /*DefaultTableModel TablaModelo = new DefaultTableModel();
                TablaModelo.setRowCount(0);
                TablaModelo.setColumnCount(0);
                //this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
      
            //prepara el modelo de la tabla
                    TablaModelo.addColumn("CODIGO");
                    TablaModelo.addColumn("APELLIDOS");
                    TablaModelo.addColumn("NOMBRE");
                    TablaModelo.addColumn("TELEFONO");
                    
      /* LEVANTAR EL MODELO Y LOGRAR RECORRER EL RESULTSET*/
        //Captar el resultado que viene del Modelo desde el método LISTARDATOS
               // ResultSet rstEmpleados =  this.ModeloEmpleado.ListarDatos();
               
                    /*try
                    {
                       
                    while(rstEmpleados.next())
                    {
                     TablaModelo.addRow(new Object[]{rstEmpleados.getInt("codigo"),rstEmpleados.getString("apellido"),rstEmpleados.getString("nombre"),rstEmpleados.getString("telefono")});  
                    }  
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, "Algo hizo falta..."+e);
                    }*/
                    
                    DefaultTableModel TablaModelo = this.ModeloDispositivos.ListarDatos();
                    //this.VistaDispositivos.jtbEmpleados.setModel(TablaModelo);
                    
                   
            //PASAR EL MODELO CREADO A LA TABLA DE LA VISTA EMPLEADOS        
                    //this.VistaDispositivos.jtbEmpleados.setModel(TablaModelo);
        
          //    poner a la escucha tabla empleados
         // this.VistaDispositivos.jtbEmpleados.addMouseListener(this);
          
        /*LEVANTAR LA VISTA EMPLEADOR*/
      //this.VistaEmpleados.setLocationRelativeTo(null);
      //this.VistaEmpleados.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.VistaConsulta.btn_Editar)
        {
            this.ModeloDispositivos.Actualizar(this.VistaDispositivos.txtDisp.getText(), 
                    this.VistaDispositivos.txtMarca.getText(), this.VistaDispositivos.txtTipo.getText(),
                    Integer.parseInt(this.VistaDispositivos.txtPrecio.getText()));
            this.ModeloDispositivos.ListarDatos();
            DefaultTableModel TablaModelo = this.ModeloDispositivos.ListarDatos();
             
        }
        if(e.getSource() == this.VistaDispositivos.btn_Agregar)
        {
            this.ModeloDispositivos.Guardar(this.VistaDispositivos.txtDisp.getText(), 
                    this.VistaDispositivos.txtMarca.getText(), this.VistaDispositivos.txtTipo.getText(),
                    Integer.parseInt(this.VistaDispositivos.txtPrecio.getText()));
            this.ModeloDispositivos.ListarDatos();
            DefaultTableModel TablaModelo = this.ModeloDispositivos.ListarDatos();
           
        }
        /*if(e.getSource() == this.VistaConsulta.btnEliminar)
        {
            try {
                this.ModeloDispositivos.Eliminar(this.VistaDispositivos.txtDisp.getText());
            } catch (SQLException ex) {
                Logger.getLogger(DispositivosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.ModeloDispositivos.ListarDatos();
            DefaultTableModel TablaModelo = this.ModeloDispositivos.ListarDatos();
            this.VistaDispositivos.jtbEmpleados.setModel(TablaModelo);
        }*/
        if(e.getSource() == this.VistaConsulta.btnEjecutar){
            DefaultTableModel TablaModelo1 = this.ModeloDispositivos.Consultar(this.VistaConsulta.txtConsulta.getText());
            this.VistaConsulta.jtbBuscar.setModel(TablaModelo1);
        }

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int fila;
        if(arg0.getSource()==this.VistaConsulta.jtbBuscar)
        {
           // JOptionPane.showMessageDialog(null, "clicando la tabla");
            fila = this.VistaConsulta.jtbBuscar.getSelectedRow();
            //dispositivo
            this.VistaConsulta.txtDisp1.setText(this.VistaConsulta.jtbBuscar.getValueAt(fila, 0).toString());
            //marca
            this.VistaConsulta.txtMarca1.setText(this.VistaConsulta.jtbBuscar.getValueAt(fila, 1).toString());
            //tipo
            this.VistaConsulta.txtTipo1.setText(this.VistaConsulta.jtbBuscar.getValueAt(fila, 2).toString());
            //precio
            this.VistaConsulta.txtPrecio1.setText(this.VistaConsulta.jtbBuscar.getValueAt(fila, 3).toString());
        }
        if(arg0.getSource() == this.VistaPrincipal.jmAgDisp){
            this.VistaDispositivos.setVisible(true);
        }
        if(arg0.getSource() == this.VistaPrincipal.jmEdDis){
            this.VistaConsulta.setVisible(true); 
        }
                           
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
       
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
       
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        
    }

    
    
 
 
}
