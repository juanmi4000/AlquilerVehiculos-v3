package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class VentanaPrincipal extends Controlador{

    @FXML
    private MenuItem confirmarSalida;

    @FXML
    void leerCliente(ActionEvent event) {
    	LeerCliente controladorLeerCliente = (LeerCliente) Controladores.get("vistas/LeerCliente.fxml", "Leer cliente", getEscenario());
    	controladorLeerCliente.limpiar();
    	controladorLeerCliente.getEscenario().showAndWait();
    	try {
    		Cliente cliente = controladorLeerCliente.getCliente();
    		if (cliente != null) {
				VistaGraficos.getInstancia().getControlador().insertar(cliente);
			Dialogos.mostrarDialogoAdvertencia("Insertar cliente", "El cliente se ha insertado correctamente", getEscenario());
    		
    		}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error al insertar el cliente", e.getMessage(), getEscenario());
		}
    }

    @FXML
    void listarClientes(ActionEvent event) {
    	ListarClientes controladorListar = (ListarClientes) Controladores.get("vistas/ListarClientes.fxml", "Listar Clintes", getEscenario());
    	controladorListar.actualizar(VistaGraficos.getInstancia().getControlador().getClientes());
    	controladorListar.getEscenario().showAndWait();
    }

}