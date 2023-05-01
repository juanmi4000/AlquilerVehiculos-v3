package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarClientes extends Controlador {

	  @FXML
	    private Button btModificar;

	    @FXML
	    private TableColumn<Cliente, String> tcDni;

	    @FXML
	    private TableColumn<Cliente, String> tcNombre;

	    @FXML
	    private TableColumn<Cliente, String> tcTelefono;

	    @FXML
	    private TableView<Cliente> tvListaClientes;

	    @FXML
	    void modificar(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    void initialize () {
	    	tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
	    	// de esta manera utilizamos una lambda, tenemos un parámetro y después nos quedamos con el dni que nos interese
	    	tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getDni()));
	    	// de esta manera, el ya sabe llamar al get
	    	tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
	    }
	    
	    @FXML
	    void actualizar (List<Cliente> clientes) {
	    	tvListaClientes.setItems(FXCollections.observableArrayList(clientes));
	    }

}
