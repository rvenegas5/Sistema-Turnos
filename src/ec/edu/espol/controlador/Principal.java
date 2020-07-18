package ec.edu.espol.controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author WILLIAM
 */
public class Principal extends Application{

    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("/ec/edu/espol/vista/VentanaPrincipal.fxml"));
            Pane ventana= (Pane) loader.load();

            // Muestra la escena que contiene el diseño raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Lanza el programa.
    public static void main(String[] args) {
        launch(args);
    }
}
