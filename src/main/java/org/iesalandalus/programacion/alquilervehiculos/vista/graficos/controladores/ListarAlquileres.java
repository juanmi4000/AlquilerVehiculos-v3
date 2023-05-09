package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ListarAlquileres extends Controlador{

    @FXML private Label cambiarDni;
    @FXML private Label cambiarFechaAlquiler;
    @FXML private Label cambiarFechaDevolucion;
    @FXML private HBox cambiarMarca;
    @FXML private Label cambiarMatricula;
    @FXML private Label cambiarModelo;
    @FXML private Label cambiarNombre;
    @FXML private Label cambiarTelefono;
    @FXML private Label cambiarTipo;
    @FXML private TableColumn<Alquiler, String> tcCliente;
    @FXML private TableColumn<Alquiler, String> tcFechaAquiler;
    @FXML private TableColumn<Alquiler, LocalDate> tcFechaDevolucion;
    @FXML private TableColumn<Alquiler, String> tcVehiculo;
    @FXML private TableView<Alquiler> tvListarAlquiler;
    
    @FXML
    private void initialize() {
    	tcCliente.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
    	tcVehiculo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
    	tcFechaAquiler.setCellValueFactory(fila -> new SimpleStringProperty(String.format("%s", fila.getValue().getFechaAlquiler())));
    	/*tcFechaDevolucion.setCellValueFactory(fila -> {
    		LocalDate fechaDevolucion = fila.getValue().getFechaDevolucion();
    		return fechaDevolucion == null ? "" : new SimpleStringProperty(String.format("%s", fechaDevolucion));
    	});*/
    }
    
    @FXML
    void actualizar (List<Alquiler> alquileres) {
    	tvListarAlquiler.setItems(FXCollections.observableArrayList(alquileres));
    }


}
