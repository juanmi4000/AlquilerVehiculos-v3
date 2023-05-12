package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.awt.Desktop;
import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
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

public class ListarAlquileres extends Controlador{
	
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

	}

    @FXML
    void acercaDe(ActionEvent event) {
    	AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de", getEscenario());
		acercaDe.getEscenario().showAndWait();
    }

    @FXML
    void borrarAlquiler(ActionEvent event) {
    	
    }

    @FXML
    void borrarAlquilerTablas(ActionEvent event) {
    	Alquiler alquiler = tvListarAlquiler.getSelectionModel().getSelectedItem();
		try {
			VistaGraficos.getInstancia().getControlador().borrar(alquiler);
			Dialogos.mostrarDialogoAdvertencia("BORRAR ALQUILER", "El alquiler se ha borrado correctamente.",
					getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
    }

    @FXML
    void buscarAlquiler(ActionEvent event) {

    }

    @FXML
    void devolverAlquiler(ActionEvent event) {

    }

    @FXML
    void estadisticasAnuales(ActionEvent event) {

    }

    @FXML
    void estadisticasMensuales(ActionEvent event) {

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
	}
    
    @FXML
	private void comprobarValor (String cadena) {
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

}