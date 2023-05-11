package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class VentanaPrincipal extends Controlador {

	@FXML
	private MenuItem confirmarSalida;

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
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error al insertar el cliente", e.getMessage(), getEscenario());
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
	void borrarCliente(ActionEvent event) {
		PedirDni pedirDni = (PedirDni) Controladores.get("vistas/PedirDni.fxml", "BORRAR CLIENTE", getEscenario());
		pedirDni.limpiar();
		pedirDni.getEscenario().showAndWait();
		try {
			Cliente cliente = pedirDni.getCliente();
			if (cliente != null) {
				VistaGraficos.getInstancia().getControlador().borrar(cliente);
				Dialogos.mostrarDialogoAdvertencia("BORRAR CLIENTE", "El cliente se ha borrado correctamente.",
						getEscenario());
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("ERROR AL BORRAR", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void leerAlquiler(ActionEvent event) {
		LeerAlquiler leerAlquiler = (LeerAlquiler) Controladores.get("vistas/LeerCliente.fxml", "INSERTAR ALQUILER", getEscenario());
		leerAlquiler.limpiar();
		leerAlquiler.getEscenario().showAndWait();
		try {
			Alquiler alquiler = leerAlquiler.getAlquiler();
			if(alquiler != null) {
				VistaGraficos.getInstancia().getControlador().insertar(alquiler);
				Dialogos.mostrarDialogoAdvertencia("INSERTAR ALQUILER", "El alquiler ha sido insertado correctamente.", getEscenario());
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
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
	void borrarAlquiler(ActionEvent event) {
		
	}

	@FXML
	void leerVehiculo(ActionEvent event) {
		LeerVehiculo controladorLeerVehiculo = (LeerVehiculo) Controladores.get("vistas/LeerVehiculo.fxml",
				"Leer Vehiculo", getEscenario());
		controladorLeerVehiculo.limpiar();
		controladorLeerVehiculo.getEscenario().showAndWait();
		try {
			Vehiculo vehiculo = controladorLeerVehiculo.getVehiculo();
			if (vehiculo != null) {
				VistaGraficos.getInstancia().getControlador().insertar(vehiculo);
				Dialogos.mostrarDialogoAdvertencia("INSERTAR VEHICULO", "El vehiculo se ha insertado correctamente.",
						getEscenario());
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error al insertar el vehiculo", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void listarVehiculos(ActionEvent event) {

	}

	@FXML
	void borrarVehiculo(ActionEvent event) {

	}

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void acercaDe(ActionEvent event) {
		AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de", getEscenario());
		acercaDe.getEscenario().showAndWait();
	}

}