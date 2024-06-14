/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class ListEventController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private TableView<?> categoriesTable;
    @FXML
    private TableColumn<?, ?> NomColumn;
    @FXML
    private TableColumn<?, ?> TelColumn;
    @FXML
    private TableColumn<?, ?> AddressColumn;
    @FXML
    private TableColumn<?, ?> EmailColumn;
    @FXML
    private TableColumn<?, ?> CategorieColumn;
    @FXML
    private TableColumn<?, ?> SiteWebColumn;
    @FXML
    private TableColumn<?, ?> ImgColumn;
    @FXML
    private TableColumn<?, ?> actionColumn;
    @FXML
    private Button btAjoutF;
    @FXML
    private Button btCategorie;
    @FXML
    private Button btFournisseur;
    @FXML
    private Button btModifier;
    @FXML
    private Button handleStatButton;
    @FXML
    private TextField tfRecherche;
    @FXML
    private Button btQR;
    @FXML
    private ImageView imageqr;
    @FXML
    private Label eventNameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RedirectionAjouter(ActionEvent event) {
    }

    @FXML
    private void RedirectionCategorie(ActionEvent event) {
    }

    @FXML
    private void RedirectionFournisseur(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void handleStatButton(ActionEvent event) {
    }

    @FXML
    private void QrCode(ActionEvent event) {
    }
    
}
