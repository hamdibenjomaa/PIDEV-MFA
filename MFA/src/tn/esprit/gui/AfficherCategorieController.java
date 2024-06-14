/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.entities.CategorieF;
import tn.esprit.services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<CategorieF> categoriesTable;


    @FXML
    private TableColumn<CategorieF, String> libelleColumn;
    @FXML
    private TableColumn<CategorieF, Void> actionColumn;
    @FXML
    private Button btAjoutC;
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
        CategorieService cs = new CategorieService();
        ArrayList<CategorieF> c = (ArrayList<CategorieF>) cs.afficher();
        // Récupérer la liste de catégories depuis une source de données
        ObservableList<CategorieF> categories = FXCollections.observableArrayList(c);

        // Ajouter les catégories à la table
        categoriesTable.setItems(categories);

        // Configurer les colonnes de la table
       // idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        actionColumn.setCellFactory(param -> new TableCell<CategorieF, Void>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    CategorieF c = (CategorieF) getTableRow().getItem();
                    // Supprimer le rendez-vous de la base de données

                    cs.supprimer(c);
                    // Supprimer le rendez-vous de la table
                    getTableView().getItems().remove(c);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        // TODO

    }

    @FXML
    private void RedirectionAjouter(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AJouterCategorie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
