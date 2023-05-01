package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VentanaPrincipal extends Controlador{

	 @FXML
	    private Button btButton;

	    @FXML
	    void saludar() {
	    	System.out.println("Botón pulsado");
	    	ListarClientes controladorListar = (ListarClientes) Controladores.get("vistas/ListarClientes.fxml", "Listar Clintes", getEscenario());
	    	controladorListar.actualizar(VistaGraficos.getInstancia().getControlador().getClientes());
	    	controladorListar.getEscenario().showAndWait();
	    
	    }
    
    @FXML
    private void initialize() {
    	//  asignar algo 
    	System.out.println("Método initialize de VentanaPrincipal");
    	// tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	// tcDni.setCellValueFactory(fila --> new SimpleStringProperty(fila.getValue().getDni()));  
    }
    

}
