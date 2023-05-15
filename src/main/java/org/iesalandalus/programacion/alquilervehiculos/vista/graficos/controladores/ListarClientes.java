package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.awt.Desktop;
import java.util.Comparator;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarClientes extends Controlador {

	private static final String ERROR = "ERROR";
	private static final String MODIFICAR_CLIENTE = "Modificar cliente";

	@FXML
	private TextField tfCambiarDni;

	@FXML
	private TextField tfCambiarNombre;

	@FXML
	private TextField tfCambiarTelefono;

	@FXML
	private TextField tfListarAlquileresCliente;

	@FXML
	private TextField tfDniEncontrado;

	@FXML
	private TextField tfNombreEncontrado;

	@FXML
	private TextField tfTelefonoEncontrado;

	@FXML
	private TableColumn<Cliente, String> tcDni;

	@FXML
	private TableColumn<Cliente, String> tcNombre;

	@FXML
	private TableColumn<Cliente, String> tcTelefono;

	@FXML
	private TextField tfDni;

	@FXML
	private TableView<Cliente> tvListaClientes;

	@FXML
	void initialize() {
		tfNombreEncontrado.setDisable(true);
		tfDniEncontrado.setDisable(true);
		tfTelefonoEncontrado.setDisable(true);
		tfCambiarNombre.setDisable(true);
		tfCambiarDni.setDisable(true);
		tfCambiarTelefono.setDisable(true);
		tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getDni()));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		tvListaClientes.getSelectionModel().selectedItemProperty()
				.addListener((ob, oldValue, newValue) -> filaSeleccionada(newValue));
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
			Dialogos.mostrarDialogoAdvertencia("Borrar cliente", "El cliente se ha borrado correctamente.",
					getEscenario());
			tvListaClientes.getItems().remove(cliente);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void buscarCliente(ActionEvent event) {
		try {
			Cliente cliente = getCliente();
			tfNombreEncontrado.setText(cliente.getNombre());
			tfDniEncontrado.setText(cliente.getDni());
			tfTelefonoEncontrado.setText(cliente.getTelefono());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void gitHub(ActionEvent event) {
		String link = "https://github.com/juanmi4000/AlquilerVehiculos-v3.git";
		try {
			Desktop deskpot = Desktop.getDesktop();
			deskpot.browse(java.net.URI.create(link));
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, "Ha ocurrido un error inesperado al abrir la página.", getEscenario());
			e.printStackTrace();
		}
	}

	@FXML
	void listarAlquileres(ActionEvent event) {
		ListarAlquileres listarAlquileres = (ListarAlquileres) Controladores.get("vistas/ListarAlquileres.fxml",
				"LISTAR ALQUILERES", getEscenario());
		listarAlquileres.actualizar(VistaGraficos.getInstancia().getControlador().getAlquileres());
		listarAlquileres.getEscenario().showAndWait();
	}

	@FXML
	void listarAlquileresCliente(ActionEvent event) {
		ListarAlquileresClienVehi listarAlquileresCliente = (ListarAlquileresClienVehi) Controladores
				.get("vistas/ListarAlquileresCliente.fxml", "ALQUILERES DEL CLIENTE", getEscenario());
		try {
			Cliente cliente = VistaGraficos.getInstancia().getControlador()
					.buscar(Cliente.getClienteConDni(tfListarAlquileresCliente.getText()));
			listarAlquileresCliente.actualizar(VistaGraficos.getInstancia().getControlador().getAlquileres(cliente));
			listarAlquileresCliente.getEscenario().showAndWait();
			tfListarAlquileresCliente.setText("");
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}

	}

	@FXML
	void listarVehiculos(ActionEvent event) {
		ListarVehiculos listarVehiculos = (ListarVehiculos) Controladores.get("vistas/ListarVehiculos.fxml",
				"LISTAR VEHICULOS", getEscenario());
		listarVehiculos.getEscenario().showAndWait();
	}

	@FXML
	void modificarCliente(ActionEvent event) {
		ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("vistas/ModificarCliente.fxml",
				MODIFICAR_CLIENTE, getEscenario());
		modificarCliente.limpiar();
		modificarCliente.getEscenario().showAndWait();

		try {
			Cliente cliente = modificarCliente.getCliente();
			if (cliente != null) {
				String nombre = modificarCliente.getTelefono();
				String telefono = modificarCliente.getNombre();
				VistaGraficos.getInstancia().getControlador().modificar(cliente, nombre, telefono);
				Dialogos.mostrarDialogoAdvertencia(MODIFICAR_CLIENTE, "El cliente se ha modificado correctamente",
						getEscenario());
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(MODIFICAR_CLIENTE, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void estadisticasAnuales(ActionEvent event) {

	}

	@FXML
	void estadisticasMensuales(ActionEvent event) {

	}

	@FXML
	void leerCliente(ActionEvent event) {
		LeerCliente controladorLeerCliente = (LeerCliente) Controladores.get("vistas/LeerCliente.fxml", "Leer cliente",
				getEscenario());
		controladorLeerCliente.limpiar();
		controladorLeerCliente.getEscenario().showAndWait();
		try {
			Cliente cliente = controladorLeerCliente.getCliente();
			if (cliente != null) {
				VistaGraficos.getInstancia().getControlador().insertar(cliente);
				Dialogos.mostrarDialogoAdvertencia("Insertar cliente", "El cliente se ha insertado correctamente",
						getEscenario());
				tvListaClientes.getItems().add(cliente);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void borrarClienteTabla(ActionEvent event) {
		Cliente cliente = tvListaClientes.getSelectionModel().getSelectedItem();
		try {
			VistaGraficos.getInstancia().getControlador().borrar(cliente);
			Dialogos.mostrarDialogoAdvertencia("BORRAR CLIENTE", "El cliente ha sido borrado correctamente.",
					getEscenario());
			tvListaClientes.getItems().remove(tvListaClientes.getSelectionModel().getSelectedIndex());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}

	}

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void actualizar(List<Cliente> clientes) {
		clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
		tvListaClientes.setItems(FXCollections.observableArrayList(clientes));
	}

	@FXML
	public void limpiar() {
		tfDni.setText("");
	}

	@FXML
	private Cliente getCliente() {
		return VistaGraficos.getInstancia().getControlador().buscar(Cliente.getClienteConDni(tfDni.getText()));
	}

	@FXML
	private void filaSeleccionada(Cliente cliente) {
		tfCambiarNombre.setText(cliente.getNombre());
		tfCambiarDni.setText(cliente.getDni());
		tfCambiarTelefono.setText(cliente.getTelefono());
	}
}
