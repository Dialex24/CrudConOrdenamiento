import Controladores.DispositivosController;
import Modelos.DispositivosModel;
import Vistas.*;

public class main {

    public static void main(String[] args) {
        // TODO code application logic here
        frmPrincipal VistaPrincipal = new frmPrincipal();
        frmConsulta VistaConsulta = new frmConsulta(VistaPrincipal, true);
        DispositivosModel ModeloEmpleados = new DispositivosModel();
        frmDispositivos VistaEmpleados = new frmDispositivos(VistaPrincipal,true);
        
        DispositivosController ControladorEmpleados = new DispositivosController(VistaEmpleados, VistaPrincipal, VistaConsulta, ModeloEmpleados);
        
    }
    
}
