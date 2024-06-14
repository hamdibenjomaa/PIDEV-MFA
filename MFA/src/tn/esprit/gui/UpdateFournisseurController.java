/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.esprit.entities.CategorieF;
import tn.esprit.entities.Fournisseur;
import tn.esprit.services.CategorieService;
import tn.esprit.services.FournisseurService;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class UpdateFournisseurController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfSiteWeb;
    @FXML
    private ChoiceBox<CategorieF> boxCategorie;
    @FXML
    private Button ModifierF;
    @FXML
    private Button AjouterImg;
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
        List<CategorieF> testList = cs.afficher();
        boxCategorie.setItems(FXCollections.observableArrayList(testList));
        boxCategorie.setConverter(new StringConverter<CategorieF>() {

            @Override
            public String toString(CategorieF c) {
                return c == null ? "" : c.getLibelle();
            }

            @Override
            public CategorieF fromString(String string) {
                return null;
            }
        });

        tfNom.setText(AfficherFournisseurController.nom);
        tfAddress.setText(AfficherFournisseurController.address);
        tfEmail.setText(AfficherFournisseurController.email);
        tfTel.setText(Integer.toString(AfficherFournisseurController.tel));
        tfSiteWeb.setText(AfficherFournisseurController.nom);
    }

    private void RedirectionCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void RedirectionFournisseur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ModifierFournisseur(ActionEvent event) throws IOException {
        Integer id = AfficherFournisseurController.id;
        String nom = tfNom.getText();
        int tel = Integer.parseInt(tfTel.getText());
        String address = tfAddress.getText();
        String email = tfEmail.getText();
        String website = tfSiteWeb.getText();
        String img = AjouterImg.getText();
        CategorieF category = boxCategorie.getValue();
        if (tfNom.getText().length() == 0 || tfTel.getText().length() == 0 || tfEmail.getText().length() == 0 || tfAddress.getText().length() == 0 || tfSiteWeb.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText(null);
            alert.setContentText("il faut remplire tous les informations");
            alert.showAndWait();

        } else {
            if (!tfEmail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                Alert aler = new Alert(Alert.AlertType.ERROR);
                aler.setTitle("Erreur");
                aler.setHeaderText(null);
                aler.setContentText("Le format de l'email est invalide !");
                ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                aler.getButtonTypes().setAll(okButton);
                aler.showAndWait();
            } else {
                if (!tfNom.getText().matches("^[a-zA-Z ]{2,50}$")) {
                    Alert aler = new Alert(Alert.AlertType.ERROR);
                    aler.setTitle("Erreur");
                    aler.setHeaderText(null);
                    aler.setContentText("Le format de Nom est invalide !");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    aler.getButtonTypes().setAll(okButton);
                    aler.showAndWait();
                } else {
                    if (tfTel.getText().length() != 8) {
                        Alert aler = new Alert(Alert.AlertType.ERROR);
                        aler.setTitle("Erreur");
                        aler.setHeaderText(null);
                        aler.setContentText("Le format de numero de telephone est invalide !");
                        ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                        aler.getButtonTypes().setAll(okButton);
                        aler.showAndWait();
                    } else {

                        Fournisseur f = new Fournisseur(id, nom, tel, address, email, website, img, category);
                        FournisseurService cf = new FournisseurService();
                        cf.update(f);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    }
                }
            }
        }
    }

    @FXML
    private void AjouterImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fch = new FileChooser();

        File f = fch.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {

                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:\\Users\\Home\\Desktop\\aa\\" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Done");

            } finally {
                is.close();
                os.close();

            }

            File file = new File(f.getName());
//            System.out.println(file.toURI().toString());
            //UserImg.setImage(new Image(file.toURI().toString()));
            String Imguser = f.getName();
            System.out.println(Imguser);
            //ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }

        AjouterImg.setText(f.getName());

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
