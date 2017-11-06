/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunchmeny;

import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Copyright 2017 Jani Pasanen
 * @author Jani Pasanen, 2017
 */
public class Lunchmeny extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Lunchmeny.fxml"));
       
        Scene scene = new Scene(root);
        scene.setCursor(Cursor.NONE);  
        
         // StageStyle.UNDECORATED gömmer programmenyn med programnamn, minimera, maximera och stäng knappen.
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
