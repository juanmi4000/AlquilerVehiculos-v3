package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModificarCliente extends Controlador{

    @FXML private Label lbCambiarNombre;
    @FXML private Label lbcambiarTelefono;
    @FXML private TextField tfDni;
    @FXML private TextField tfNombre;
    @FXML private TextField tfTelefono;
    
    private boolean cancelado;
    
    @FXML
    private void initialize() {
    	
    }

    @FXML
    void aceptar(ActionEvent event) {
    	cancelado = false;
    	getEscenario().close();
    }

    @FXML
    void cancelar(ActionEvent event) {
    	cancelado = true;
    	getEscenario().close();
    }
    
    @FXML
    void buscarCliente(ActionEvent event) {
    	Cliente cliente = getCliente();
    	try {
    		lbCambiarNombre.setText(cliente.getNombre());
        	lbcambiarTelefono.setText(cliente.getTelefono());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}
    		
    }
    
    public Cliente getCliente() {
    	String dni = tfDni.getText();
    	return VistaGraficos.getInstancia().getControlador().buscar(Cliente.getClienteConDni(dni));
    	
    }
    
    public String getNombre() {
    	return cancelado ? "" : tfNombre.getText();
    }
    
    public String getTelefono() {
    	return cancelado ? "" : tfTelefono.getText();
    }
    
    public void limpiar() {
    	tfDni.setText("");
    	tfNombre.setText("");
    	tfTelefono.setText("");
    	cancelado = true;
    }
    
    

}