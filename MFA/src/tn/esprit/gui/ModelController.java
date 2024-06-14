/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.entities.Fournisseur;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class ModelController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    

    int id_single;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(Fournisseur i) {
        //Image img = new Image(getClass().getResourceAsStream("C:\\xampp\\htdocs\\aa\\" + i.getImg()));
        //image.setImage(img);
        nom.setText(i.getNom());
        
        File f = new File("C:\\xampp\\htdocs\\aa\\" + i.getImg());
        image.setImage(new Image(f.toURI().toString()));
        
        id_single = i.getId();

    }

}
