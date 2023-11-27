package chronometer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class TimerController {

    @FXML
    private Label timerLabel;

    @FXML
    private Button startButton, stopButton;

    @FXML
    private ImageView statusImageView;

    private Thread timerThread;
    private boolean isRunning = false;

    private int  segundos;

    private int horas;

    private int minutos;

    private MediaPlayer mediaPlayer;

    @FXML
    private void initialize() {
        stopButton.setDisable(true);

        URL imageUrl = getClass().getResource("/chronometer/images/rojo.png");

        if (imageUrl != null) {
            System.out.println("Image URL: " + imageUrl);
            statusImageView.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            System.out.println("Error: Image URL is null");
        }

        String soundFile = "/chronometer/sound/click.mp3";
        Media sound = new Media(getClass().getResource(soundFile).toString());
        mediaPlayer = new MediaPlayer(sound);

    }

    /**
     *
     */
    @FXML
    private void startTimer() {
        reproducirSonido();
        if (!isRunning) {
            timerThread = new Thread(() -> {
                try {
                    isRunning = true;
                    while (isRunning) {
                        Thread.sleep(1000);
                        segundos++;

                        Platform.runLater(() -> {
                            actualizarTiempo();
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            timerThread.setDaemon(true);
            timerThread.start();

            startButton.setText("CONTANDO");
            startButton.setDisable(true);
            stopButton.setDisable(false);

            // Cambiar la imagen a verde
            URL imageUrl = getClass().getResource("/chronometer/images/verde.png");
            if (imageUrl != null) {
                System.out.println("Image URL: " + imageUrl);
                statusImageView.setImage(new Image(imageUrl.toExternalForm()));
            } else {
                System.out.println("Error: Image URL is null");
            }
        }
    }

    /**
     *
     */
    @FXML
    private void stopTimer() {
        reproducirSonido();
        if (isRunning) {
            isRunning = false;
            startButton.setText("DETENIDO");
            stopButton.setText("REANUDAR");

            URL imageUrl = getClass().getResource("/chronometer/images/rojo.png");
            if (imageUrl != null) {
                System.out.println("Image URL: " + imageUrl);
                statusImageView.setImage(new Image(imageUrl.toExternalForm()));
            } else {
                System.out.println("Error: Image URL is null");
            }
        } else {
            isRunning = true;
            timerThread = new Thread(() -> {
                try {
                    while (isRunning) {
                        Thread.sleep(1000);
                        segundos++;

                        Platform.runLater(() -> {
                            actualizarTiempo();
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            timerThread.setDaemon(true);
            timerThread.start();

            startButton.setText("CONTANDO");
            stopButton.setText("DETENER");

            // Cambiar la imagen a verde
            URL imageUrl = getClass().getResource("/chronometer/images/verde.png");
            if (imageUrl != null) {
                System.out.println("Image URL: " + imageUrl);
                statusImageView.setImage(new Image(imageUrl.toExternalForm()));
            } else {
                System.out.println("Error: Image URL is null");
            }
        }
    }

    /**
     *
     */
    @FXML
    private void resetTimer() {
        reproducirSonido();
        isRunning = false;
        startButton.setText("INICIAR");
        startButton.setDisable(false);
        stopButton.setDisable(true);

        horas = minutos = segundos = 0;
        actualizarTiempo();
        URL imageUrl = getClass().getResource("/chronometer/images/rojo.png");
        if (imageUrl != null) {
            System.out.println("Image URL: " + imageUrl);
            statusImageView.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            System.out.println("Error: Image URL is null");
        }
    }

    /**
     *
     */
    private void actualizarTiempo() {
        int horasDisplay = horas;
        int minutosDisplay = minutos;
        int segundosDisplay = segundos;

        if (segundosDisplay == 60) {
            minutos++;
            minutosDisplay++;
            segundosDisplay = 0;
            segundos = 0;
        }

        if (minutosDisplay == 60) {
            horas++;
            horasDisplay++;
            minutosDisplay = 0;
            minutos = 0;
        }

        if (horasDisplay == 24) {
            horasDisplay = 0;
            horas = 0;
        }

        timerLabel.setText(String.format("%02d:%02d:%02d", horasDisplay, minutosDisplay, segundosDisplay));
    }

    private void reproducirSonido() {
        String soundFile = "/chronometer/sound/click.mp3";
        Media sound = new Media(getClass().getResource(soundFile).toString());
        MediaPlayer newMediaPlayer = new MediaPlayer(sound);
        newMediaPlayer.play();
    }
}

