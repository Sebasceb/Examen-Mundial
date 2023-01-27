package com.mycompany.examenmundial;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

    private static ArrayList<Equipo> listaEquipos;
    @FXML
    private ComboBox<Equipo> cmbEquipos;
    @FXML
    private ComboBox<Jugador> cmbJugadores;
    @FXML
    private VBox panelJugador;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnGenerar;

    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hello World");
        listaEquipos = Equipo.obtenerEquipos();

        cmbEquipos.getItems().addAll(listaEquipos);

        btnGenerar.setOnAction(eh -> generarNomina());
    }

    @FXML
    public void cargarJugadoresEnCombo(ActionEvent e) {
        Equipo eq = cmbEquipos.getValue();
        cmbJugadores.getItems().setAll(eq.getListaJugadores());

    }

    @FXML
    private void llenarPanelJugadores() {
        panelJugador.getChildren().clear();
        Jugador j1 = cmbJugadores.getValue();

        HBox hbox = new HBox(25);
        ImageView imgv = new ImageView();

        System.out.println(App.pathFiles + j1.getNombreImagen());
        try ( FileInputStream input = new FileInputStream(App.pathFiles + j1.getNombreImagen())) {

            Image image = new Image(input);

            imgv.setImage(image);
            imgv.setFitHeight(200);
            imgv.setPreserveRatio(true);

        } catch (Exception e) {
            System.out.println("No se encuentra la imagen");

        }
        GridPane gridPane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(40);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60);

        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints(50);
            gridPane.getRowConstraints().add(row);
        }
        Label lblNombre1 = new Label(j1.getNombre());

        gridPane.getColumnConstraints().addAll(col1, col2);

        Label lblNombre = new Label("Nombre:");
        gridPane.add(lblNombre, 0, 0);

        gridPane.add(lblNombre1, 1, 0);

        Label lblPos1 = new Label("Posicion:");
        Label lblPos2 = new Label(j1.getPosicion());
        gridPane.add(lblPos1, 0, 1);
        gridPane.add(lblPos2, 1, 1);
        Label lblSal1 = new Label("Salario:");
        Label lblSal2 = new Label(j1.getSalario() + "");
        gridPane.add(lblSal1, 0, 2);
        gridPane.add(lblSal2, 1, 2);

        hbox.getChildren().addAll(imgv, gridPane);
        panelJugador.getChildren().add(hbox);
    }

    private void generarNomina() {
        Thread t = new Thread(() -> {
            try ( FileWriter fw = new FileWriter(App.pathFiles + "nomina.txt")) {

                for (Equipo e : listaEquipos) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            lblMensaje.setText("Calculando nómina de " + e.getPais());
                        }

                    });
                    double totalE = e.calcularTotalSueldos();

                    fw.write(e.getPais() + "," + e.getListaJugadores().size() + "," + totalE + "\n");
                    System.out.println("equipo" + totalE);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        lblMensaje.setText("Archivo creado exitosamente");
                    }
                });
            } catch (IOException ex) {
                System.out.println("Error al escribir el archivo");
            }

        });
        t.start();
    }

}
