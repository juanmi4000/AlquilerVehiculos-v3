package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class LeerVehiculo extends Controlador {
	
	private static final String FURGONETA = "Furgoneta";
	private static final ObservableList<String> TIPOS_VEHICULOS = FXCollections.observableArrayList("Turismo",
			FURGONETA, "Autobus");

	@FXML
	private ChoiceBox<String> cbTipo;

	@FXML
	private TextField tfPma;

	@FXML
	private TextField tfCilindrada;

	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfModelo;

	@FXML
	private TextField tfPlazas;

	private boolean cancelado;

	@FXML
	void initialize() {
		cbTipo.setItems(TIPOS_VEHICULOS);
		tfCilindrada.setDisable(false);
		tfPlazas.setDisable(false);
		tfPma.setDisable(false);
		cbTipo.selectionModelProperty().addListener((ob, ol, ne) -> comprobarValor());
	}

	@FXML
	void cancelar(ActionEvent event) {
		cancelado = true;
		getEscenario().close();
	}

	@FXML
	void insertarAlquiler(ActionEvent event) {
		cancelado = false;
		getEscenario().close();
	}

	public Vehiculo getVehiculo() {
		String valor = cbTipo.valueProperty().getValue();
		Vehiculo vehiculo = null;
		String marca = tfMarca.getText();
		String modelo = tfModelo.getText();
		String matricula = tfMatricula.getText();
		if (valor.equals("Turismo")) {
			int cilindrada = Integer.parseInt(tfCilindrada.getText());
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
		} else if (valor.equals("Furgoneta")) {
			int plazas = Integer.parseInt(tfPlazas.getText());
			int pma = Integer.parseInt(tfPma.getText());
			vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
		} else {
			int plazas = Integer.parseInt(tfPlazas.getText());
			vehiculo = new Autobus(marca, modelo, plazas, matricula);
		}
		return cancelado ? null : vehiculo;
	}

	private void comprobarValor() {
		String valor = cbTipo.valueProperty().getValue();
		if (valor.equals("Turismo")) {
			tfCilindrada.setDisable(true);
			tfPlazas.setDisable(false);
			tfPma.setDisable(false);
		} else if (valor.equals("Furgoneta")) {
			tfPlazas.setDisable(true);
			tfPma.setDisable(true);
			tfCilindrada.setDisable(false);
		} else {
			tfPlazas.setDisable(true);
			tfPma.setDisable(false);
			tfCilindrada.setDisable(false);
		}

	}
	
	void limpiar() {
    	tfMarca.setText("");
    	tfModelo.setText("");
    	tfMatricula.setText("");
    	tfCilindrada.setText("");
    	tfPlazas.setText("");
    	tfPlazas.setText("");
    	cancelado = true;
    }

}