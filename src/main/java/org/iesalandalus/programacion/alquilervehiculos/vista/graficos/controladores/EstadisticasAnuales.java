package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class EstadisticasAnuales extends Controlador{
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private PieChart pcEstadisticasMensuales;

    @FXML
    private TextField tfFecha;

    @FXML
	void mostrarEstadisticas(ActionEvent event) {
		int contadorTurismo = 0;
		int contadorFurgoneta = 0;
		int contadorAutobus = 0;
		LocalDate fecha = LocalDate.parse("01/01/" + tfFecha.getText(), FORMATO_FECHA);
		for (Alquiler alquiler : VistaGraficos.getInstancia().getControlador().getAlquileres()) {
			if (alquiler.getFechaAlquiler().getYear() == fecha.getYear()) {
				if (alquiler.getVehiculo() instanceof Turismo) {
					contadorTurismo++;
				} else if (alquiler.getVehiculo() instanceof Furgoneta) {
					contadorFurgoneta++;
				} else {
					contadorAutobus++;
				}
			}
		}
		formarEsta(contadorTurismo, contadorFurgoneta, contadorAutobus);
	}
	
	@FXML
	private void formarEsta(int contT, int contF, int contA) {
		PieChart.Data slTurismo = new PieChart.Data("Turismo", contT);
		PieChart.Data slFurgoneta = new PieChart.Data("Furgoneta", contF);
		PieChart.Data slAutobus = new PieChart.Data("Autobus", contA);
		
		pcEstadisticasMensuales.getData().addAll(slTurismo, slFurgoneta, slAutobus);
		
		Tooltip tpTurismo = new Tooltip(String.format("%s", contT));
		Tooltip tpFurgoneta = new Tooltip(String.format("%s", contF));
		Tooltip tpAutobus = new Tooltip(String.format("%s", contA));
		
		Tooltip.install(slTurismo.getNode(), tpTurismo);
		Tooltip.install(slFurgoneta.getNode(), tpFurgoneta);
		Tooltip.install(slAutobus.getNode(), tpAutobus);
	}
	
	@FXML
	void limpiar() {
		tfFecha.setText("");
	}
}
