/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.gui.FirstWindow;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.tools.MaConnexion;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class StatistiquesController implements Initializable {
    @FXML
    private PieChart piechart;
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

    @FXML
    private Button retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Générer les statistiques de réclamation
            Map<String, Integer> stats = generateClaimStatistics();

            // Afficher les statistiques dans le graphique circulaire
            displayStatistics(stats);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setHeaderText("Une erreur s'est produite lors de la génération des statistiques");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        retour.setOnAction(event -> redirectToList());
    }

    public Map<String, Integer> generateClaimStatistics() throws SQLException {
        Map<String, Integer> stats = new HashMap<>();

        try {
    String query = "SELECT c.libelle, COUNT(*) as nbr FROM fournisseur f JOIN categorie_f c ON f.categorie_id = c.id GROUP BY c.libelle";
    Statement st = MaConnexion.getInstance().getCnx().createStatement();
    ResultSet rs = st.executeQuery(query);

    while(rs.next()) {
        String categorie = rs.getString("libelle");
        int nbrFournisseurs = rs.getInt("nbr");
        stats.put(categorie, nbrFournisseurs);
    }

        

        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // renvoie l'exception SQLException pour que le bloc catch dans initialize() puisse la capturer
        }

        return stats;
    }

    public void displayStatistics(Map<String, Integer> stats) {
        // Effacer les anciennes données du graphique
        data.clear();
// Ajouter les nouvelles données au graphique
        stats.forEach((statut, count) -> {
            PieChart.Data slice = new PieChart.Data(statut, count);
data.add(slice);
});
      // Afficher les données dans le graphique
    piechart.setData(data);
}



  public void redirectToList() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherFournisseur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjouterFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void retour(MouseEvent event) {
        this.redirectToList();
    }
  
    
}