/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.entities.Fournisseur;
import tn.esprit.services.FournisseurService;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class FournisseurClientController implements Initializable {

    

   
    @FXML
    private GridPane Grid;
    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private Button s;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FournisseurService sf = new FournisseurService();
        try {
            List<Fournisseur> fournisseur = sf.afficher();
            int row = 1;
            int column = 0;
            for (int i = 0; i < fournisseur.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Model.fxml"));
                AnchorPane pane = loader.load();
                ModelController controller = loader.getController();
                controller.setData(fournisseur.get(i));
                Grid.add(pane, column, row);
                column++;
                if (column > 2) {
                    row = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RedirectionS(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FournisseurClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }



    

}
