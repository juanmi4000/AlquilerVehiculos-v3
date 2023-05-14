package org.iesalandalus.programacion.alquilervehiculos.vista.graficos.controladores;

import java.awt.Desktop;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.VistaGraficos;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListarVehiculos extends Controlador {

	private static final String ERROR = "ERROR";

	@FXML
	private TableColumn<Vehiculo, Integer> tcCilindrada; // esto es de tipo promitivo o de objeto?

	@FXML
	private TableColumn<Vehiculo, String> tcMarca;

	@FXML
	private TableColumn<Vehiculo, String> tcMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tcModelo;

	@FXML
	private TableColumn<Vehiculo, Integer> tcPlazas;

	@FXML
	private TableColumn<Vehiculo, Integer> tcPma;

	@FXML
	private TableColumn<Vehiculo, String> tcTipo;

	@FXML
	private TextField tfCambiarCilindrada;

	@FXML
	private TextField tfCambiarMarca;

	@FXML
	private TextField tfCambiarMatricula;

	@FXML
	private TextField tfCambiarModelo;

	@FXML
	private TextField tfCambiarPlazas;

	@FXML
	private TextField tfCambiarPma;

	@FXML
	private TextField tfCambiarTipo;

	@FXML
	private TextField tfCilindradaEncontrada;

	@FXML
	private TextField tfListarAlquileresV;

	@FXML
	private TextField tfMarcaEncontrada;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfMatriculaEncontrada;

	@FXML
	private TextField tfModeloEncontrado;

	@FXML
	private TextField tfPlazasEncontradas;

	@FXML
	private TextField tfPmaEncontrada;

	@FXML
	private TextField tfTipoEncontrado;

	@FXML
	private TableView<Vehiculo> tvListaVehiculos;

	@FXML
	private void initialize() {
		deshabilitar();
		tvListaVehiculos.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> filaSeleccionada(ne));
	}

	@FXML
	void acercaDe(ActionEvent event) {
		AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de", getEscenario());
		acercaDe.getEscenario().showAndWait();
	}

	@FXML
	void borrarVehiculoTabla(ActionEvent event) {
		Vehiculo vehiculo = tvListaVehiculos.getSelectionModel().getSelectedItem();
		try {
			VistaGraficos.getInstancia().getControlador().borrar(vehiculo);
			tvListaVehiculos.getItems().remove(vehiculo);
			Dialogos.mostrarDialogoAdvertencia("BORRAR VEHICULO", "El vehículo ha sido borrado correctamente.",
					getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void borrarVehiculo(ActionEvent event) {

		try {
			Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador()
					.buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText()));
			VistaGraficos.getInstancia().getControlador().borrar(vehiculo);
			tvListaVehiculos.getItems().remove(vehiculo);
			Dialogos.mostrarDialogoAdvertencia("BORRAR VEHICULO", "El vehículo ha sido borrado correctamente.",
					getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}
	}

	@FXML
	void buscarVehiculo(ActionEvent event) {
		try {
			Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador()
					.buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText()));
			vehiculoEncontrado(vehiculo);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
		}

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
	void insertarVehiculo(ActionEvent event) {
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
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
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
	void listarAlquileresVehiculo(ActionEvent event) {
		ListarAlquileresClienVehi listarAlquileresVehiculo = (ListarAlquileresClienVehi) Controladores
				.get("vistas/ListarAlquileresClienVehi.fxml", "ALQUILERES VEHICULO", getEscenario());
		try {
			Vehiculo vehiculo = VistaGraficos.getInstancia().getControlador().buscar(Vehiculo.getVehiculoConMatricula(tfListarAlquileresV.getText()));
			listarAlquileresVehiculo.actualizar(VistaGraficos.getInstancia().getControlador().getAlquileres(vehiculo));
			listarAlquileresVehiculo.getEscenario().showAndWait();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ERROR, e.getMessage(), getEscenario());
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
	void actualizar(List<Vehiculo> vehiculos) {
		tvListaVehiculos.setItems(FXCollections.observableArrayList(vehiculos));
	}

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void deshabilitar() {
		Controles.deshabilitarCamposTexto(tfCambiarTipo, tfCambiarMarca, tfCambiarModelo, tfCambiarMatricula,
				tfCambiarCilindrada, tfCambiarPlazas, tfCambiarPma);
		Controles.deshabilitarCamposTexto(tfTipoEncontrado, tfMarcaEncontrada, tfModeloEncontrado,
				tfMatriculaEncontrada, tfCilindradaEncontrada, tfPlazasEncontradas, tfPmaEncontrada);
	}

	@FXML
	private void filaSeleccionada(Vehiculo vehiculo) {
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
	private void vehiculoEncontrado(Vehiculo vehiculo) {
		tfMarcaEncontrada.setText(vehiculo.getMarca());
		tfModeloEncontrado.setText(vehiculo.getModelo());
		tfMatriculaEncontrada.setText(vehiculo.getMatricula());
		if (vehiculo instanceof Turismo turismo) {
			tfTipoEncontrado.setText("Turismo");
			tfCilindradaEncontrada.setText(String.format("%s", turismo.getCilindrada()));
			tfPmaEncontrada.setText("-----------");
			tfPlazasEncontradas.setText("-----------");
		} else if (vehiculo instanceof Furgoneta furgoneta) {
			tfTipoEncontrado.setText("Furgoneta");
			tfCilindradaEncontrada.setText("-----------");
			tfPmaEncontrada.setText(String.format("%s", furgoneta.getPma()));
			tfPlazasEncontradas.setText(String.format("%s", furgoneta.getPlazas()));
		} else if (vehiculo instanceof Autobus autobus) {
			tfTipoEncontrado.setText("Autobus");
			tfCilindradaEncontrada.setText("-----------");
			tfPmaEncontrada.setText("-----------");
			tfPlazasEncontradas.setText(String.format("%s", autobus.getPlazas()));
		}
	}

}
