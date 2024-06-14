/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.CategorieF;
import tn.esprit.services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AjouterUneCategorieController implements Initializable {

    @FXML
    private TextField tfLibelle;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private Button s;
    @FXML
    private Button c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
        String libelle = tfLibelle.getText();
        if(tfLibelle.getText().length()==0){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajouter une categorie");
            alert.setHeaderText(null);
            alert.setContentText("il faut remplire tous les informations");
            alert.showAndWait();
        } else {
        CategorieF c = new CategorieF(100,libelle);
        CategorieService cs = new CategorieService();
        cs.ajouter(c);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
    }

    

    @FXML
    private void RedirectionS(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void RedirectionCategorieS(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}
