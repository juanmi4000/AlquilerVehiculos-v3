package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.time.LocalDate;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class LeerAlquiler extends Controlador {

	private boolean cancelado;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private TextField tfCliente;

	@FXML
	private TextField tfVehiculo;

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
	public Alquiler getAlquiler() {
		Cliente cliente = VistaGraficos.getInstancia().getControlador()
				.buscar(Cliente.getClienteConDni(tfCliente.getText()));
		Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador()
				.buscar(Vehiculo.getVehiculoConMatricula(tfVehiculo.getText()));
		LocalDate fechaAlquiler = dpFechaAlquiler.getValue();
		return cancelado ? null : new Alquiler(cliente, vehiculo, fechaAlquiler);
	}

	@FXML
	void limpiar() {
		tfCliente.setText("");
		tfVehiculo.setText("");
		dpFechaAlquiler.setValue(null);
		cancelado = true;
	}

}
