package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.awt.Desktop;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarClientes extends Controlador{
	
	private static final String ERROR = "Ha ocurrido un error inesperado al abrir la página.";

	@FXML
	private Label cambiarDni;

	@FXML
	private Label cambiarNombre;

	@FXML
	private Label cambiarTelefono;

	@FXML
	private Label nombreBuscado;

	@FXML
	private TableColumn<Cliente, String> tcDni;
	
	@FXML
    private Label dniEncontrado;

	@FXML
	private TableColumn<Cliente, String> tcNombre;

	@FXML
	private TableColumn<Cliente, String> tcTelefono;

	@FXML
	private Label telefonoBuscado;

	@FXML
	private TextField tfDni;

	@FXML
	private TableView<Cliente> tvListaClientes;

	@FXML
	void initialize() {
		tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getDni()));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
	}

	@FXML
	void acercaDe(ActionEvent event) {
		AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de", getEscenario());
		acercaDe.getEscenario().showAndWait();
	}

	@FXML
	void borrarCliente(ActionEvent event) {
		try {
			Cliente cliente = getCliente();
			VistaGraficos.getInstancia().getControlador().borrar(cliente);
			limpiar();
			Dialogos.mostrarDialogoAdvertencia("Borrar cliente", "El cliente se ha borrado correctamente.", getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}
		
	}

	@FXML
	void buscarCliente(ActionEvent event) {
		try {
			Cliente cliente = getCliente();
			nombreBuscado.setText(cliente.getNombre());
			dniEncontrado.setText(cliente.getDni());
			telefonoBuscado.setText(cliente.getTelefono());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void gitHub(ActionEvent event) {
		String link = "https://github.com/juanmi4000/AlquilerVehiculos-v3.git";
		try {
			Desktop deskpot = Desktop.getDesktop();
			deskpot.browse(java.net.URI.create(link));
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error al abrir la URL", ERROR, getEscenario());
			e.printStackTrace();
		}
	}

	@FXML
	void listarAlquileres(ActionEvent event) {
		ListarAlquileres listarAlquileres = (ListarAlquileres) Controladores.get("vistas/ListarAlquileres.fxml", "LISTAR ALQUILERES", getEscenario());
		listarAlquileres.actualizar(VistaGraficos.getInstancia().getControlador().getAlquileres());
		listarAlquileres.getEscenario().showAndWait();
	}

	@FXML
	void listarVehiculos(ActionEvent event) {

	}
	
	@FXML
	void listarAlquileresCliente(ActionEvent event) {
		
	}

	@FXML
	void modificar(ActionEvent event) {
		ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("vistas/ModificarCliente.fxml",
				"Modificar cliente", getEscenario());
		modificarCliente.limpiar();
		modificarCliente.getEscenario().showAndWait();
		
		try {
			Cliente cliente = modificarCliente.getCliente();
			String nombre = modificarCliente.getTelefono();
			String telefono = modificarCliente.getNombre();
			VistaGraficos.getInstancia().getControlador().modificar(cliente, nombre, telefono);
			Dialogos.mostrarDialogoAdvertencia("Modificar cliente", "El cliente se ha modificado correctamente", getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Modificar cliente", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}
	
	@FXML 
	void actualizar(List<Cliente> clientes) {
		 tvListaClientes.setItems(FXCollections.observableArrayList(clientes)); 
	}
	
	public void limpiar() {
    	tfDni.setText("");
    }
	
	private Cliente getCliente() {
		return VistaGraficos.getInstancia().getControlador().buscar(Cliente.getClienteConDni(tfDni.getText()));
	}

}
