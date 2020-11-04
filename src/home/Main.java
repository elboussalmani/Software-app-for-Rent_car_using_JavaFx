/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author oXCToo
 */
public class Main extends Application {


    public void start(Stage stageq) throws Exception {
        Parent rootq = FXMLLoader.load(getClass().getResource("/FXML/OnBoard.fxml"));

          
        Scene scene = new Scene(rootq);
        stageq.setScene(scene);
        stageq.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
