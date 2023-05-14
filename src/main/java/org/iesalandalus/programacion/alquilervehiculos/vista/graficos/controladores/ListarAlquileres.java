package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.awt.Desktop;
import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListarAlquileres extends Controlador {

	private static final String ERROR = "ERROR";
	private static final ObservableList<String> DEVOLVER = FXCollections.observableArrayList("Devolver por cliente",
			"Devolver por vehiculo");

	@FXML
	private ChoiceBox<String> cbDevolver;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private DatePicker dpFechaDevolucion;

	@FXML
	private TableColumn<Alquiler, String> tcCliente;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaAquiler;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaDevolucion;

	@FXML
	private TableColumn<Alquiler, String> tcVehiculo;

	@FXML
	private TextField tfCambiarCilindrada;

	@FXML
	private TextField tfCambiarDni;

	@FXML
	private TextField tfCambiarFecAlq;

	@FXML
	private TextField tfCambiarFecDev;

	@FXML
	private TextField tfCambiarMarca;

	@FXML
	private TextField tfCambiarMatricula;

	@FXML
	private TextField tfCambiarModelo;

	@FXML
	private TextField tfCambiarNombre;

	@FXML
	private TextField tfCambiarPlazas;

	@FXML
	private TextField tfCambiarPma;

	@FXML
	private TextField tfCambiarTelefono;

	@FXML
	private TextField tfCambiarTipo;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfPrecio;

	@FXML
	private TextField tfOpDni;

	@FXML
	private TextField tfOpMat;

	@FXML
	private TableView<Alquiler> tvListarAlquiler;

	@FXML
	private void initialize() {
		deshabilitar();
		cbDevolver.setItems(DEVOLVER);
		cbDevolver.valueProperty().addListener((ob, ol, ne) -> comprobarValor(ne));
		tcCliente.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
		tcVehiculo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
		tcFechaAquiler
				.setCellValueFactory(fila -> new SimpleObjectProperty<LocalDate>(fila.getValue().getFechaAlquiler()));
		tcFechaDevolucion
				.setCellValueFactory(fila -> new SimpleObjectProperty<LocalDate>(fila.getValue().getFechaDevolucion()));
		tvListarAlquiler.getSelectionModel().selectedItemProperty()
				.addListener((ob, oldValue, newValue) -> filaSeleccionada(newValue));

	}

	@FXML
	void acercaDe(ActionEvent event) {
		AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de", getEscenario());
		acercaDe.getEscenario().showAndWait();
	}

	@FXML
	void borrarAlquiler(ActionEvent event) {
		try {
			Alquiler alquiler = getAlquiler(true);
			VistaGraficos.getInstancia().getControlador().borrar(alquiler);
			tvListarAlquiler.getItems().remove(alquiler);
			Dialogos.mostrarDialogoAdvertencia("BORRAR ALQUILER", "El alquiler ha sido borrado correctamente.", getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
		
		
		limpiarOperaciones();
	}

	@FXML
	void borrarAlquilerTablas(ActionEvent event) {
		Alquiler alquiler = tvListarAlquiler.getSelectionModel().getSelectedItem();
		try {
			VistaGraficos.getInstancia().getControlador().borrar(alquiler);
			tvListarAlquiler.getItems().remove(tvListarAlquiler.getSelectionModel().getSelectedIndex());
			Dialogos.mostrarDialogoAdvertencia("BORRAR ALQUILER", "El alquiler se ha borrado correctamente.",
					getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void buscarAlquiler(ActionEvent event) {
		try {
			Alquiler alquiler = getAlquiler(true);
			if (alquiler != null) {
				BuscarAlquiler buscarAlquiler = (BuscarAlquiler) Controladores.get("vistas/BuscarAlquiler.fxml",
						"ALQUILER BUSCADO", getEscenario());
				buscarAlquiler.limpiar();
				buscarAlquiler.cambiarValores(alquiler);
				buscarAlquiler.getEscenario().showAndWait();
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
		limpiarOperaciones();
	}

	@FXML
	void devolverAlquiler(ActionEvent event) {
		String dni = tfDni.getText();
		String matricula = tfMatricula.getText();
		LocalDate fechaDevolucion = dpFechaDevolucion.getValue();
		try {
			if (!dni.isBlank()) {
				Cliente cliente = VistaGraficos.getInstancia().getControlador().buscar(Cliente.getClienteConDni(dni));
				VistaGraficos.getInstancia().getControlador().devolver(cliente, fechaDevolucion);
			} else {
				Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador()
						.buscar(Vehiculo.getVehiculoConMatricula(matricula));
				VistaGraficos.getInstancia().getControlador().devolver(vehiculo, fechaDevolucion);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
		limpiarDevolver();
	}

	@FXML
	void estadisticasAnuales(ActionEvent event) {

	}

	@FXML
	void estadisticasMensuales(ActionEvent event) {

	}

	@FXML
	void leerAlquiler(ActionEvent event) {
		try {
			Alquiler alquiler = getAlquiler(false);
			if (alquiler != null) {
				VistaGraficos.getInstancia().getControlador().insertar(alquiler);
				tvListarAlquiler.getItems().add(alquiler);
				Dialogos.mostrarDialogoAdvertencia("INSERTAR ALQUILER", "El alquiler se ha insertado correctamente.",
						getEscenario());
			}
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
			Dialogos.mostrarDialogoError("Error al abrir la URL", "Ha ocurrido un error inesperado al abrir la página.",
					getEscenario());
			e.printStackTrace();
		}
	}

	@FXML
	void listarClientes(ActionEvent event) {
		ListarClientes controladorListar = (ListarClientes) Controladores.get("vistas/ListarClientes.fxml",
				"Listar Clintes", getEscenario());
		controladorListar.limpiar();
		controladorListar.actualizar(VistaGraficos.getInstancia().getControlador().getClientes());
		controladorListar.getEscenario().showAndWait();
	}

	@FXML
	void listarVehiculos(ActionEvent event) {
		ListarVehiculos listarVehiculos = (ListarVehiculos) Controladores.get("vistas/ListarVehiculos.fxml",
				"LISTAR VEHICULOS", getEscenario());
		listarVehiculos.getEscenario().showAndWait();
	}

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void actualizar(List<Alquiler> alquileres) {
		tvListarAlquiler.setItems(FXCollections.observableArrayList(alquileres));
	}

	@FXML
	void deshabilitar() {
		tfDni.setDisable(true);
		tfMatricula.setDisable(true);
		dpFechaDevolucion.setDisable(true);
		tfCambiarNombre.setDisable(true);
		tfCambiarDni.setDisable(true);
		tfCambiarTelefono.setDisable(true);
		tfCambiarTipo.setDisable(true);
		tfCambiarMarca.setDisable(true);
		tfCambiarModelo.setDisable(true);
		tfCambiarMatricula.setDisable(true);
		tfCambiarCilindrada.setDisable(true);
		tfCambiarPma.setDisable(true);
		tfCambiarPlazas.setDisable(true);
		tfCambiarFecAlq.setDisable(true);
		tfCambiarFecDev.setDisable(true);
		tfPrecio.setDisable(true);
		cbDevolver.getSelectionModel().select("Elige una opcion:");
	}

	@FXML
	private void comprobarValor(String cadena) {
		if (cadena.equals("Devolver por cliente")) {
			tfDni.setDisable(false);
			tfMatricula.setDisable(true);
			dpFechaDevolucion.setDisable(false);
		} else {
			tfDni.setDisable(true);
			tfMatricula.setDisable(false);
			dpFechaDevolucion.setDisable(false);
		}
	}

	@FXML
	private void filaSeleccionada(Alquiler alquiler) {
		Cliente cliente = VistaGraficos.getInstancia().getControlador().buscar(alquiler.getCliente());
		Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador().buscar(alquiler.getVehiculo());
		LocalDate fechaAlquiler = alquiler.getFechaAlquiler();
		LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
		tfCambiarNombre.setText(cliente.getNombre());
		tfCambiarDni.setText(cliente.getDni());
		tfCambiarTelefono.setText(cliente.getTelefono());
		tipoVehiculo(vehiculo);
		tfCambiarFecAlq.setText(String.format("%s", fechaAlquiler));
		if (fechaDevolucion == null) {
			tfCambiarFecDev.setText("Aún no está devuelto");
			tfPrecio.setText("---------------");
		} else {
			tfCambiarFecDev.setText(String.format("%s", fechaDevolucion));
			tfPrecio.setText(String.format("%s", vehiculo.getFactorPrecio()));
		}
	}

	@FXML
	private void tipoVehiculo(Vehiculo vehiculo) {
		tfCambiarMarca.setText(vehiculo.getMarca());
		tfCambiarModelo.setText(vehiculo.getModelo());
		tfCambiarMatricula.setText(vehiculo.getMatricula());
		if (vehiculo instanceof Turismo turismo) {
			tfCambiarTipo.setText("Turismo");
			tfCambiarCilindrada.setText(String.format("%s", turismo.getCilindrada()));
			tfCambiarPma.setText("-----------");
			tfCambiarPlazas.setText("-----------");
		} else if (vehiculo instanceof Furgoneta furgoneta) {
			tfCambiarTipo.setText("Furgoneta");
			tfCambiarCilindrada.setText("-----------");
			tfCambiarPma.setText(String.format("%s", furgoneta.getPma()));
			tfCambiarPlazas.setText(String.format("%s", furgoneta.getPlazas()));
		} else if (vehiculo instanceof Autobus autobus) {
			tfCambiarTipo.setText("Autobus");
			tfCambiarCilindrada.setText("-----------");
			tfCambiarPma.setText("-----------");
			tfCambiarPlazas.setText(String.format("%s", autobus.getPlazas()));

		}
	}

	@FXML
	Alquiler getAlquiler(boolean buscar) {
		Cliente cliente = VistaGraficos.getInstancia().getControlador()
				.buscar(Cliente.getClienteConDni(tfOpDni.getText()));
		Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador()
				.buscar(Vehiculo.getVehiculoConMatricula(tfOpMat.getText()));
		LocalDate fechaAlquiler = dpFechaAlquiler.getValue();
		Alquiler alquiler = new Alquiler(cliente, vehiculo, fechaAlquiler);
		return buscar ? VistaGraficos.getInstancia().getControlador().buscar(alquiler) : alquiler ;

	}

	@FXML
	void limpiarDevolver() {
		Controles.limpiarCamposTexto(tfDni, tfMatricula);
		dpFechaDevolucion.setValue(null);
	}

	@FXML
	void limpiarOperaciones() {
		Controles.limpiarCamposTexto(tfOpDni, tfOpMat);
		dpFechaAlquiler.setValue(null);
	}

}