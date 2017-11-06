/*
 * Copyright Jani Pasanen, 2017
 */
package lunchmeny;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Jani Pasanen
 */
public class LunchmenyController implements Initializable {

    @FXML
    private Label lblMeat;
    @FXML
    private Label lblFish;
    @FXML
    private Label lblPasta;
    @FXML
    private Label lblVeg;

    static String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
    static String dow = weekday_name.toLowerCase(Locale.ENGLISH);
    LunchmenyModel model = new LunchmenyModel();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            model.lasInDagensLunch(dow);
        } catch (IOException ex) {
            Logger.getLogger(LunchmenyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LunchmenyController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblMeat.setText(LunchmenyModel.daysMeat);
        lblFish.setText(LunchmenyModel.daysFish);
        lblPasta.setText(LunchmenyModel.daysPasta);
        lblVeg.setText(LunchmenyModel.daysVegetarian);

        update();
    }

    public void update() {

        Timeline timeline = new Timeline(
                // Call update method for every 15 minutes.
                new KeyFrame(Duration.minutes(15),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {

                            model.lasInDagensLunch(dow);
                        } catch (IOException ex) {
                            Logger.getLogger(LunchmenyController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(LunchmenyController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        lblMeat.setText(LunchmenyModel.daysMeat);
                        lblFish.setText(LunchmenyModel.daysFish);
                        lblPasta.setText(LunchmenyModel.daysPasta);
                        lblVeg.setText(LunchmenyModel.daysVegetarian);
                       // System.out.println("uppdaterades: " + dateFormat.format(date));

                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

}
