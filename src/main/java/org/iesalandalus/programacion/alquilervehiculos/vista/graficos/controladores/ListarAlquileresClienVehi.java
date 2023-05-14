package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarAlquileresClienVehi extends Controlador{

    @FXML
    private TableColumn<Alquiler, String> tcCliente;

    @FXML
    private TableColumn<Alquiler, LocalDate> tcFechaAlquiler;

    @FXML
    private TableColumn<Alquiler, LocalDate> tcFechaDevolucion;

    @FXML
    private TableColumn<Alquiler, String> tcVehiculo;

    @FXML
    private TableView<Alquiler> tvListarAlquileresCliente;
    
    @FXML
    private void initialize() {
    	tcCliente.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
    	tcVehiculo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
    	tcFechaAlquiler.setCellValueFactory(fila -> new SimpleObjectProperty<LocalDate>(fila.getValue().getFechaAlquiler()));
    	tcFechaDevolucion.setCellValueFactory(fila -> new SimpleObjectProperty<LocalDate>(fila.getValue().getFechaDevolucion()));
    }
    
    @FXML
    void actualizar (List<Alquiler> alquileres) {
    	tvListarAlquileresCliente.setItems(FXCollections.observableArrayList(alquileres));
    }

}

