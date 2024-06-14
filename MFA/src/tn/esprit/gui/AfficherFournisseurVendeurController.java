/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.entities.CategorieF;
import tn.esprit.entities.Fournisseur;
import tn.esprit.services.FournisseurService;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AfficherFournisseurVendeurController implements Initializable {

    @FXML
    private TableView<Fournisseur> categoriesTable;
    @FXML
    private TableColumn<Fournisseur, String> NomColumn;
    @FXML
    private TableColumn<Fournisseur, Integer> TelColumn;
    @FXML
    private TableColumn<Fournisseur, String> AddressColumn;
    @FXML
    private TableColumn<Fournisseur, String> EmailColumn;
    @FXML
    private TableColumn<Fournisseur, String> CategorieColumn;
    @FXML
    private TableColumn<Fournisseur, String> SiteWebColumn;
    @FXML
    private TableColumn<Fournisseur, String> ImgColumn;
    @FXML
    private TextField tfRecherche;
    @FXML
    private Button btQR;
    @FXML
    private ImageView imageqr;
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
        FournisseurService cs = new FournisseurService();
        ArrayList<Fournisseur> f = (ArrayList<Fournisseur>) cs.afficher();
        // Récupérer depuis une source de données
        ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList(f);

        // Ajouter  à la table
        categoriesTable.setItems(fournisseurs);

        // Configurer les colonnes de la table
        // idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));

        TelColumn.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        CategorieColumn.setCellValueFactory(cellData -> {
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
            CategorieF c = cellData.getValue().getCategorieId();
            if (c != null) {
                nameProperty.set(c.getLibelle());
            }
            return nameProperty;
        });
        SiteWebColumn.setCellValueFactory(new PropertyValueFactory<>("website"));
        ImgColumn.setCellValueFactory(new PropertyValueFactory<>("img"));

        tfRecherche.setPromptText("recherche");
        // new liste filtré
         FilteredList<Fournisseur> filteredInstrumentList = new FilteredList<>(fournisseurs, Fournisseur -> true);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredInstrumentList.setPredicate(Fournisseur
                    -> Fournisseur.getNom().toLowerCase().contains(newValue.toLowerCase())
            );
        });
        categoriesTable.setItems(filteredInstrumentList);
    }

    @FXML
    private void QrCode(ActionEvent event) throws WriterException, IOException {
        
        Fournisseur selectedPiece = categoriesTable.getSelectionModel().getSelectedItem();
        if (selectedPiece != null) {
            String qrText = selectedPiece.getWebsite();
            createQR(qrText);
        } else {
            alert("Please select a row.");
        }
    }
    private void createQR(String qrText) throws WriterException, IOException {
        try {
            String path = System.getProperty("user.home") + File.separatorChar + "Desktop" + File.separatorChar + "qr_code.png";
            BitMatrix matrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
            alert("QR Code Created");
            setQRImage(path);
            //hl.setVisible(false);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    private void setQRImage(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            Image image = new Image(stream) {};
            imageqr.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void RedirectionS(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseurVendeur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}
