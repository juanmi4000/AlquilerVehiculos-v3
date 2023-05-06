package org.iesalandalus.programacion.alquilervehiculos.vista.graficos;

import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.graficos.utilidades.Dialogos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LanzadorVentanaPrincipal extends Application {

	private static final String TITULO = "Vista Ventana Alquiler de Vehículos";

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			Controlador ventanaPrincipal = Controladores.get("vistas/VentanaPrincipal.fxml", TITULO, null);
			ventanaPrincipal.getEscenario().setOnCloseRequest(e -> confirmarSalida(ventanaPrincipal.getEscenario(), e));
			ventanaPrincipal.getEscenario().show();

			//Controlador ventanaPrincipal = Controladores.get("vistas/VentanaPrincipal.fxml", TITULO, null);
			// Dialogos.setHojasEstilos(ventanaPrincipal).getEscenario().getScene().getRoot().getStylesheets().get(0));
			//ventanaPrincipal.getEscenario().setOnCloseRequest(e -> confirmarSalida(ventanaPrincipal.getEscenario(), e));
			// Image icono = new Image
			// (LocalizadorRecursos.class.getResourceAsStream("imagenes/iconoVehiculos.png"));
			// ventanaPrincipal.getEscenario().getIcons().add(icono);
			//ventanaPrincipal.getEscenario().show();
			/*
			FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml")); // dice de donde lo tiene
																							// que coger
			Parent raiz = cargadorVentanaPrincipal.load(); // lo carga y lo traducia a la gerarquia de nodos

			Scene escena = new Scene(raiz);
			primaryStage.setTitle("Vista grafica para el alquiler de vehiculos");
			primaryStage.setScene(escena);
			primaryStage.show();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void comenzar() {
		launch(LanzadorVentanaPrincipal.class); // crea una instancia de la clase que le estamos pasando
	}

	private void confirmarSalida(Stage escenario, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				escenario)) {
			escenario.close();
		} else {
			e.consume();
		}

	}

}
